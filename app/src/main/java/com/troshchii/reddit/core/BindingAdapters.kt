package com.troshchii.reddit.core

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso


@BindingAdapter("picassoUrl")
fun loadImageUrlWithPicasso(imageView: ImageView, url: String?) {
    if (url.isNullOrBlank()) {
        imageView.setImageDrawable(null)
    } else {
        Picasso.get()
            .load(url)
            .into(imageView)
    }
}