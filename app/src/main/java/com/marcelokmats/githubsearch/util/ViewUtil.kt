package com.marcelokmats.githubsearch.util

import android.view.View

object ViewUtil {

    enum class Type {
        CONTENT,
        PROGRESSBAR,
        ERROR
    }

    fun toggleVisibility(content: View, progressBar: View, errorMessage: View, type: ViewUtil.Type) {
        when (type) {
            ViewUtil.Type.CONTENT -> {
                content.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
                errorMessage.visibility = View.GONE
            }
            ViewUtil.Type.PROGRESSBAR -> {
                content.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
                errorMessage.visibility = View.GONE
            }
            ViewUtil.Type.ERROR -> {
                content.visibility = View.GONE
                progressBar.visibility = View.GONE
                errorMessage.visibility = View.VISIBLE
            }
        }
    }
}
