package com.vincentvandintel.fantasyaggregator.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vincentvandintel.fantasyaggregator.R;
import com.vincentvandintel.fantasyaggregator.model.PlayerNewsItem;
import com.vincentvandintel.fantasyaggregator.viewHolder.PlayerNewsCardViewHolder;

import java.util.ArrayList;

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
        Log.v("info", "Player news in onBindViewHolder is".concat(playerNewsItems.toString()));
        Log.v("CardPosition", "Card position is ".concat(Integer.toString(cardPosition)));
        PlayerNewsItem player = playerNewsItems.get(cardPosition);
        String name = player.getFirstName().concat(" ").concat(player.getLastName());
        String info = player.getPosition().concat(", ").concat(player.getTeamAbbr()).concat("\n");
//        String time = player.getTimestamp();
        String content = player.getBody().concat("\n\n").concat(player.getAnalysis());

        holder.name.setText(name);
        holder.info.setText(info);
//        holder.time.setText(time);
        holder.content.setText(content);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return playerNewsItems.size();
    }
}