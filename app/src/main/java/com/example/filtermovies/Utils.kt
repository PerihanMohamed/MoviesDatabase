package com.example.filtermovies

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String) {
    Glide.with(this)
            .load(url)
            .into(this)

}