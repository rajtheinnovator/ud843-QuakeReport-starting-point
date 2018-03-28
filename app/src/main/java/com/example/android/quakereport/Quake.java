package com.example.android.quakereport;

/**
 * {@link Quake} represents details about a particular earthquake.
 * It contains magnitude, location and date for that earthquake.
 */

public class Quake {

    /** Magnitude of the quake */
    private double quakeMagnitude;

    /** Location of the quake */
    private String quakeLocation;

    /** Date of the quake */
    private long timeInMilliseconds;

    /** Url of the quake */
    private String quakeUrl;

    /**
     * Crate a new Quake object
     *
     * @param quake_magnitude is the magnitude of the earthquake
     *
     * @param quake_location is the location of the earthquake
     *
     * @param time_in_milliseconds is the date the earthquake happened
     *
     * @param quake_url is the web-link to the page containing info on this quake
     */
    public Quake(double quake_magnitude, String quake_location, long time_in_milliseconds, String quake_url){
        quakeMagnitude = quake_magnitude;
        quakeLocation = quake_location;
        timeInMilliseconds = time_in_milliseconds;
        quakeUrl = quake_url;
    }

    /** Get the magnitude of the quake */
    public double getQuakeMagnitude() {return quakeMagnitude;}

    /** Get the location fo the quake */
    public String getQuakeLocation() {return quakeLocation;}

    /** Get the date the quake happened */
    public long getQuakeDate() {return timeInMilliseconds;}

    /** Get the url of the quake */
    public String getQuakeUrl() {return quakeUrl;}
}
