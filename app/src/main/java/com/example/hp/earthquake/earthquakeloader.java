package com.example.hp.earthquake;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

public class earthquakeloader extends AsyncTaskLoader<List<earthquake>> {
    String url;
    earthquakeloader(Context context,String url)
    {
     super(context);
     this.url = url;
    }

    @Nullable
    @Override
    public List<earthquake> loadInBackground() {
        ArrayList<earthquake> earthquakes;
        try {
            earthquakes = QueryUtils.extractEarthquakes(url);
        }
        catch (Exception e)
        {
            return null;
        }
        return earthquakes;
    }

    /*@Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }*/
}

