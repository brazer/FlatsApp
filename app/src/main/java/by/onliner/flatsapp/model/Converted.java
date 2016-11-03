package by.onliner.flatsapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 03.11.2016.
 */

public class Converted {

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

    public class Currency {

        private String amount;

        private String currency;

        public String getAmount() {
            return amount;
        }

        public String getCurrency() {
            return currency;
        }
    }

}
