package by.onliner.flatsapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Converted implements Parcelable {

    private Currency BYR;

    private Currency BYN;

    private Currency USD;

    public Currency getBYR() {
        return BYR;
    }

    public Currency getBYN() {
        return BYN;
    }

    public Currency getUSD() {
        return USD;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.BYR, flags);
        dest.writeParcelable(this.BYN, flags);
        dest.writeParcelable(this.USD, flags);
    }

    public Converted() {
    }

    protected Converted(Parcel in) {
        this.BYR = in.readParcelable(Currency.class.getClassLoader());
        this.BYN = in.readParcelable(Currency.class.getClassLoader());
        this.USD = in.readParcelable(Currency.class.getClassLoader());
    }

    public static final Parcelable.Creator<Converted> CREATOR = new Parcelable.Creator<Converted>() {
        @Override
        public Converted createFromParcel(Parcel source) {
            return new Converted(source);
        }

        @Override
        public Converted[] newArray(int size) {
            return new Converted[size];
        }
    };
}
