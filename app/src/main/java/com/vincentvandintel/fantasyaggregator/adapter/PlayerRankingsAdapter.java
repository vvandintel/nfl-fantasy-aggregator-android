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
        TextView title = (TextView) convertView.findViewById(R.id.title);
//        TextView rank = (TextView) convertView.findViewById(R.id.rank);
        TextView game = (TextView) convertView.findViewById(R.id.game);

        // Populate the data into the template view using the data object
        String titleText = rankedLeader.getRank().toString()
                .concat(" - ")
                .concat(rankedLeader.getFirstName())
                .concat(" ")
                .concat(rankedLeader.getLastName());

        String gameText = "Game: "
                .concat(rankedLeader.getTeamAbbr())
                .concat(" versus ")
                .concat(rankedLeader.getOpponentTeamAbbr());;

        title.setText(titleText);
        // rank.setText(rankedLeader.getRank().toString());
        game.setText(gameText);

        // Return the completed view to render on screen
        return convertView;
    }
}