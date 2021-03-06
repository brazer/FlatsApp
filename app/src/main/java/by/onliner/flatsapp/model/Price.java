package by.onliner.flatsapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Admin on 03.11.2016.
 */

public class Price implements Parcelable {

    private String amount;

    private String currency;

    private Converted converted;

    public String getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public Converted getConverted() {
        return converted;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.amount);
        dest.writeString(this.currency);
        dest.writeParcelable(this.converted, flags);
    }

    public Price() {
    }

    protected Price(Parcel in) {
        this.amount = in.readString();
        this.currency = in.readString();
        this.converted = in.readParcelable(Converted.class.getClassLoader());
    }

    public static final Parcelable.Creator<Price> CREATOR = new Parcelable.Creator<Price>() {
        @Override
        public Price createFromParcel(Parcel source) {
            return new Price(source);
        }

        @Override
        public Price[] newArray(int size) {
            return new Price[size];
        }
    };
}
