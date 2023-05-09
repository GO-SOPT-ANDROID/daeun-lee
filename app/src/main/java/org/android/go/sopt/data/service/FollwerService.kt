package org.android.go.sopt.data.service

import org.android.go.sopt.data.model.response.ResponseFollower
import retrofit2.http.GET

interface FollwerService {
    @GET("api/users?page=2")
    suspend fun fetchFollowerList(): ResponseFollower
}