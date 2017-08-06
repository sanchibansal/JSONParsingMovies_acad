package com.example.sakshi.jsonparsingmovies_acad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sakshi on 8/6/2017.
 */

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Model> modelArrayList;
    LayoutInflater inflater;

    //parameterized constructor
    public  CustomAdapter(Context context, ArrayList<Model> modelArrayList ){
        this.context = context;
        this.modelArrayList = modelArrayList;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        //returning size of the arraylist
        return modelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        //returning item of the arraylist
        return modelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        //returning position
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //inflating item_row XML
        convertView = inflater.inflate(R.layout.item_row,null);
        // creating textViews and setting Text to it
        TextView name = (TextView)convertView.findViewById(R.id.Moviename);
        name.setText(modelArrayList.get(position).getName());
        TextView votes = (TextView)convertView.findViewById(R.id.votes);
        votes.setText("Votes: "+modelArrayList.get(position).getVote());
        TextView id = (TextView)convertView.findViewById(R.id.id);
        id.setText("ID: "+modelArrayList.get(position).getId());
        //returning view
        return convertView;
    }
}
