package com.margi.task9_shayarifragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Margi on 24-Feb-17.
 */
public class GridFragment extends android.support.v4.app.Fragment
{
    private View view;
    private GridView gridView;
    private CustomGridViewAdapter adapter;
    private static final String TAG = "JSON Parsing ";

    private int imgs[] = new int[]{
            R.drawable.life,
            R.drawable.valentile,
            R.drawable.love,
            R.drawable.friendship,
            R.drawable.positive,
            R.drawable.funny,
            R.drawable.motivation
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.gridview_fragment, container, false);

        new Category().execute("http://rapidans.esy.es/test/getallcat.php");
        return view;
    }

    class Category extends AsyncTask<String,Void,String> {


        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork == null) {
                try {
                    AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                    alertDialog.setTitle("Info");
                    alertDialog.setMessage("Internet not available, Cross check your internet connectivity and try again");
                    //alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                    alertDialog.show();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }

            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("Please wait while loading...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection;
            try {
                URL url = new URL(params[0]);
                try {
                    connection = (HttpURLConnection)url.openConnection();
                    connection.connect();

                    InputStream stream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuffer buffer = new StringBuffer();
                    String line = "";

                    while ((line =reader.readLine())!= null){
                        buffer.append(line);
                    }

                    String bufferString = buffer.toString();
                    return  bufferString;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            if(dialog.isShowing()) {
                dialog.dismiss();
            }
            ArrayList<Post> arrayList = new ArrayList<Post>();
            super.onPostExecute(s);
            try {
                JSONObject rootObject = new JSONObject(s);
                JSONArray dataObject = rootObject.getJSONArray("data");
                for (int i = 0; i <dataObject.length() ; i++) {

                    JSONObject idObject = dataObject.getJSONObject(i);
                    Post p = new Post();

                    int id = idObject.getInt("id");
                    String name = idObject.getString("name");
                    Log.d(TAG, "ID: "+id);
                    Log.d(TAG, "Name: "+name);

                    p.setId(id);
                    p.setName(name);
                    arrayList.add(p);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            gridView = (GridView)view.findViewById(R.id.grid_id);

            Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.grid_animation);
            gridView.startAnimation(animation);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation)
                {
                    Toast.makeText(getActivity(), "Welcome to Your Quote", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    Toast.makeText(getActivity(), "Welcome to Your Quote", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            adapter = new CustomGridViewAdapter(getActivity(),R.layout.gridview_adapter,arrayList,imgs);
            gridView.setAdapter(adapter);
        }
    }
}

