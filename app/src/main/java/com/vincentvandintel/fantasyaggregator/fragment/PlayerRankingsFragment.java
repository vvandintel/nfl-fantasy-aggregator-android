package com.vincentvandintel.fantasyaggregator.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vincentvandintel.fantasyaggregator.MainActivity;
import com.vincentvandintel.fantasyaggregator.R;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by vvand on 9/17/2017.
 */

public class PlayerRankingsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.player_rankings_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);

        Log.v("info", "Starting up attached fragment PlayerRankingsFragment");
        MainActivity activity = (MainActivity) getActivity();
        activity.getPreferences(MODE_PRIVATE).edit().putString("fantasyDataType", "editorweekranks").apply();
        activity.initializePositionSpinner();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.v("info", "Destroying fragment PlayerRankingsFragment");
    }
}
