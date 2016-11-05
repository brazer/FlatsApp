package by.onliner.flatsapp.utils;

import android.content.res.Resources;

import org.joda.time.DateTime;
import org.joda.time.Period;

import java.util.Date;

import by.onliner.flatsapp.R;

/**
 * Created by Admin on 05.11.2016.
 */

public class DateUtil {

    public static String getTerm(DateTime date, Resources resources) {
        Period period = new Period(date, DateTime.now());
        int months = period.getMonths();
        if (months > 1)
            return resources.getString(R.string.more_month);
        int days = period.toStandardDays().getDays();
        if (days == 0) {
            return resources.getString(R.string.zero_days);
        } else if (days == 1) {
            return resources.getString(R.string.one_days);
        } else if (days < 5) {
            return String.format(resources.getString(R.string.two_four_days), days);
        } else {
            return String.format(resources.getString(R.string.more_four_days), days);
        }
    }

}
