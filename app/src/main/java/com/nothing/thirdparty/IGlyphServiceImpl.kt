package com.nothing.thirdparty

import android.content.Context
import android.util.Log
import java.io.DataInputStream
import java.io.DataOutputStream
import kotlin.math.floor


class IGlyphServiceImpl(private val context: Context) : IGlyphService.Stub() {
    private var TAG = "IGlyphServiceImpl";

    private var su: Process? = null
    private var suOut: DataOutputStream? = null
    private var suIn: DataInputStream? = null;

    private fun cmd(arr: IntArray?): String {
        if (arr == null) return ""

        // The Phone (2) sysfs interface expects space-separated values.
        val values = arr.joinToString(" ")
        return "printf '%s\\n' '$values' > /sys/class/leds/led_strips/frame_brightness\n"
    }

    private fun bindglyphService() {
        Log.i(TAG, "BOUND")
    }

    override fun setFrameColors(iArray: IntArray?) {
        if (iArray == null) return

        // Convert Glyph brightness (0..4096) to LED brightness (0..255).
        val clamped = iArray.map { value ->
            val input = value.coerceIn(0, 4096)
            ((input.toLong() * 255L + 2048L) / 4096L).toInt()
        }.toIntArray()

        suOut?.writeBytes(cmd(clamped))
        suOut?.flush()

        Log.d(TAG, "Frame sent: ${clamped.size} channels")
    }

    override fun openSession() {
        try {
            su = Runtime.getRuntime()
                .exec("su")

            suOut = DataOutputStream(su?.outputStream)
            suIn = DataInputStream(su?.inputStream)

            suOut?.writeBytes("echo 1 > /sys/class/leds/led_strips/always_on\n");
            suOut?.flush();

            suOut?.writeBytes("echo 1 > /sys/class/leds/led_strips/operating_mode\n");
            suOut?.flush();

        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }

        Log.i(TAG, "OPENED")
    }

    override fun closeSession() {
        suOut?.writeBytes("exit\n");
        suOut?.flush();


        suOut?.close()
        su?.destroy();

        Log.i(TAG, "CLOSED")
    }

    override fun register(str: String) = true

    override fun registerSDK(str1: String, str2: String) = true
}