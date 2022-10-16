package com.ntn.findtit;

import android.app.Application;

import com.parse.Parse;

public class AppParse extends Application {
    public static final String PARSE_SERVER = "http://134.122.114.18:1337/parse";
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("findit").clientKey("finditkey")
                .server(PARSE_SERVER )
                .build());
        }
}

