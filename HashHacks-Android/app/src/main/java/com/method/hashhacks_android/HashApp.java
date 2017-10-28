package com.method.hashhacks_android;

import android.app.Application;

/**
 * Created by piyush0 on 28/10/17.
 */

public class HashApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/" + FontsOverride.FONT_PROXIMA_NOVA);
    }
}
