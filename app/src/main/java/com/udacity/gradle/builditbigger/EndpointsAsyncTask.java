package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

import info.markovy.jokeandroid.JokeActivity;

class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    public static final String ROOT_URL = "http://10.0.2.2:8080/_ah/api/";
    private static final String TAG = "EndpointsAsyncTask";

    private static MyApi myApiService = null;
    private Context context;
    private CountingIdlingResource espressoIdlingResource;

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        try{
            if(context!= null && ((MainActivity)context).getEspressoIdlingResource() != null){
                Log.d(TAG, "Idling resource given");
                espressoIdlingResource = ((MainActivity) context).getEspressoIdlingResource();
                espressoIdlingResource.increment();
            } else {
                Log.d(TAG, "No Idling resource given");

            }
        } catch (Exception ex) {
            Log.e(TAG, "No idling resource " + ex.getMessage());
            espressoIdlingResource = null;
        }

        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl(ROOT_URL)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0].first;
        String name = params[0].second;

        try {
            return myApiService.sayHi(name).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Intent intent = new Intent(context, JokeActivity.class);
        intent.putExtra(JokeActivity.PARAM_JOKE,result);
        context.startActivity(intent);
        if(espressoIdlingResource!=null){
            espressoIdlingResource.decrement();
            Log.d(TAG, "Idling resource freeing");

        }
      //  Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}