package com.vincentvandintel.fantasyaggregator.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vincentvandintel.fantasyaggregator.R;
import com.vincentvandintel.fantasyaggregator.model.PlayerNewsItem;
import com.vincentvandintel.fantasyaggregator.viewHolder.PlayerNewsCardViewHolder;
import com.vincentvandintel.fantasyaggregator.viewModel.PlayerNewsCard;

import java.util.ArrayList;

/**
 * Created by vvand on 9/25/2017.
 */

public class PlayerNewsAdapter extends RecyclerView.Adapter<PlayerNewsCardViewHolder> {
    private ArrayList<PlayerNewsItem> playerNewsItems;

    public PlayerNewsAdapter(ArrayList<PlayerNewsItem> playerNewsItems) {
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
        setText(holder, cardPosition);
    }

    private void setText(PlayerNewsCardViewHolder holder, int cardPosition) {
        PlayerNewsCard playerNewsCard = new PlayerNewsCard(playerNewsItems.get(cardPosition));
        holder.name.setText(playerNewsCard.getName());
        holder.info.setText(playerNewsCard.getInfo());
        holder.content.setText(playerNewsCard.getContent());
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