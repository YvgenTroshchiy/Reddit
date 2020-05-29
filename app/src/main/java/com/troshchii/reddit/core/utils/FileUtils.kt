package com.troshchii.reddit.core.utils

import android.content.Context
import android.graphics.Bitmap
import androidx.annotation.WorkerThread
import com.troshchii.reddit.core.extensions.getBitmapWithGlide
import com.troshchii.reddit.core.extensions.logE

private const val TAG = "FileUtils"

/** You must call this method on a background thread */
@WorkerThread fun getBitmapWithGlideAndWriteIt(context: Context, url: String, fileName: String): Boolean {
    val bitmap = url.getBitmapWithGlide(context) ?: return false

    return try {
        writeBitmapToFile(context, fileName, bitmap)
        true
    } catch (e: Exception) {
        logE(TAG, "writeBitmapToFile", e)
        false
    }
}

fun writeBitmapToFile(context: Context, fileName: String, bitmap: Bitmap) {
    context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
        bitmap.compress(Bitmap.CompressFormat.WEBP, 100, it)
    }
}