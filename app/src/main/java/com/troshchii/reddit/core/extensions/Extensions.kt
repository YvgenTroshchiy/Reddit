package com.troshchii.reddit.core.extensions

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.WorkerThread
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide

private const val TAG = "Extension"

fun Context.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, text, duration).show()

fun Context.inflater(): LayoutInflater = LayoutInflater.from(this)

fun ViewGroup.inflate(
    layoutId: Int, attachToRoot: Boolean = false
): View = LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
    beginTransaction().func().commit()

/** You must call this method on a background thread */
@WorkerThread fun String.getBitmapWithGlide(context: Context): Bitmap? {
    return try {
        Glide.with(context)
            .asBitmap()
            .load(this)
            .submit()
            .get()
    } catch (e: Exception) {
        logE(TAG, "getBitmapWithGlide", e)
        null
    }
}