package com.vincentvandintel.fantasyaggregator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.vincentvandintel.fantasyaggregator.R;
import com.vincentvandintel.fantasyaggregator.model.RankedLeader;

import java.util.ArrayList;

/**
 * Created by vvand on 9/11/2017.
 */

public class PlayerRankingsAdapter extends ArrayAdapter<RankedLeader> {
    public PlayerRankingsAdapter(Context context, ArrayList<RankedLeader> rankedLeaders) {
        super(context, 0, rankedLeaders);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        RankedLeader rankedLeader = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.player_rankings_list, parent, false);
        }
        // Lookup view for data population
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView rank = (TextView) convertView.findViewById(R.id.rank);
        // Populate the data into the template view using the data object
        String nameText = new StringBuilder(rankedLeader.getFirstName()).append(" ").append(rankedLeader.getLastName()).toString();
        name.setText(nameText);
        rank.setText(rankedLeader.getRank().toString());

        // Return the completed view to render on screen
        return convertView;
    }
}