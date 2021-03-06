package com.troshchii.reddit.ui.topnews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.troshchii.reddit.R
import com.troshchii.reddit.core.extensions.getLogTag
import com.troshchii.reddit.core.extensions.logI

class TopNewsActivity : AppCompatActivity() {

    private val tag = getLogTag<TopNewsActivity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logI(tag, "onCreate")

        setContentView(R.layout.activity_layout)
    }
}