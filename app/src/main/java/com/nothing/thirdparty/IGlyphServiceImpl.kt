package com.nothing.thirdparty

import android.content.Context
import android.util.Log
import java.io.DataInputStream
import java.io.DataOutputStream


class IGlyphServiceImpl(private val context: Context) : IGlyphService.Stub() {
    private var TAG = "IGlyphServiceImpl";

    private var su: Process? = null
    private var suOut: DataOutputStream? = null
    private var suIn: DataInputStream? = null;

    private fun cmd(arr: IntArray?): String {
        return "echo '" +
                arr.contentToString().removePrefix("[").removeSuffix("]") +
                "' > /sys/class/leds/led_strips/frame_brightness\n"
    }

    private val test = arrayOf(
        625,
        625,
        625,
        1000,
        1000,
        1000,
        1000,
        1000,
        1000,
        1000,
        1000,
        1000,
        1000,
        0,
        0,
        0,
        0,
        0,
        0,
        625,
        625,
        625,
        625,
        625,
        625,
        625,
        625,
        625,
        625,
        625,
        625,
        625,
        625
    ).toIntArray()
    private val off: IntArray = arrayOf(
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0
    ).toIntArray()

    private fun bindglyphService() {
        Log.i(TAG, "BOUND")
    }

    override fun setFrameColors(iArray: IntArray?) {
        val clamped = iArray?.map { b -> (b.toFloat() / 4000 * 1000).toInt() }?.toIntArray()

        suOut?.writeBytes(cmd(clamped));
        suOut?.flush();

        Log.i(TAG, cmd(clamped));

    }

    override fun openSession() {
        try {
            su = Runtime.getRuntime()
                .exec("su")

            suOut = DataOutputStream(su?.outputStream)
            suIn = DataInputStream(su?.inputStream)
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }


        Log.i(TAG, "OPENEND")
    }

    override fun closeSession() {
        suOut?.close()
        su?.destroy();

        Log.i(TAG, "CLOSED")
    }

    override fun register(str: String) = true

    override fun registerSDK(str1: String, str2: String) = true
}