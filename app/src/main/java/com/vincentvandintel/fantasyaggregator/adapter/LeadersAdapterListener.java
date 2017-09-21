//package com.vincentvandintel.fantasyaggregator.adapter;
//
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.Button;
//
//import com.vincentvandintel.fantasyaggregator.R;
//
///**
// * Created by vvand on 9/14/2017.
// */
//
//public class LeadersAdapterListener implements AdapterView.OnItemSelectedListener {
//    public LeadersAdapterListener() {
//
//    }
//
//    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
//        final String leaderPosition = parent.getItemAtPosition(pos).toString();
//        final Button button = (Button) findViewById(R.id.get_leaders_button_id);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Code here executes on main thread after user presses button
//                // send HTTP request to NFL API for scoring leaders
//                String api = getString(R.string.api);
//                getLeaderData(api, leaderPosition, 5);
//            }
//        });
//    }
//    public void onNothingSelected(AdapterView<?> parent) {
//    }
//}
