/*
 * SPDX-FileCopyrightText: 2023 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.nothing.thirdparty

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log


public class GlyphService : Service() {
    private val TAG = "GlyphAdapter"

    override fun onCreate() {
        Log.d(TAG, "onCreate")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand")
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder {
        Log.d(TAG, "BOUND: $intent")
        return IGlyphServiceImpl(this);
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
    }
}