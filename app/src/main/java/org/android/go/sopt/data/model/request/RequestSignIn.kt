package org.android.go.sopt.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestSignIn(
    @SerialName("id")
    val id: String,
    @SerialName("password")
    val password: String,
)