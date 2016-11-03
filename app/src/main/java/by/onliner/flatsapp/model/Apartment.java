package by.onliner.flatsapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Admin on 03.11.2016.
 */

public class Apartment {

    private int id;

    private Price price;

    @SerializedName("rent_type")
    private String rentType;

    private Location location;

    private String photo;

    private Contact contact;

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("last_time_up")
    private Date lastTimeUp;

    @SerializedName("up_available_in")
    private int upAvailableIn;

    private String url;

    public int getId() {
        return id;
    }

    public Price getPrice() {
        return price;
    }

    public String getRentType() {
        return rentType;
    }

    public Location getLocation() {
        return location;
    }

    public String getPhoto() {
        return photo;
    }

    public Contact getContact() {
        return contact;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getLastTimeUp() {
        return lastTimeUp;
    }

    public int getUpAvailableIn() {
        return upAvailableIn;
    }

    public String getUrl() {
        return url;
    }

    public class Contact {

        private boolean owner;

        public boolean isOwner() {
            return owner;
        }
    }

}
