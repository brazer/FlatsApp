package by.onliner.flatsapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Admin on 05.11.2016.
 */

public class Contact implements Parcelable {

    private boolean owner;

    public boolean isOwner() {
        return owner;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.owner ? (byte) 1 : (byte) 0);
    }

    public Contact() {
    }

    protected Contact(Parcel in) {
        this.owner = in.readByte() != 0;
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}
