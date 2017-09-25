package com.example.android.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.graphics.drawable.GradientDrawable;

import static android.R.attr.rating;

/**
 * Created by enach on 9/12/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = "of";

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquake) {
        super(context, 0, earthquake);
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);

        switch (magnitudeFloor) {
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

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_items, parent, false);
        }

        /**get the position of each item in the listView*/
        Earthquake currentEarthQuakeAdapter = getItem(position);

        /**Get the original location String from the Earthquake object and store it as a variable*/
        String originalLocation = currentEarthQuakeAdapter.getLocation();
        String primaryLocation;
        String locationOffset;


        /**find the TextView with the ID mag*/
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.mag);
        /**Format the magnitude to show 1 decimal place*/
        String formattedMagnitude = formatMagnitude(currentEarthQuakeAdapter.getMag());
        /**Display the magnitude of the current earthquake*/
        magnitudeTextView.setText(formattedMagnitude);

        /**Create a if statement where I check to see if the String from the Json
         * contains the word "of" a.k.a LOCATION_SEPARATOR and then separate the string
         * If it doesn't contain it, it will use the near_the String from values and
         * display only the last part of the string
         * */
        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        /**find the TextView with the ID primary_location*/
        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.primary_location);
        /**Set the text for the string*/
        primaryLocationView.setText(primaryLocation);

        /**find the TextView with the ID location_offset*/
        TextView locationOffsetView = (TextView) listItemView.findViewById(R.id.location_offset);
        /**Set the text for the string*/
        locationOffsetView.setText(locationOffset);


        /**Create a new Date object from the time im milliseconds of the earthquake*/
        Date dateObject = new Date(currentEarthQuakeAdapter.getTimeInMilliseconds());

        /**find the TextView with the ID date*/
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        /**Format the date string*/
        String formattedDate = formatDate(dateObject);
        /**Display the date of the current earthquake in the TextView*/
        dateTextView.setText(formattedDate);

        /**Find the textView with the ID time*/
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        /**Format the time string*/
        String formattedTime = formatTime(dateObject);
        /**Display the time of the current earthquake in the TextView*/
        timeView.setText(formattedTime);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthQuakeAdapter.getMag());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        return listItemView;
    }

}
