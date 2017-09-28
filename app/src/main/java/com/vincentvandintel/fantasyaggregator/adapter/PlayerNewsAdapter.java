package com.vincentvandintel.fantasyaggregator.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vincentvandintel.fantasyaggregator.R;
import com.vincentvandintel.fantasyaggregator.model.PlayerNewsItem;
import com.vincentvandintel.fantasyaggregator.viewHolder.PlayerNewsCardViewHolder;

import java.util.ArrayList;

import static com.vincentvandintel.fantasyaggregator.R.array.position;

/**
 * Created by vvand on 9/25/2017.
 */

public class PlayerNewsAdapter extends RecyclerView.Adapter<PlayerNewsCardViewHolder> {
    private Context context;
    private ArrayList<PlayerNewsItem> playerNewsItems;

    public PlayerNewsAdapter(Context context, ArrayList<PlayerNewsItem> playerNewsItems) {
        this.context = context;
        this.playerNewsItems = playerNewsItems;
    }

    @Override
    public PlayerNewsCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_news_card, parent, false);

        return new PlayerNewsCardViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(PlayerNewsCardViewHolder holder, int cardPosition) {
        PlayerNewsItem player = playerNewsItems.get(position);
        String name = player.getFirstName().concat(" ").concat(player.getLastName());
        String position =  player.getPosition();
        String team = player.getTeamAbbr();
        String time = player.getTimestamp();
        String content = player.getBody().concat(" ").concat(player.getAnalysis());

        holder.name.setText(name);
        holder.position.setText(position);
        holder.team.setText(team);
        holder.time.setText(time);
        holder.content.setText(content);
    }

    @Override
    public int getItemCount() {
        return playerNewsItems.size();
    }
}