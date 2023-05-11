package org.android.go.sopt.data.datasource.remote

import org.android.go.sopt.data.model.response.ResponseFollower
import org.android.go.sopt.data.service.FollwerService
import javax.inject.Inject

class FollowerDataSource @Inject constructor(
    private val followerService: FollwerService,
) {
    suspend fun fetchfollowerList(): ResponseFollower =
        followerService.fetchFollowerList()
}