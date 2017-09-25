package com.example.android.quakereport;

import static com.example.android.quakereport.R.id.date;

/**
 * Created by enach on 9/11/2017.
 */

public class Earthquake {

    /**Magnitude of the earthquake*/
    private Double mMagnitude;
    /**Location of the earthquake*/
    private String mLocation;
    /**Date of the earthquake*/
    private long mTimeInMilliseconds;
    /**Url of the earthquake*/
    private String mUrl;

    /**
     *
     * @param mag - The intensity of the earthquake
     * @param location - where did it occur
     * @param timeInMilliseconds - when it happened
     * @param url - url for the USGS webiste
     */

    public Earthquake (Double mag, String location, long timeInMilliseconds, String url){
        mMagnitude = mag;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
        mUrl = url;
    }

    /**
     * @return the magnitude
     */

    public Double getMag(){
        return mMagnitude;
    }

    /**
     * @return the location
     */

    public String getLocation(){
        return mLocation;
    }

    /**
     * @return the date
     */

    public long getTimeInMilliseconds(){
        return mTimeInMilliseconds;
    }

    /**
     * return the url
     */
    public String getUrl(){
        return mUrl;
    }
}
