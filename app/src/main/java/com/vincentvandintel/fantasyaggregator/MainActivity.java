package com.vincentvandintel.fantasyaggregator;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vincentvandintel.fantasyaggregator.model.Leader;
import com.vincentvandintel.fantasyaggregator.request.RequestSingleton;
import com.vincentvandintel.fantasyaggregator.util.LeadersAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.vincentvandintel.fantasyaggregator.R.array.position;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create spinner dropdown of leader positions
        InitializeSpinner();
    }

    private void InitializeSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.position_spinner);

        //Use custom adapter to populate spinner with NFL positions
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MainActivity.this,
                position, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        // Event handler for selecting spinner dropdown
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                final String leaderPosition = parent.getItemAtPosition(pos).toString();
                final Button button = (Button) findViewById(R.id.get_leaders_button_id);
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        // Code here executes on main thread after user presses button
                        // send HTTP request to NFL API for scoring leaders
                        String api = getString(R.string.api);
                        getLeaderData(api, leaderPosition, 5);
                    }
                });
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getLeaderData(String api, final String position, int count) {
        String url = new StringBuilder(api).append("/players/scoringleaders?format=json&sort=pts&count=")
                .append(count).append("&position=").append(position).toString();

        JsonObjectRequest jsObjRequest = InitializeLeaders(position, url);
        RequestSingleton.getInstance(this).addToRequestQueue(jsObjRequest);
    }

    @NonNull
    private JsonObjectRequest InitializeLeaders(final String position, String url) {
        return new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            ArrayList<Leader> leaders = formatLeaders(response, position);
                            LeadersAdapter leaderListAdapter = new LeadersAdapter(MainActivity.this, leaders);

                            ListView leadersListView = (ListView) findViewById(R.id.leaders_list_view);
                            leadersListView.setAdapter(leaderListAdapter);


                        } catch (JSONException jsonException){
                            Log.e("Error", "Error: " + jsonException.toString());
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.toString());
                    }
                });
    }

    private ArrayList<Leader> formatLeaders(JSONObject response, String position) throws JSONException {
        JSONObject positions = response.getJSONObject("positions");
        String positionLeaders = positions.getJSONArray(position).toString();

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Leader>>(){}.getType();
        ArrayList<Leader> leaders = gson.fromJson(positionLeaders, type);
        return leaders;
    }
}
