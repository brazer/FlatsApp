package by.onliner.flatsapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Admin on 05.11.2016.
 */

public class Currency implements Parcelable {

    private String amount;

    private String currency;

    public String getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.amount);
        dest.writeString(this.currency);
    }

    public Currency() {
    }

    protected Currency(Parcel in) {
        this.amount = in.readString();
        this.currency = in.readString();
    }

    public static final Creator<Currency> CREATOR = new Creator<Currency>() {
        @Override
        public Currency createFromParcel(Parcel source) {
            return new Currency(source);
        }

        @Override
        public Currency[] newArray(int size) {
            return new Currency[size];
        }
    };
}
