package com.vincentvandintel.fantasyaggregator.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vincentvandintel.fantasyaggregator.R;

/**
 * Created by vvand on 9/25/2017.
 */

public class PlayerNewsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.player_news_fragment, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.v("info", "Destroying fragment PlayerRankingsFragment");
    }
}
