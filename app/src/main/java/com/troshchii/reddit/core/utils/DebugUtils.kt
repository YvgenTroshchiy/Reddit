package com.troshchii.reddit.core.utils

import android.os.Looper
import android.os.StrictMode
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread


fun enableStrictMode() {
    StrictMode.setThreadPolicy(
        StrictMode.ThreadPolicy.Builder()
            .detectAll()
            .penaltyDeathOnNetwork()
            .penaltyFlashScreen()
            .penaltyLog().build()
    )

    StrictMode.setVmPolicy(
        StrictMode.VmPolicy.Builder()
            .detectAll()
            .penaltyLog().build()
    )
}

fun isMainThread() = (Looper.myLooper() == Looper.getMainLooper())

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