package org.android.go.sopt.data.service

import org.android.go.sopt.data.model.response.ResponseFollower
import retrofit2.http.GET
import retrofit2.http.Query

interface FollwerService {
    @GET("api/users")
    suspend fun fetchFollowerList(
        @Query("page") page: Int = 2,
    ): ResponseFollower
}