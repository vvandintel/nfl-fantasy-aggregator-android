package com.vincentvandintel.fantasyaggregator.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.vincentvandintel.fantasyaggregator.R;
import com.vincentvandintel.fantasyaggregator.model.Leader;

import java.util.ArrayList;

/**
 * Created by vvand on 9/11/2017.
 */

public class LeadersAdapter extends ArrayAdapter<Leader> {
    public LeadersAdapter(Context context, ArrayList<Leader> leaders) {
        super(context, 0, leaders);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Leader leader = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.leaders_list, parent, false);
        }
        // Lookup view for data population
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView points = (TextView) convertView.findViewById(R.id.pts);
        // Populate the data into the template view using the data object
        String nameText = new StringBuilder(leader.getFirstName()).append(" ").append(leader.getLastName()).toString();
        name.setText(nameText);
        points.setText(leader.getPts());

        // Return the completed view to render on screen
        return convertView;
    }
}