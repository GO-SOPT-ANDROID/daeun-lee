package org.android.go.sopt.presentation.model

import androidx.annotation.DrawableRes

data class Repo(
    @DrawableRes val image: Int,
    val title: String,
    val author: String
)
