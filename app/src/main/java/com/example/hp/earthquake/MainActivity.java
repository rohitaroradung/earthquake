package com.example.hp.earthquake;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.content.AsyncTaskLoader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<earthquake>> {
    /** URL for earthquake data from the USGS dataset */
    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
   private static final String LOG_TAG = MainActivity.class.getName();
    /**
     * URL for earthquake data from the USGS dataset
     */

  private static int  m = 6;
    private static  String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query";
    earthquakeadapter mAdapter;

    @NonNull
    @Override
    public Loader<List<earthquake>> onCreateLoader(int i, @Nullable Bundle bundle) {
        Log.i(LOG_TAG,"createLoader");
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String minMagnitude = sharedPrefs.getString(
                getString(R.string.settings_min_magnitude_key),
                getString(R.string.settings_min_magnitude_default));

        String orderBy = sharedPrefs.getString(
                getString(R.string.settings_order_by_key),
                getString(R.string.settings_order_by_default)
        );

        Uri baseUri = Uri.parse(USGS_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("format", "geojson");
        uriBuilder.appendQueryParameter("limit", "10");
        uriBuilder.appendQueryParameter("minmag", minMagnitude);
        uriBuilder.appendQueryParameter("orderby", orderBy);
        USGS_REQUEST_URL = uriBuilder.toString();
        return new earthquakeloader(this,USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<earthquake>> loader, List<earthquake> data) {
        // Clear the adapter of previous earthquake data
        Log.i(LOG_TAG,"onloadfinished");
        final ArrayList<earthquake> data2 = (ArrayList<earthquake>)data;
         mAdapter = new earthquakeadapter(MainActivity.this,data2);

        ListView listview = (ListView)findViewById(R.id.list);


        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        listview.setAdapter(mAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<earthquake> earthquakes = data2;
                earthquake ear = earthquakes.get(position);
                webview.changeurl(ear.getUrl(),ear.getTitle());
                openWebPage(ear.getUrl());


            }
        });

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<earthquake>> loader) {
        Log.i(LOG_TAG,"loader reset");
        mAdapter = null;

    }



    public void openWebPage(String url) {
        Intent intent = new Intent(this,webview.class);
        startActivity(intent);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportLoaderManager().initLoader(1,null,this).forceLoad();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, settings.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




}