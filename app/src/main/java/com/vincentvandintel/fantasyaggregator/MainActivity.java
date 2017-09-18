package com.vincentvandintel.fantasyaggregator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.vincentvandintel.fantasyaggregator.adapter.LeadersAdapter;
import com.vincentvandintel.fantasyaggregator.model.Leader;
import com.vincentvandintel.fantasyaggregator.request.RequestSingleton;
import com.vincentvandintel.fantasyaggregator.util.Fantasy;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.vincentvandintel.fantasyaggregator.R.array.position;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
    }

    private void setupView() {
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupNavigationDrawer(toolbar);
        // Create spinner dropdown of leader positions
        initializePositionSpinner();
    }

    private void setupNavigationDrawer(Toolbar toolbar) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                MainActivity.this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close) {
            @Override
            public void onDrawerClosed(View v){
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };

        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.left_drawer);
        navigationView.setNavigationItemSelectedListener(MainActivity.this);
    }

    private void initializePositionSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.position_spinner);
        populateSpinner(spinner);
    }

    private void populateSpinner(Spinner spinner) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MainActivity.this,
                position, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @NonNull
    private JsonObjectRequest initializeLeaders(final String position, String url) {
        return new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            displayLeaders(response, position);
                        } catch (JSONException e){
                            Log.e("Error", "Error: " + e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.toString());
                    }
                });
    }

    private void displayLeaders(JSONObject response, String position) throws JSONException {
        Fantasy fantasy = new Fantasy();
        ArrayList<Leader> leaders = fantasy.formatLeaders(response, position);
        Log.v("Leaders", "Leaders are: " + leaders);
        LeadersAdapter leaderListAdapter = new LeadersAdapter(MainActivity.this, leaders);
        ListView leadersListView = (ListView) findViewById(R.id.leaders_list_view);
        leadersListView.setAdapter(leaderListAdapter);
    }

    private void getLeaderData(String api, final String position, int count) {
        String url = new StringBuilder(api).append("/players/scoringleaders?format=json&sort=pts&count=")
                .append(count).append("&position=").append(position).toString();

        JsonObjectRequest jsObjRequest = initializeLeaders(position, url);
        RequestSingleton.getInstance(this).addToRequestQueue(jsObjRequest);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        String leaderPosition = parent.getItemAtPosition(pos).toString();
        // save leader position to private state
        getPreferences(MODE_PRIVATE).edit().putString("leaderPosition", leaderPosition).apply();

        final Button button = (Button) findViewById(R.id.get_leaders_button_id);
        button.setOnClickListener(this);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void onClick(View v) {
        // Code here executes on main thread after user presses button
        // send HTTP request to NFL API for scoring leaders
        String api = getString(R.string.api);
        String leaderPosition = getPreferences(MODE_PRIVATE).getString("leaderPosition","");
        getLeaderData(api, leaderPosition, 5);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int menuItemId = menuItem.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        switch (menuItemId){
            case R.id.get_leaders_menu_item:
              //  Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_SHORT).show();
                drawer.closeDrawers();
                break;
            case R.id.get_player_rankings_menu_item:
               // Toast.makeText(getApplicationContext(),"Settings",Toast.LENGTH_SHORT).show();
                drawer.closeDrawers();
                break;

        }

        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
