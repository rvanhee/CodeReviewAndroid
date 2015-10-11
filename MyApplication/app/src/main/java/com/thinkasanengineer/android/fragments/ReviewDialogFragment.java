package com.thinkasanengineer.android.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codereview.remi.codereview.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Remi on 10/10/15.
 */
public class ReviewDialogFragment extends DialogFragment {

    @Bind(R.id.reviewLineTitle)
    TextView titleView;

    private String line;
    private String lineNb;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater i = getActivity().getLayoutInflater();
        View v = i.inflate(R.layout.dialog_fragment_review, null);
        ButterKnife.bind(this, v);

        line = getArguments().getString("line");
        lineNb = getArguments().getString("lineNb");
        titleView.setText(lineNb + ".   " + line);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // do something...
                            }
                        }
                )
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        }
                );

        builder.setView(v);

        return builder.create();

    }
}
