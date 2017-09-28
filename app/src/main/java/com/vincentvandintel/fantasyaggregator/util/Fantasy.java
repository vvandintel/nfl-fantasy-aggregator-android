package com.vincentvandintel.fantasyaggregator.util;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vincentvandintel.fantasyaggregator.model.PlayerNewsItem;
import com.vincentvandintel.fantasyaggregator.model.RankedLeader;
import com.vincentvandintel.fantasyaggregator.model.ScoringLeader;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by vvand on 9/16/2017.
 */

public class Fantasy {
    public Fantasy() {
    }

    public ArrayList<ScoringLeader> formatScoringLeaders(JSONObject response, String position) throws JSONException {
        JSONObject positions = response.getJSONObject("positions");
        String positionLeaders = positions.getJSONArray(position).toString();
        Log.v("Position Leaders", "Position Leaders are " + positionLeaders);

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ScoringLeader>>(){}.getType();

        ArrayList<ScoringLeader> leaders = new ArrayList<>();

        try {
            leaders = gson.fromJson(positionLeaders, type);
        } catch (Exception exception) {
            Log.e("Error", exception.getMessage());
        }

        return leaders;
    }

    public ArrayList<RankedLeader> formatRankedLeaders(JSONObject response) throws JSONException {
        //  String playerLeaders = players.getJSONArray(position).toString();
        String players = response.getJSONArray("players").toString();
        Log.v("info", "Player Leaders are " + players);

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<RankedLeader>>(){}.getType();

        ArrayList<RankedLeader> leaders = new ArrayList<>();

        try {
            leaders = gson.fromJson(players, type);
        } catch (Exception exception) {
            Log.e("Error", exception.getMessage());
        }

        return leaders;
    }

    public ArrayList<PlayerNewsItem> formatPlayerNews(JSONObject response) throws JSONException {
        //  String playerLeaders = players.getJSONArray(position).toString();
        String playerNews = response.getJSONArray("news").toString();
        Log.v("info", "Player news is " + playerNews);

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<PlayerNewsItem>>(){}.getType();

        ArrayList<PlayerNewsItem> players = new ArrayList<>();

        try {
            players = gson.fromJson(playerNews, type);
        } catch (Exception exception) {
            Log.e("Error", exception.getMessage());
        }

        return players;
    }
}
