package com.example.sakshi.jsonparsingmovies_acad;

import android.content.Context;
import android.os.AsyncTask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by sakshi on 8/5/2017.
 */

public class DataProcess extends AsyncTask<Void,Void,String> {

    private Context context;
    private String url;
    private DataListener dataListener;
    //parameterized constructor
    public DataProcess(Context context, String url, DataListener dataListener) {
        this.context = context;
        this.url = url;
        this.dataListener = dataListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params) {
        //creating httpclient
        OkHttpClient okHttpClient = new OkHttpClient();
        //setting timeout time limit
        okHttpClient.setReadTimeout(120, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(120,TimeUnit.SECONDS);
        //Requesting url builder
        Request request = new Request.Builder().url(url).build();
        String responsedata = null;
        try {
            //response of the httpclient
            Response response = okHttpClient.newCall(request).execute();
            if(response.isSuccessful()){
                //setting response data if response is successful
                responsedata = response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
            //returning response data
        return responsedata;
    }

    @Override
    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);
        //calling update list method
        dataListener.updatelist(aVoid);

    }
}
