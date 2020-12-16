package com.backbase.assignment.ui.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.backbase.assignment.R
import com.bumptech.glide.Glide

/**
 * Using glide to load image when binding to xml view
 * @param view
 * @param url
 */
@BindingAdapter("imageUrl")
fun loadImage(
    view: ImageView,
    url: String
) {
    if (url.isNotEmpty()) {
        Glide.with(view.context)
            .load(url)
            .placeholder(R.drawable.ic_baseline_image_24)
            .error(R.drawable.ic_sharp_broken_image_24)
            .into(view)
    }
}
