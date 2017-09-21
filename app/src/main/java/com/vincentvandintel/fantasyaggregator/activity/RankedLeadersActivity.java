package com.vincentvandintel.fantasyaggregator.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.vincentvandintel.fantasyaggregator.R;

public class RankedLeadersActivity extends AppCompatActivity /* implements NavigationView.OnNavigationItemSelectedListener */ {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranked_leaders);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

//    private void setupNavigationDrawer(Toolbar toolbar) {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
//                RankedLeadersActivity.this,
//                drawer,
//                toolbar,
//                R.string.navigation_drawer_open,
//                R.string.navigation_drawer_close) {
//            @Override
//            public void onDrawerClosed(View v){
//                super.onDrawerClosed(v);
//            }
//
//            @Override
//            public void onDrawerOpened(View v) {
//                super.onDrawerOpened(v);
//            }
//        };
//
//        drawer.addDrawerListener(drawerToggle);
//        drawerToggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.left_drawer);
//        navigationView.setNavigationItemSelectedListener(RankedLeadersActivity.this);
//    }
//
//    @Override
//    public boolean onNavigationItemSelected(MenuItem menuItem) {
//        int menuItemId = menuItem.getItemId();
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//
//        switch (menuItemId){
//            case R.id.get_leaders_menu_item:
//                drawer.closeDrawers();
//                Intent intent = new Intent(RankedLeadersActivity.this, MainActivity.class);
//                startActivity(intent);
//                this.overridePendingTransition(0, 0);
//                break;
//            case R.id.get_player_rankings_menu_item:
//                drawer.closeDrawers();
//                break;
//
//        }
//
//        return true;
//    }

//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }

}
