package com.troshchii.reddit.core.extensions

import android.util.Log
import com.troshchii.reddit.BuildConfig


val production = !BuildConfig.DEBUG

inline fun <reified T : Any> getLogTag(): String = T::class.java.simpleName

fun logI(tag: String, text: String) {
    if (production) return
    Log.i(tag, text)
}

fun logD(tag: String, text: String) {
    if (production) return
    Log.d(tag, text)
}

fun logW(tag: String, text: String) {
    if (production) return
    Log.w(tag, text)
}

fun logW(tag: String, throwable: Throwable?) {
    if (production) return
    Log.w(tag, throwable)
}

fun logW(tag: String, text: String, throwable: Throwable?) {
    if (production) return
    Log.w(tag, text, throwable)
}

fun logE(tag: String, text: String) {
    if (production) return
    Log.e(tag, text)
}

fun logE(tag: String, text: String, t: Throwable) {
    if (production) return
    Log.e(tag, text, t)
}