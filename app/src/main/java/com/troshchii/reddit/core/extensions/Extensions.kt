package com.troshchii.reddit.core.extensions

import android.content.Context
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


fun setStrictMode() {
    StrictMode.setThreadPolicy(
        StrictMode.ThreadPolicy.Builder()
            .detectAll()
            .penaltyLog()
            .build()
    )
    StrictMode.setVmPolicy(
        StrictMode.VmPolicy.Builder()
            .detectLeakedSqlLiteObjects()
            .detectLeakedClosableObjects()
            .penaltyLog()
            .penaltyDeath()
            .build()
    )
}

fun Context.inflater(): LayoutInflater = LayoutInflater.from(this)

fun ViewGroup.inflate(
    layoutId: Int, attachToRoot: Boolean = false
): View = LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)