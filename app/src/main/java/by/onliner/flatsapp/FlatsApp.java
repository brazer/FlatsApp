package by.onliner.flatsapp;

import android.app.Application;

import by.onliner.flatsapp.network.API;

/**
 * Created by Admin on 03.11.2016.
 */

public class FlatsApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        API.initialize();
    }
}
