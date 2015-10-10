package com.thinkasanengineer.android.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amazonaws.mobileconnectors.apigateway.ApiClientFactory;
import com.codereview.remi.codereview.R;
import com.codereviewme.clientsdk.CodeReviewMeClient;
import com.codereviewme.clientsdk.model.SourceCodeGETResponseModel;
import com.thinkasanengineer.android.async.SourceCodeDownloader;


import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Remi on 10/9/15.
 */
public class ReviewFragment extends Fragment implements SourceCodeDownloader.OnSourceCodeDownloaded {

    @Bind(R.id.textViewContainer)
    LinearLayout textViewContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_review, container, false);
        ButterKnife.bind(this, v);


       /* ApiClientFactory factory = new ApiClientFactory();
        // Create a client.
        final CodeReviewMeClient client = factory.apiKey("LlKxZxjiTp9PbSL5amWqq0vdjarU39S2bVYa4Uuj").build(CodeReviewMeClient.class);*/

/*        Thread task = new Thread()
        {
            @Override
            public void run()
            {
                try {

                    SourceCodeGETResponseModel response = client.sourceCodeGet("github", "/auchenberg/chrome-devtools-app/blob/master/app/TargetsCollection.js");

                    SourceCodeGETResponseModel.MyObject myObj = response.getData();
                    displaySourceCode(myObj.getSource());


                } catch (Exception e){
                    e.printStackTrace();
                }

            }

        };

        task.start();*/

        new SourceCodeDownloader(this).execute();

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.expandableListViewContainer, new ExpandableListViewContainer(), null).commit();

        return v;
    }


    private void displaySourceCode(String code) {
/*        String code = "function TargetsCollection() {\n    " +
                "this.collection = new Map();\n}\n\nTargetsCollection.prototype.add = function(id, metadata) {\n\n    " +
                "var target = metadata;\n    target.id = id;\n\n    return this.collection.set(id, target);\n}\n\n" +
                "TargetsCollection.prototype.remove = function(key) {\n    return this.collection.delete(key);\n}\n\n" +
                "TargetsCollection.prototype.has = function(key) {\n    return this.collection.has(key);\n}\n\n" +
                "TargetsCollection.prototype.clear = function(){\n    return this.collection.clear();\n}\n\n" +
                "TargetsCollection.prototype.toArray = function() {\n\n    var targets = [];\n\n    " +
                "for (var target of this.collection.values()) {\n        " +
                "targets.push(target);\n    }\n\n    return targets;\n\n}\n\nmodule.exports = TargetsCollection;\n";*/


        String[] lines = code.split("\n");

        for (String line : lines) {
            TextView textView = new TextView(getActivity());
            textView.setText(line);
            textViewContainer.addView(textView);
        }
    }

    @Override
    public void onSourceCodeDownloaded(String sourceCode) {
        displaySourceCode(sourceCode);
    }


}
