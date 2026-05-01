package com.nothing.thirdparty

import android.app.Activity
import android.content.ComponentName
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log

public class MainActivity : Activity() {

    private var TAG = "GlyphAdapterActivity"

    inner class SawCon : ServiceConnection {
        override fun onServiceConnected(
            p0: ComponentName?,
            p1: IBinder?
        ) {
            Log.d(TAG, "onConnected")
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            Log.d(TAG, "onDisconnected")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        /*val launchIntent =
            getPackageManager().getLaunchIntentForPackage("com.nothing.glyph.composer");
        try {
            startActivityForResult(launchIntent, 0)
        } catch (e: Exception) {

        }*/
        //startActivityForResult(launchIntent, 0);


        /*launchIntent.setPackage("com.nothing.thirdparty");
        launchIntent.setAction("com.nothing.thirdparty.bind_glyphservice");
        launchIntent.setComponent(
            ComponentName(
                "com.nothing.thirdparty",
                "com.nothing.thirdparty.GlyphService"
            )
        );*/

        // var con = SawCon();
        // bindService(launchIntent, con, 1);
    }
}