package com.ntn.findit.app

import android.app.Application
import com.parse.Parse;

class App : Application() {
    companion object {
        const val PARSE_SERVER = "http://134.122.114.18:1337/parse"
    }

    override fun onCreate() {
        super.onCreate()
        Parse.initialize(
            Parse.Configuration.Builder(this).applicationId("findit")
                .clientKey("finditkey")
                .server(PARSE_SERVER)
                .build()
        )

    }
}