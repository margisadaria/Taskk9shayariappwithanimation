package com.margi.task9_shayarifragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Margi on 24-Feb-17.
 */
public class ShareFragment extends android.support.v4.app.Fragment
{
    private String id;
    private TextView t1;
    private Button share;
    //private Textview t2;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.lastfragmnet, container, false);
        id = getArguments().getString("Quote");
        t1 = (TextView)view.findViewById(R.id.quote);
        t1.setText(id);

        share = (Button)view.findViewById(R.id.btn_share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, t1.getText());
                startActivity(Intent.createChooser(shareIntent, "Share Via"));
            }
        });

        return view;
    }
}





