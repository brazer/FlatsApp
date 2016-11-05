package by.onliner.flatsapp.utils;

import android.content.res.Resources;

import java.text.DecimalFormat;

import by.onliner.flatsapp.R;

/**
 * Created by Admin on 05.11.2016.
 */

public class CurrencyBuilder {

    public static String getNewRoubles(String value, Resources resources) {
        double newRoubles = Double.parseDouble(value);
        DecimalFormat newRoublesFormatter = new DecimalFormat("000.00");
        newRoublesFormatter.setMinimumFractionDigits(0);
        newRoublesFormatter.setMinimumIntegerDigits(2);
        return String.format(
                resources.getString(R.string.new_roubles),
                newRoublesFormatter.format(newRoubles)
        );
    }

    public static String getDollarsAndOldRoubles(String dollarVal, String oldRoublesVal, Resources resources) {
        double oldRoubles = Double.parseDouble(oldRoublesVal);
        double dollars = Double.parseDouble(dollarVal);
        DecimalFormat oldRoublesFormatter = new DecimalFormat("00,000.00");
        oldRoublesFormatter.setMinimumFractionDigits(0);
        DecimalFormat dollarFormatter = new DecimalFormat("000.00");
        dollarFormatter.setMinimumFractionDigits(0);
        dollarFormatter.setMinimumIntegerDigits(2);
        return String.format(
                resources.getString(R.string.dollars_and_old_roubles),
                dollarFormatter.format(dollars),
                oldRoublesFormatter.format(oldRoubles)
        );
    }

}
