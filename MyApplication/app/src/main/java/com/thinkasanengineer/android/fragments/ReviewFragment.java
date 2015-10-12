package com.thinkasanengineer.android.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codereview.remi.codereview.R;
import com.thinkasanengineer.android.webservices.SourceCodeDownloader;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Remi on 10/9/15.
 */
public class ReviewFragment extends Fragment implements SourceCodeDownloader.OnSourceCodeDownloaded {

    @Bind(R.id.textViewContainer)
    LinearLayout textViewContainer;

    @Bind(R.id.rightPanelButton)
    ImageButton rightPanelButton;

    @Bind(R.id.rightPanel)
    LinearLayout rightPanel;

    @Bind(R.id.rightPanelAction)
    LinearLayout rightPanelAction;

    private boolean isRightPanelVisible = true;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_review, container, false);
        ButterKnife.bind(this, v);

        new SourceCodeDownloader(this).execute();

        initTopPanel();
        initRightPanel();

        return v;
    }


    private void initTopPanel(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.expandableListViewContainer, new ExpandableListViewContainer(), null).commit();
    }

    private void initRightPanel(){

        rightPanelButton.setImageResource(R.drawable.ic_keyboard_arrow_right_black_24dp);
        rightPanelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doRightPanelAction();
            }
        });

    }

    private void doRightPanelAction(){
        if (isRightPanelVisible){
            rightPanel.setVisibility(View.GONE);
            isRightPanelVisible = false;
            rightPanelButton.setImageResource(R.drawable.ic_keyboard_arrow_left_black_24dp);
        } else {
            rightPanel.setVisibility(View.VISIBLE);
            isRightPanelVisible = true;
            rightPanelButton.setImageResource(R.drawable.ic_keyboard_arrow_right_black_24dp);
        }
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

        if (code != null && !code.equals("")) {

            String[] lines = code.split("\n");

            for (int i = 0; i < lines.length; i++) {

                setCodeTextView(i + 1, lines);

                setRightPanelAction(i +1);

                setRightPanel(i +1);

            }

        } else {
            TextView textView = new TextView(getActivity());
            textView.setText("no Data");
            textViewContainer.addView(textView);
        }
    }


    private void setCodeTextView(int lineNb, String[] lines){
        final TextView textView = new TextView(getActivity());
        final String line = lines[lineNb - 1];

        textView.setText(lineNb + ".   " + line);
        textView.setTag(lineNb);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("line", line);
                bundle.putString("lineNb", textView.getTag().toString());

                ReviewDialogFragment reviewDialogFragment = new ReviewDialogFragment();
                reviewDialogFragment.setArguments(bundle);
                reviewDialogFragment.show(getFragmentManager(), null);

            }
        });

        textViewContainer.addView(textView);
    }


    private void setRightPanelAction(int lineNb){
        ImageButton imageButton = new ImageButton(getActivity());
        imageButton.setImageResource(R.drawable.ic_bug_report_black_18dp);

        //TODO: Height should not be set manually. Need to include the image in the line from the left panel
        imageButton.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, 27));
        imageButton.setVisibility(View.INVISIBLE);

        //TODO: remove this. This is just for POC
        if (lineNb == 10 || lineNb == 14 || lineNb == 25){
            imageButton.setVisibility(View.VISIBLE);
        }

        rightPanelAction.addView(imageButton);

    }

    private void setRightPanel(int lineNb){
        final TextView textView = new TextView(getActivity());
        textView.setTextColor(Color.WHITE);
        if (lineNb == 10 || lineNb == 14 || lineNb == 25) {
            textView.setText("This is a comment");
        }

        rightPanel.addView(textView);


    }


    @Override
    public void onSourceCodeDownloaded(String sourceCode) {

        displaySourceCode(sourceCode);
    }


}
