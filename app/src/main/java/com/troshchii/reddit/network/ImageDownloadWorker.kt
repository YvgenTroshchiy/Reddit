package com.troshchii.reddit.network

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.troshchii.reddit.core.extensions.getLogTag
import com.troshchii.reddit.core.extensions.logI
import com.troshchii.reddit.core.utils.getBitmapWithGlideAndWriteIt


private const val EXTRA_IMAGE_URL = "image_url"


class ImageDownloadWorker(private val appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {

    private val tag = getLogTag<ImageDownloadWorker>()

    override fun doWork(): Result {
        logI(tag, "doWork")

        //TODO pass it
        val url =
            "https://external-preview.redd.it/dl1WffE4D_VNuLLUa5zsXvSuJeVq6A1ABCL2PTv6Eeo.jpg?auto=webp&amp;s=e550ce2fd518b9af9fe438d99fdcbc1b6686b175"
        val result = getBitmapWithGlideAndWriteIt(appContext, url, "image")

        return if (result) Result.success() else Result.failure()
    }
}