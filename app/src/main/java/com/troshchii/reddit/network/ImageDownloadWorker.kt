package com.troshchii.reddit.network

import android.content.Context
import android.os.Environment
import androidx.core.content.ContextCompat.getSystemService
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.troshchii.reddit.core.extensions.getLogTag
import com.troshchii.reddit.core.extensions.getThreadMessage
import com.troshchii.reddit.core.extensions.logI

private const val EXTRA_IMAGE_URL = "image_url"

class ImageDownloadWorker(private val appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {

    private val tag = getLogTag<ImageDownloadWorker>()

    override fun doWork(): Result {
        logI(tag, "doWork ${getThreadMessage()}")

        // Can't toast on a thread that has not called Looper.prepare()
//        appContext.toast("Downloading...")

        //TODO pass it
        val url = "https://www.redditstatic.com/gold/awards/icon/SnooClappingPremium_512.png"
//        val result = getBitmapWithGlideAndWriteIt(appContext, url, "image.png")

        val fileName = "image_redit.png"

//        val downloadManager = getSystemService(appContext, Context.DOWNLOAD_SERVICE);

//        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, fileName);
        val result = true;
        logI(tag, "Done")
        return if (result) Result.success() else Result.failure()
    }
}