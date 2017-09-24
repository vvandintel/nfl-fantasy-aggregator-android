package com.vincentvandintel.fantasyaggregator.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vincentvandintel.fantasyaggregator.MainActivity;
import com.vincentvandintel.fantasyaggregator.R;


/**
 * Created by vvand on 9/17/2017.
 */

public class PlayerRankingsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ranked_leaders_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        //((YourActivityClassName)getActivity()).yourPublicMethod();
        // (MainActivity)getActivity().init
        MainActivity activity = (MainActivity) getActivity();
        activity.initializePositionSpinner();
    }
}
