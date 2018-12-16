package com.marcelokmats.githubsearch.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object ImageUtil {

    fun setupImage(context: Context, imageUrl: String, imageHolder: ImageView) {
        val requestOptions = RequestOptions()
        requestOptions.placeholder(android.R.drawable.stat_notify_error)
        requestOptions.error(android.R.drawable.stat_notify_error)

        Glide.with(context).setDefaultRequestOptions(requestOptions).load(imageUrl).into(imageHolder)
    }
}
