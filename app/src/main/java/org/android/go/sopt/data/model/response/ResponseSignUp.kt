package org.android.go.sopt.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseSignUp(
    val status: Int,
    val message: String,
    val data: InfoData,
) {
    @Serializable
    data class InfoData(
        val name: String,
        val skill: String,
    )
}