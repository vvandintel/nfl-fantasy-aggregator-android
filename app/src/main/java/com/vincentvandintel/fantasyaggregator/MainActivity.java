package com.vincentvandintel.fantasyaggregator;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.vincentvandintel.fantasyaggregator.adapter.PlayerNewsAdapter;
import com.vincentvandintel.fantasyaggregator.adapter.PlayerRankingsAdapter;
import com.vincentvandintel.fantasyaggregator.adapter.ScoringLeadersAdapter;
import com.vincentvandintel.fantasyaggregator.fragment.PlayerNewsFragment;
import com.vincentvandintel.fantasyaggregator.fragment.PlayerRankingsFragment;
import com.vincentvandintel.fantasyaggregator.fragment.ScoringLeadersFragment;
import com.vincentvandintel.fantasyaggregator.model.PlayerNewsItem;
import com.vincentvandintel.fantasyaggregator.model.RankedLeader;
import com.vincentvandintel.fantasyaggregator.model.ScoringLeader;
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
        setupView(savedInstanceState);
    }

    private void setupView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        setupFantasyFragment(savedInstanceState);
        if (findViewById(R.id.toolbar) == null) {
            return;
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupNavigationDrawer(toolbar);
    }

    private void setupFantasyFragment(Bundle savedInstanceState) {
        if (findViewById(R.id.fantasy_fragment_container) == null || savedInstanceState != null) {
            return;
        }
        ScoringLeadersFragment scoringLeadersFragment = new ScoringLeadersFragment();
        scoringLeadersFragment.setArguments(getIntent().getExtras());
        getFragmentManager()
                .beginTransaction()
                .add(R.id.fantasy_fragment_container, scoringLeadersFragment)
                .commitNow();
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

    public void initializePositionSpinner() {
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
    private JsonObjectRequest initializeLeaders(String url) {
        Log.v("info", "Requesting data from ".concat(url));
        Toast.makeText(this, "Requesting data", Toast.LENGTH_SHORT).show();
        return new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.v("info", "Response is " + response.toString());
                            displayFantasyData(response);
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

    private void displayFantasyData(JSONObject response) throws JSONException {
        Fantasy fantasy = new Fantasy();
        String fantasyDataType = getPreferences(MODE_PRIVATE).getString("fantasyDataType", "");
        if (fantasyDataType.isEmpty()) {
            Log.e("Error", "Fantasy data type not set!");
            return;
        }

        if (fantasyDataType.equals("scoringleaders")) {
            String message = "Displaying scoring leaders...";
            Log.v("Info", message);
            displayScoringLeaders(response, fantasy);
        } else if (fantasyDataType.equals("editorweekranks")) {
            String message = "Displaying player rankings...";
            Log.v("info", message);
            displayPlayerRankings(response, fantasy);
        } else if (fantasyDataType.equals("news")) {
            String message = "Displaying player news...";
            Log.v("info", message);
            displayPlayerNews(response, fantasy);
        }
    }

    private void displayPlayerRankings(JSONObject response, Fantasy fantasy) throws JSONException {
        ArrayList<RankedLeader> rankedLeaders = fantasy.formatRankedLeaders(response);
        Log.v("Ranked Leaders", "Ranked Leaders are: " + rankedLeaders);
        PlayerRankingsAdapter rankedLeadersListAdapter = new PlayerRankingsAdapter(MainActivity.this, rankedLeaders);
        ListView rankedLeadersListView = (ListView) findViewById(R.id.ranked_leaders_list_view);
        rankedLeadersListView.setAdapter(rankedLeadersListAdapter);
    }

    private void displayScoringLeaders(JSONObject response, Fantasy fantasy) throws JSONException {
        String fantasyPosition = getPreferences(MODE_PRIVATE).getString("fantasyPosition","");
        ArrayList<ScoringLeader> scoringLeaders = fantasy.formatScoringLeaders(response, fantasyPosition);
        Log.v("Scoring Leaders", "Scoring Leaders are: " + scoringLeaders);
        ScoringLeadersAdapter scoringLeaderListAdapter = new ScoringLeadersAdapter(MainActivity.this, scoringLeaders);
        ListView scoringLeadersListView = (ListView) findViewById(R.id.scoring_leaders_list_view);
        scoringLeadersListView.setAdapter(scoringLeaderListAdapter);
    }

    private void displayPlayerNews(JSONObject response, Fantasy fantasy) throws JSONException {
        final ArrayList<PlayerNewsItem> playerNews = fantasy.formatPlayerNews(response);
        Log.v("Player News", "Player news is: " + playerNews);
        final PlayerNewsAdapter playerNewsAdapter = new PlayerNewsAdapter(MainActivity.this, playerNews);
        RecyclerView playerNewsRecyclerView = (RecyclerView) findViewById(R.id.player_news_recycler_view);

        RecyclerView.LayoutManager playerNewsLayoutManager = new LinearLayoutManager(MainActivity.this);
        playerNewsRecyclerView.setLayoutManager(playerNewsLayoutManager);
        playerNewsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        playerNewsRecyclerView.setAdapter(playerNewsAdapter);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

                playerNews.remove(viewHolder.getAdapterPosition());
                playerNewsAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }

            @Override
            public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int fromPos, RecyclerView.ViewHolder target, int toPos, int x, int y) {
                super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(playerNewsRecyclerView);
    }

    public void getFantasyData(String api, int count) {
        String fantasyDataType = getPreferences(MODE_PRIVATE).getString("fantasyDataType", "");
        String fantasyPosition = getPreferences(MODE_PRIVATE).getString("fantasyPosition","");
        String url = new StringBuilder(api)
                .append("/players/")
                .append(fantasyDataType)
                .append("?format=json&sort=pts&count=")
                .append(count)
                .append("&position=")
                .append(fantasyPosition)
                .toString();

        JsonObjectRequest jsObjRequest = initializeLeaders(url);
        RequestSingleton.getInstance(this).addToRequestQueue(jsObjRequest);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        String fantasyPosition = parent.getItemAtPosition(pos).toString();
        // save leader position to private state
        getPreferences(MODE_PRIVATE).edit().putString("fantasyPosition", fantasyPosition).apply();
        final Button button = (Button) findViewById(R.id.get_leaders_button_id);
        button.setOnClickListener(this);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public void onClick(View view) {
        // Code here executes on main thread after user presses button
        // send HTTP request to NFL API for leaders leaders
        String api = getResources().getString(R.string.api);
        getFantasyData(api, 25);
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
                replaceWithScoringLeadersFragment();
                break;
            case R.id.get_player_rankings_menu_item:
                replaceWithPlayerRankingsFragment();
                break;
            case R.id.get_player_news_menu_item:
                replaceWithPlayerNewsFragment();
                break;

        }

        drawer.closeDrawers();

        return true;
    }

    private void replaceWithPlayerNewsFragment() {
        Log.v("info", "Replacing current fragment with PlayerNewsFragment");
        PlayerNewsFragment playerNewsFragment = new PlayerNewsFragment();
        playerNewsFragment.setArguments(getIntent().getExtras());
        replaceFantasyFragment(playerNewsFragment);
    }

    private void replaceWithPlayerRankingsFragment() {
        Log.v("info", "Replacing current fragment with PlayerRankingsFragment");
        PlayerRankingsFragment playerRankingsFragment = new PlayerRankingsFragment();
        playerRankingsFragment.setArguments(getIntent().getExtras());
        replaceFantasyFragment(playerRankingsFragment);
    }

    private void replaceWithScoringLeadersFragment() {
        Log.v("info", "Replacing current fragment with ScoringLeadersFragment");
        ScoringLeadersFragment scoringLeadersFragment = new ScoringLeadersFragment();
        scoringLeadersFragment.setArguments(getIntent().getExtras());
        replaceFantasyFragment(scoringLeadersFragment);
    }

    private void replaceFantasyFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .addToBackStack("previousLeadersFragment")
                .replace(R.id.fantasy_fragment_container, fragment)
                .commit();
        //  Intent intent = new Intent(MainActivity.this, RankedLeadersActivity.class);
        //   startActivity(intent);
        //    this.overridePendingTransition(0, 0);
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
