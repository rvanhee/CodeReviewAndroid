package com.codereview.remi.codereview.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codereview.remi.codereview.R;

/**
 * Created by Remi on 10/9/15.
 */
public class ExpandableListViewContainer extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_expandable_list_view, container, false);

        return v;
    }
}
