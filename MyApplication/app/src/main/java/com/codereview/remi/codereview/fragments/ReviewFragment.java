package com.codereview.remi.codereview.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amazonaws.mobileconnectors.apigateway.ApiClientFactory;
import com.codereview.remi.codereview.R;
import com.thinkasanengineer.clientsdk.CodeReviewMeClient;
import com.thinkasanengineer.clientsdk.model.Empty;

/**
 * Created by Remi on 10/9/15.
 */
public class ReviewFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_review, container, false);


        ApiClientFactory factory = new ApiClientFactory();
        // Create a client.
        final CodeReviewMeClient client = factory.build(CodeReviewMeClient.class);
        //Empty empty = client.codereviewretrieverGet("");


        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.expandableListViewContainer, new ExpandableListViewContainer(),null).commit();

        return v;
    }
}
