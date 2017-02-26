package com.margi.task9_shayarifragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Margi on 24-Feb-17.
 */
public class CustomGridViewAdapter extends ArrayAdapter<Post> {
    private Context context;
    private int layoutResourceId;
    private ArrayList<Post> arrayList = new ArrayList<Post>();
    private Fragment getquoteFrag;
    private int imgs[];


    public CustomGridViewAdapter(Context context, int layoutResourceId, ArrayList<Post> arrayList, int[] imgs) {
        super(context, layoutResourceId, arrayList);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.arrayList = arrayList;
        this.imgs = imgs;

    }

    static class ViewHolder {
        TextView id, name;
        ImageView imageView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {


        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(layoutResourceId, parent, false);
            holder.imageView = (ImageView) convertView.findViewById(R.id.iv_id);
            holder.id = (TextView) convertView.findViewById(R.id.tv_data_id);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Post post = arrayList.get(position);
        holder.imageView.setImageResource(imgs[position]);
        //holder.name.setText(post.getName());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                getquoteFrag = new ListFragment1();

                FragmentManager quote_fm = ((MainActivity)context).getSupportFragmentManager();
                FragmentTransaction quote_ft = quote_fm.beginTransaction();
                bundle.putInt("pos", post.getId());
                getquoteFrag.setArguments(bundle);
                quote_ft.replace(R.id.linear, getquoteFrag);
                quote_ft.addToBackStack(null);
                quote_ft.commit();
            }
        });
        return convertView;
    }
}



