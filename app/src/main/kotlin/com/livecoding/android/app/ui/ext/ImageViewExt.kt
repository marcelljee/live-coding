package com.livecoding.android.app.ui.ext

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.squareup.picasso.Picasso

fun ImageView.load(url: String?) {
    if (url?.isEmpty() == true) return

    Picasso.get()
        .load(url)
        .into(this)
}

fun ImageView.load(url: String?, @DrawableRes placeholderResId: Int) {
    if (url?.isEmpty() == true) {
        setImageResource(placeholderResId)
    } else {
        Picasso.get()
            .load(url)
            .placeholder(placeholderResId)
            .into(this)
    }
}