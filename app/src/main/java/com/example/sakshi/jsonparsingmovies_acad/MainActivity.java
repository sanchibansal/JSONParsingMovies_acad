package com.example.sakshi.jsonparsingmovies_acad;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements DataListener {
    //declaring array list, listview , custom adapter and url;
    ListView list;
    ArrayList<Model> modelArrayList;
    CustomAdapter customAdapter;
    String url = "http://api.themoviedb.org/3/tv/top_rated?api_key=8496be0b2149805afa458ab8ec27560c";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //associating listView
        list = (ListView)findViewById(R.id.listview);
        modelArrayList = new ArrayList<>();
        //checking the internet connection
        if(isConnectedToInternet()){
            //calling Async Task
            DataProcess dataProcess = new DataProcess(MainActivity.this, url,this);
            dataProcess.execute();
        }else{
            //displays toast if there is no internet connection
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
        //custom adapter for displaying listitems
        customAdapter = new CustomAdapter(this,modelArrayList);
        //setting adapter to listview
        list.setAdapter(customAdapter);

    }

    private boolean isConnectedToInternet() {
        boolean isconnected = false;
        //connectivity managee for checking the internet connection of the device
        ConnectivityManager connectivityManager = (ConnectivityManager)this.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null){
            isconnected = true;
        }
        return isconnected;
    }

    @Override
    public void updatelist(String data) {
        try {
            //creating jsonObject
            JSONObject jsonObject = new JSONObject(data);
            //getting array from the json object
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            for(int i=0; i<jsonArray.length();i++){
                //getting object i from the JsonArray
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                //getting data from i object in the array
                String name = jsonObject1.getString("name");
                String id = jsonObject1.getString("id");
                String votes = jsonObject1.getString("vote_count");
                Model model = new Model();
                //setting name, votes and id
                model.setId(id);
                model.setName(name);
                model.setVote(votes);
                //adding model to mmodelArrayList
                modelArrayList.add(model);
              }
            customAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
