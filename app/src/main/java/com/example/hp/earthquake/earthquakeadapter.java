package com.example.hp.earthquake;
import android.support.v4.content.ContextCompat;


import static java.security.AccessController.getContext;




        import android.app.Activity;
        import android.graphics.drawable.GradientDrawable;
        import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.support.v4.content.ContextCompat;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.TextView;

import com.example.hp.earthquake.earthquake;

import java.util.ArrayList;

public class earthquakeadapter extends ArrayAdapter<earthquake> {
    public earthquakeadapter(Activity context, ArrayList<earthquake> words)
    {
        super(context,0,words);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listview = convertView;
        if(listview == null) {
            listview = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        earthquake ear = getItem(position);
        TextView textView1 = (TextView) listview.findViewById(R.id.mag);
        GradientDrawable magnitudeCircle = (GradientDrawable) textView1.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(ear.getM());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);
        textView1.setText(""+ear.getMag());
        TextView textView2 = (TextView) listview.findViewById(R.id.location1);
        textView2.setText(""+ear.getLocation1());
        TextView textView5 = (TextView) listview.findViewById(R.id.location2);
        textView5.setText(""+ear.getLocation2());
        TextView textView3 = (TextView) listview.findViewById(R.id.date);
        textView3.setText(""+ear.getDate());
        TextView textView4 = (TextView)listview.findViewById(R.id.time);
        textView4.setText(""+ear.getTime());



        return listview;
    }
    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}