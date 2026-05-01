/*
 * SPDX-FileCopyrightText: 2023 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.nothing.thirdparty

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast


public class GlyphService : Service() {
    private val TAG = "GlyphAdapter"

    override fun onCreate() {
        Log.d(TAG, "onCreate")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand")
        Toast.makeText(this, "STARTED", Toast.LENGTH_SHORT).show()

        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.d(TAG, "BOUND: $intent")
        Toast.makeText(this, "BOUND: ${intent.extras}", Toast.LENGTH_SHORT).show()

        return IGlyphServiceImpl(this);
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        Toast.makeText(this, "DONE", Toast.LENGTH_SHORT).show()
    }
}