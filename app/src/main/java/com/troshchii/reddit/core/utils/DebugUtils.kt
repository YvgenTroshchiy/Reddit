package com.troshchii.reddit.core.utils

import android.os.StrictMode
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread


fun setStrictMode() {
    StrictMode.setThreadPolicy(
        StrictMode.ThreadPolicy.Builder()
            .detectAll()
            .penaltyLog()
            .build()
    )
    StrictMode.setVmPolicy(
        StrictMode.VmPolicy.Builder()
            .detectAll()
            .penaltyLog()
            .build()
    )
}

fun debugDelayAsync(seconds: Long) {
    thread {
        debugDelay(seconds)
    }
}

fun debugDelay(seconds: Long) {
    try {
        TimeUnit.SECONDS.sleep(seconds)
    } catch (e: InterruptedException) {
        e.printStackTrace()
    }
}