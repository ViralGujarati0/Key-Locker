package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.model.cardddd;
import com.example.myapplication.model.passworddd;

import java.util.ArrayList;

public class CustomCardAdapter extends BaseAdapter {

    Context context;
    ArrayList<cardddd> arrayList;

    CustomCardAdapter(Context context,ArrayList<cardddd> arrayList)
    {
        this.context = context;
        this.arrayList=arrayList;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.card_row,null);
        TextView title = (TextView) convertView.findViewById(R.id.txt_title_card);
        cardddd cr = arrayList.get(position);
        title.setText(cr.getTitle());
        return convertView;
    }

}
