package com.tung.musicapplication.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders

fun ImageView.loadUrl(url: String) {
    val imageRequest = GlideUrl(
        url, LazyHeaders.Builder()
            .addHeader("User-Agent", "5")
            .build()
    )

    Glide.with(context).load(imageRequest).into(this)
}