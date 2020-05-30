package com.troshchii.reddit.ui.newsdetails

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.troshchii.reddit.R
import com.troshchii.reddit.core.extensions.getLogTag
import com.troshchii.reddit.core.extensions.logD
import com.troshchii.reddit.core.extensions.toast
import kotlinx.android.synthetic.main.newsdetails_activity.*

private const val EXTRA_TITLE = "extra_title"
private const val EXTRA_URL = "extra_url"

private var Intent.title: String
    get() = getStringExtra(EXTRA_TITLE)
    set(value) {
        putExtra(EXTRA_TITLE, value)
    }

private var Intent.imageUrl: String
    get() = getStringExtra(EXTRA_URL)
    set(value) {
        putExtra(EXTRA_URL, value)
    }

class NewsDetailsActivity : AppCompatActivity() {

    private val tag = getLogTag<NewsDetailsActivity>()

    companion object {
        fun newIntent(context: Context, title: String, imageUrl: String): Intent {
            return Intent(context, NewsDetailsActivity::class.java).apply {
                this.title = title
                this.imageUrl = imageUrl
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.newsdetails_activity)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        showImage()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.newsdetails_menu, menu)
        return true
    }

    private var downloadId = -1L

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            R.id.menu_save -> {
                logD(tag, "Save Image")

                if (Environment.getExternalStorageState() != Environment.MEDIA_MOUNTED) {
                    toast("External storage not mounted!")
                } else {

                    //TODO: use not debug
                    val url = "https://www.redditstatic.com/gold/awards/icon/SnooClappingPremium_512.png"

                    val request = DownloadManager.Request(Uri.parse(url))
                        .setTitle(intent.title)
                        .setDescription("Downloading")
                        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                        .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, intent.title + ".png")

                    val downloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                    downloadId = downloadManager.enqueue(request)
                }
                true
            }
            else -> false
        }

    // TODO: Create VM. Set Placeholder
    private fun showImage() {
        val url = HtmlCompat.fromHtml(intent.imageUrl, HtmlCompat.FROM_HTML_MODE_LEGACY)
        Glide.with(this)
            .load(url.toString())
            .into(image)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}