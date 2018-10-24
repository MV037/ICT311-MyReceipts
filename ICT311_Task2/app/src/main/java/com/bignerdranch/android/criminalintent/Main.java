package com.bignerdranch.android.criminalintent;

import java.util.Date;
import java.util.UUID;

public class Main {

    private UUID mId;
    private String mTitle;
    private String mComments;
    private Date mDate;
    private String mShop;
    private boolean mSolved;
    private String mLat;
    private String mLng;
    private String mLatLon;

    public Main(){
        this(UUID.randomUUID());
    }
    public Main(UUID id) {
        mId = id;
        mDate = new Date();

    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getShopname() {
        return mShop;
    }

    public String getComments() {
        return mComments;
    }

    public String getLat(){
        return mLat;
    }

    public String getLng(){
        return mLng;
    }

    public void setLat(String Lat){
        mLat = "Lat: " + Lat;
    }

    public void setLng(String Lng){
        mLng = "Lon: " + Lng;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
    public void setShopname(String title) {
        mShop = title;
    }
    public void setComments(String title) {
        mComments = title;
    }
    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }
    public String getPhotoFilename() {
        return "IMG_" + getId().toString() + ".jpg";
    }

}
