package org.android.go.sopt.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserInfo(
    val id: String,
    val password: String,
    val name: String?,
    val favoriteSong: String?
) : Parcelable