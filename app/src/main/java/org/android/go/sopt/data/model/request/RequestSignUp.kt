package org.android.go.sopt.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestSignUp(
    val id: String,
    val password: String,
    val name: String,
    val skill: String,
)