package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
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

/**
 * {@link QuakeAdapter} is an {@link android.widget.ArrayAdapter} that can provide the layout for each list item
 * based on a data source, which is a list of {@link Quake} objects.
 */

public class QuakeAdapter extends ArrayAdapter<Quake>{
    /** Create a new {@link QuakeAdapter} object.
     *
     * @param context is the current contect (i.e. Activity) that the adapter is being created in.
     * @param earthquakes is the list of {@link Quake}s to be displayed.
     */
    public QuakeAdapter(Context context, ArrayList<Quake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check is an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get the {@link Quake} object located at this position in the list
        Quake currentQuake = getItem(position);

        //Find the TextView in the list_item.xml layout with the ID magnitude_text_view.
        TextView magnitudeTextView = listItemView.findViewById(R.id.magnitude_text_view);
        // Format the magnitude to show 1 decimal place
        String formattedMagnitude = formatMagnitude(currentQuake.getQuakeMagnitude());
        // Get the magnitude from the currentQuake object and set this text on the
        // magnitudeTextView.
        magnitudeTextView.setText(formattedMagnitude);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentQuake.getQuakeMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        String quakeOffset = "Can't find offset";
        String quakeNearestLocation = "Can't find location";


        String fullLocation = currentQuake.getQuakeLocation();
        if(fullLocation.contains("of")){
            String[] parts = fullLocation.split("(?<=of )");
            quakeOffset = parts[0];
            quakeNearestLocation = parts[1];
        } else {
            quakeOffset = "Near the";
            quakeNearestLocation = fullLocation;
        }


        // Find the TextView in the list_item.xml layout with the ID location_text_view.
        TextView offsetTextView = listItemView.findViewById(R.id.offset_text_view);
        // Get the location from the currentQuake object and set this text on the
        // locationTextView.
        offsetTextView.setText(quakeOffset);

        // Find the TextView in the list_item.xml layout with the ID location_text_view.
        TextView locationTextView = listItemView.findViewById(R.id.location_text_view);
        // Get the location from the currentQuake object and set this text on the
        // locationTextView.
        locationTextView.setText(quakeNearestLocation);

        // Create a new Date object from the time in miliseconds of the earthquake
        Date dateObject = new Date(currentQuake.getQuakeDate());

        // Find the TextView in the list_item.xml layout with the ID date_text_view.
        TextView dateTextView = listItemView.findViewById(R.id.date_text_view);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        dateTextView.setText(formattedDate);

        // Find the TextView in the list_item.xml layout with the ID time_text_view.
        TextView timeTextView = listItemView.findViewById(R.id.time_text_view);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeTextView.setText(formattedTime);

        return listItemView;
    }

    /**
     * A method to decide on the color of the magnitude circle based on how great the magnitude is
     * @param quakeMagnitude is a double of the magnitude of the currentQuake
     * @return magnitudeColorResourceId is the color resource id that best matches the magnitude of the quake
     */
    private int getMagnitudeColor(double quakeMagnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(quakeMagnitude);

        switch(magnitudeFloor) {
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
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double quakeMagnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(quakeMagnitude);
    }

    /**
     * Return the formatted date string (i.e. "Mar 3 , 1984") from a Date object
     *
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     *  Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

}
