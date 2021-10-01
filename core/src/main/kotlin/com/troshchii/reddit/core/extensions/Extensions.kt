package com.troshchii.reddit.core.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.WorkerThread
import androidx.core.content.res.use
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

private const val TAG = "Extension"

fun Context.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, text, duration).show()

fun Context.inflater(): LayoutInflater = LayoutInflater.from(this)

fun ViewGroup.inflate(
    layoutId: Int, attachToRoot: Boolean = false
): View = context.inflater().inflate(layoutId, this, attachToRoot)

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

inline fun <reified T : Activity> Context.withArgs(vararg params: Pair<String, String>) {
    val intent = Intent(this, T::class.java).apply {
        params.forEach { putExtra(it.first, it.second) }
    }
    startActivity(intent)
}

inline fun <T : Fragment> T.withArgs(argsBuilder: Bundle.() -> Unit): T {
    return apply {
        arguments = Bundle().apply(argsBuilder)
    }
}

fun ImageView.setImageUrl(url: String?) {
    url?.let {
        Glide.with(context)
            .load(it)
//            .skipMemoryCache(true)
            .apply(RequestOptions.noTransformation())
            .into(this)
    } ?: run {
        // TODO: Show placeholder
    }
}

/**
 * Retrieve a color from the current [android.content.res.Resources.Theme].
 */
@ColorInt
@SuppressLint("Recycle")
fun Context.themeColor(
    @AttrRes themeAttrId: Int
): Int {
    return obtainStyledAttributes(
        intArrayOf(themeAttrId)
    ).use {
        it.getColor(0, Color.MAGENTA)
    }
}