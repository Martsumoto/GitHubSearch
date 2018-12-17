package com.marcelokmats.githubsearch.util

import android.support.constraint.ConstraintLayout.LayoutParams
import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation

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

    fun expand(v: View) {
        v.measure(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        val targetHeight = v.measuredHeight

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.layoutParams.height = 1
        v.visibility = View.VISIBLE
        val a = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                v.layoutParams.height = if (interpolatedTime == 1f)
                    LayoutParams.WRAP_CONTENT
                else
                    (targetHeight * interpolatedTime).toInt()
                v.requestLayout()
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        // 1dp/ms
        a.duration = (targetHeight / v.context.resources.displayMetrics.density).toLong()
        v.startAnimation(a)
    }

    fun collapse(v: View) {
        val initialHeight = v.measuredHeight

        val a = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                if (interpolatedTime == 1f) {
                    v.visibility = View.GONE
                } else {
                    v.layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
                    v.requestLayout()
                }
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        // 1dp/ms
        a.duration = (initialHeight / v.context.resources.displayMetrics.density).toLong()
        v.startAnimation(a)
    }
}
