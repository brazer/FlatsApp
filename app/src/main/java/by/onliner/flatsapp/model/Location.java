package by.onliner.flatsapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 03.11.2016.
 */

public class Location implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.address);
        dest.writeString(this.userAddress);
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
    }

    public Location() {
    }

    protected Location(Parcel in) {
        this.address = in.readString();
        this.userAddress = in.readString();
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
    }

    public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel source) {
            return new Location(source);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
}
