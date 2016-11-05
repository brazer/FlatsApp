package by.onliner.flatsapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Apartment implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeParcelable(this.price, flags);
        dest.writeString(this.rentType);
        dest.writeParcelable(this.location, flags);
        dest.writeString(this.photo);
        dest.writeParcelable(this.contact, flags);
        dest.writeLong(this.createdAt != null ? this.createdAt.getTime() : -1);
        dest.writeLong(this.lastTimeUp != null ? this.lastTimeUp.getTime() : -1);
        dest.writeInt(this.upAvailableIn);
        dest.writeString(this.url);
    }

    public Apartment() {
    }

    protected Apartment(Parcel in) {
        this.id = in.readInt();
        this.price = in.readParcelable(Price.class.getClassLoader());
        this.rentType = in.readString();
        this.location = in.readParcelable(Location.class.getClassLoader());
        this.photo = in.readString();
        this.contact = in.readParcelable(Contact.class.getClassLoader());
        long tmpCreatedAt = in.readLong();
        this.createdAt = tmpCreatedAt == -1 ? null : new Date(tmpCreatedAt);
        long tmpLastTimeUp = in.readLong();
        this.lastTimeUp = tmpLastTimeUp == -1 ? null : new Date(tmpLastTimeUp);
        this.upAvailableIn = in.readInt();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<Apartment> CREATOR = new Parcelable.Creator<Apartment>() {
        @Override
        public Apartment createFromParcel(Parcel source) {
            return new Apartment(source);
        }

        @Override
        public Apartment[] newArray(int size) {
            return new Apartment[size];
        }
    };
}
