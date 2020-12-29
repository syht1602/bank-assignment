package com.backbase.assignment.ui.utils

import android.graphics.Color
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.backbase.assignment.R
import com.backbase.assignment.ui.models.moviedetail.Genre
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.flexbox.FlexboxLayout


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
    val circularProgressDrawable = CircularProgressDrawable(view.context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.backgroundColor = Color.TRANSPARENT
    circularProgressDrawable.start()

    if (url.isNotEmpty()) {
        Glide.with(view.context)
            .load(url)
            .placeholder(circularProgressDrawable)
            .error(R.drawable.ic_sharp_broken_image_24)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(view)
    }
}

/**
 * Using glide to load image when binding to xml view
 * @param view
 * @param genres
 */
@BindingAdapter("createGenre")
fun createGenre(
    view: FlexboxLayout,
    genres: List<Genre>
) {
    for (genre in genres) {
        val textParam = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        val textView = TextView(view.context)
        textView.setBackgroundResource(R.drawable.genre_item_background)

        textView.text = genre.name.toUpperCase()
        textView.setPadding(30)
        textView.textSize = 15F
        textView.setTextColor(ContextCompat.getColor(view.context, R.color.genre_text))
        textView.isSingleLine = true
        textView.layoutParams = textParam
        view.addView(textView)
    }
}
