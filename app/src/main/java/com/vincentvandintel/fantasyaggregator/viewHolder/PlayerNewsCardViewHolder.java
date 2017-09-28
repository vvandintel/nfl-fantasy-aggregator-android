package com.vincentvandintel.fantasyaggregator.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.vincentvandintel.fantasyaggregator.R;

/**
 * Created by vvand on 9/27/2017.
 */

public class PlayerNewsCardViewHolder extends RecyclerView.ViewHolder {
    public TextView name, position, team, time, content;

    public PlayerNewsCardViewHolder(View view) {
        super(view);

        name = (TextView) view.findViewById(R.id.player_news_name);
        position = (TextView) view.findViewById(R.id.player_news_position);
        team = (TextView) view.findViewById(R.id.player_news_team);
        time = (TextView) view.findViewById(R.id.player_news_time);
        content = (TextView) view.findViewById(R.id.player_news_content);
    }
}
