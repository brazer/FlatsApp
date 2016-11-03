package by.onliner.flatsapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 03.11.2016.
 */

public class Location {

    private String address;

    @SerializedName("user_address")
    private String userAddress;

    private double latitude;

    private double longitude;

    public String getAddress() {
        return address;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
