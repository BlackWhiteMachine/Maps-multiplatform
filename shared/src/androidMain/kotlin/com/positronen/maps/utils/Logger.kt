package com.positronen.maps.utils

import android.util.Log
import com.positronen.maps.BuildConfig

object Logger {

    private const val TAG = "MapsShared"

    fun debug(message: String, tag: String? = null) {
        if (BuildConfig.DEBUG) {
            message.chunked(4000).forEach {
                Log.d(tag ?: TAG, it)
            }
        }
    }

    fun exception(throwable: Throwable, tag: String? = null) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, throwable.message.toString())
        } else {
            // To analytics
        }
    }
}

actual expect fun debug(message: String, tag: String? = null) {
    Logger.debug(message, tag)
}

actual expect fun exception(throwable: Throwable, tag: String? = null) {
    Logger.exception(throwable, tag)
}