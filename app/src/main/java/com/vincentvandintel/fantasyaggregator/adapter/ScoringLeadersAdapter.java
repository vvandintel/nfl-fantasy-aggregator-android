package com.vincentvandintel.fantasyaggregator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.vincentvandintel.fantasyaggregator.R;
import com.vincentvandintel.fantasyaggregator.model.ScoringLeader;

import java.util.ArrayList;

/**
 * Created by vvand on 9/11/2017.
 */

public class ScoringLeadersAdapter extends ArrayAdapter<ScoringLeader> {
    public ScoringLeadersAdapter(Context context, ArrayList<ScoringLeader> leaders) {
        super(context, 0, leaders);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ScoringLeader leader = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.scoring_leaders_list, parent, false);
        }
        // Lookup view for data population
        TextView title = (TextView) convertView.findViewById(R.id.scoring_leader_name);
        TextView points = (TextView) convertView.findViewById(R.id.scoring_leader_points);
        TextView stats = (TextView) convertView.findViewById(R.id.scoring_leader_stats);
        TextView game = (TextView) convertView.findViewById(R.id.scoring_leader_game);

        // Populate the data into the template view using the data object
        String titleText = leader.getFirstName()
                .concat(" ")
                .concat(leader.getLastName());
        String gameText = "Game: "
                .concat(leader.getTeamAbbr())
                .concat(" versus ")
                .concat(leader.getOpponentTeamAbbr());;
        String pointsText = leader.getPts();
        String statsText = leader.getStatsLine();

        if (pointsText.equals("false")) pointsText = "0.00";

        title.setText(titleText);
        points.setText(pointsText.concat(" points"));
        stats.setText(statsText);
        game.setText(gameText);

        // Return the completed view to render on screen
        return convertView;
    }
}