package com.thinkasanengineer.android.webservices;

import android.os.AsyncTask;

import com.amazonaws.mobileconnectors.apigateway.ApiClientFactory;
import com.codereviewme.clientsdk.CodeReviewMeClient;
import com.codereviewme.clientsdk.model.SourceCodeGETResponseModel;

/**
 * Created by Remi on 10/10/15.
 */
public class SourceCodeDownloader extends AsyncTask<Void, Void, String> { //change Object to required type
    private OnSourceCodeDownloaded listener;

    public SourceCodeDownloader(OnSourceCodeDownloaded listener) {
        this.listener = listener;
    }


    protected void onPostExecute(String sourceCode) {
        listener.onSourceCodeDownloaded(sourceCode);
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            ApiClientFactory factory = new ApiClientFactory();

            // Create a client.
            final CodeReviewMeClient client = factory.apiKey("LlKxZxjiTp9PbSL5amWqq0vdjarU39S2bVYa4Uuj").build(CodeReviewMeClient.class);

            SourceCodeGETResponseModel response = client.sourceCodeGet("github", "/auchenberg/chrome-devtools-app/blob/master/app/TargetsCollection.js");
            return response.getSource();

        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


    public interface OnSourceCodeDownloaded {
        void onSourceCodeDownloaded(String sourceCode);
    }
}
