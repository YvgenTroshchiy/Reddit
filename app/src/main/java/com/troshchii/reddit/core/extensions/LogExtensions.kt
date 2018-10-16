package com.troshchii.reddit.core.extensions

import android.util.Log
import com.troshchii.reddit.BuildConfig


val production = !BuildConfig.DEBUG

inline fun <reified T : Any> getLogTag(): String = T::class.java.simpleName

fun Any.logI(tag: String = getLogTag(), text: String) {
    if (production) return
    Log.i(tag, text)
}

private fun Any.getLogTag() = this::class.java.simpleName

fun Any.logD(tag: String = getLogTag(), text: String) {
    if (production) return
    Log.d(tag, text)
}

fun Any.logW(tag: String = getLogTag(), text: String) {
    if (production) return
    Log.w(tag, text)
}

fun Any.logW(tag: String = getLogTag(), throwable: Throwable?) {
    if (production) return
    Log.w(tag, throwable)
}

fun Any.logW(tag: String = getLogTag(), text: String, throwable: Throwable?) {
    if (production) return
    Log.w(tag, text, throwable)
}

fun Any.logE(tag: String = getLogTag(), text: String) {
    if (production) return
    Log.e(tag, text)
}

fun Any.logE(tag: String = getLogTag(), text: String, t: Throwable) {
    if (production) return
    Log.e(tag, text, t)
}