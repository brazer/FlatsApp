package by.onliner.flatsapp.utils;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.StringRes;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

import by.onliner.flatsapp.R;

public class RoomParser {

    //for testing in JVM
    public static String getRooms(String roomType) {
        if (roomType.equals("room"))
            return "Комната";
        String numberStr = roomType.replaceAll("[^0-9]+", " ").trim();
        int number;
        try {
            number = Integer.parseInt(numberStr);
        } catch (Exception e) {
            Log.e("RoomParser", "Problem with " + roomType, e);
            return "";
        }
        if (number == 1)
            return "Однокомнатная";
        else if (number == 2)
            return "Двухкомнатная";
        else if (number == 3)
            return "Трехкомнатная";
        else if (number == 4)
            return "Четырехкомнатная";
        else if (number == 5)
            return "Пятикомнатная";
        else {
            return numberStr + "-комнатная";
        }
    }

    public static String getRooms(String roomType, Resources resources) {
        if (roomType.equals("room"))
            return resources.getString(R.string.room);
        String numberStr = roomType.replaceAll("[^0-9]+", " ").trim();
        int number;
        try {
            number = Integer.parseInt(numberStr);
        } catch (Exception e) {
            Log.e("RoomParser", "Problem with " + roomType, e);
            return "";
        }
        if (number == 1)
            return resources.getString(R.string.one_room);
        else if (number == 2)
            return resources.getString(R.string.two_room);
        else if (number == 3)
            return resources.getString(R.string.three_room);
        else if (number == 4)
            return resources.getString(R.string.four_room);
        else if (number == 5)
            return resources.getString(R.string.five_room);
        else {
            return String.format(resources.getString(R.string.n_room), numberStr);
        }
    }

}
