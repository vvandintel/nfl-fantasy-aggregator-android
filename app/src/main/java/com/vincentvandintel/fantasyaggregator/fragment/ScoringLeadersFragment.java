package com.vincentvandintel.fantasyaggregator.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vincentvandintel.fantasyaggregator.R;


/**
 * Created by vvand on 9/17/2017.
 */

public class ScoringLeadersFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.scoring_leaders, container, false);
        return view;
    }
}
