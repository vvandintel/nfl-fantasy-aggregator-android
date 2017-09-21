package com.vincentvandintel.fantasyaggregator.util;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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

    public ArrayList<ScoringLeader> formatLeaders(JSONObject response, String position) throws JSONException {
        JSONObject positions = response.getJSONObject("positions");
        String positionLeaders = positions.getJSONArray(position).toString();
        Log.v("Position Leaders", "Position Leaders are " + positionLeaders);

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ScoringLeader>>(){}.getType();

        ArrayList<ScoringLeader> leaders = new ArrayList<ScoringLeader>();

        try {
            leaders = gson.fromJson(positionLeaders, type);
        } catch (Exception exception) {
            Log.e("Error", exception.getMessage());
        }

        return leaders;
    }
}
