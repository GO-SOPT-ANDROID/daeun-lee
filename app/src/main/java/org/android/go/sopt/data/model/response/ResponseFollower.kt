package org.android.go.sopt.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.android.go.sopt.domain.model.Follower

@Serializable
data class ResponseFollower(
    @SerialName("data")
    val infos: List<Info>,
    val page: Int,
    @SerialName("per_page")
    val perPage: Int,
    val support: Support,
    val total: Int,
    @SerialName("total_pages")
    val totalPages: Int,

    ) {
    @Serializable
    data class Info(
        val avatar: String,
        val email: String,
        @SerialName("first_name")
        val firstName: String,
        val id: Int,
        @SerialName("last_name")
        val lastName: String,
    )

    @Serializable
    data class Support(
        val text: String,
        val url: String,
    )

    fun toFollower() = infos.map { info ->
        Follower(
            avatar = info.avatar,
            email = info.email,
            firstName = info.firstName
        )
    }
}