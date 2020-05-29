package com.troshchii.reddit.core.utils

import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import android.os.Environment.DIRECTORY_DOWNLOADS
import androidx.annotation.WorkerThread
import com.troshchii.reddit.core.extensions.getBitmapWithGlide
import com.troshchii.reddit.core.extensions.logE
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream

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
    //TODO: Use for 29 API
//    val imagesCollection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)

    val externalFilesDir = context.getExternalFilesDir(DIRECTORY_DOWNLOADS)
    val file = File(externalFilesDir, fileName)

    try {
        FileOutputStream(file).use {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
        }
    } catch (e: FileNotFoundException) {
        logE(TAG, "writeBitmapToFile", e)
    }
}