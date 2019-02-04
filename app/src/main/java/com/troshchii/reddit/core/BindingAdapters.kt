package com.troshchii.reddit.core

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


//TODO: Set Placeholder
@BindingAdapter("imageUrl")
fun loadImageUrlWithPicasso(imageView: ImageView, url: String?) {
    if (url.isNullOrBlank()) {
        imageView.setImageDrawable(null)
    } else {
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }
}