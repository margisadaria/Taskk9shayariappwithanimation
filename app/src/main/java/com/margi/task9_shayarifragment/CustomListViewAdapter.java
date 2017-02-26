package com.margi.task9_shayarifragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Margi on 24-Feb-17.
 */
public class CustomListViewAdapter extends ArrayAdapter
{
    private Fragment bigQuote;

    Context con;
    int layoutResourceId;
    ArrayList<Post2> data = new ArrayList<>();

    public CustomListViewAdapter(Context context, int resource, ArrayList<Post2> objects) {
        super(context, resource, objects);
        this.layoutResourceId = resource;
        this.con = context;
        this.data = objects;
    }

    static class ViewHolder
    {
        TextView id, cat_id, quotes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(con).inflate(layoutResourceId, parent, false);

            holder.id = (TextView)convertView.findViewById(R.id.tv_id);
            holder.cat_id = (TextView)convertView.findViewById(R.id.tv_cat_id);
            holder.quotes = (TextView)convertView.findViewById(R.id.tv_quote_id);

            convertView.setTag(holder);
        }

        else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Post2 p = data.get(position);
        holder.quotes.setText(p.getQuote());

        holder.quotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bigQuote = new ShareFragment();

                FragmentManager fm = ((MainActivity)con).getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                bundle.putString("Quote", p.getQuote());
                bigQuote.setArguments(bundle);
                ft.replace(R.id.linear, bigQuote);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return convertView;
    }
}


