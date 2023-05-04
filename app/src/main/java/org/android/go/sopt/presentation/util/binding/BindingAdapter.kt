package org.android.go.sopt.presentation.util.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun loadImager(view: ImageView, imageurl: String) {
    Glide.with(view)
        .load(imageurl)
        .into(view)
}