package org.android.go.sopt.data.datasource.remote

import org.android.go.sopt.data.ServicePool
import org.android.go.sopt.data.model.response.ResponseFollower

class FollowerDataSource {
    private val reqresService = ServicePool.reqresService

    suspend fun fetchfollowerList(): ResponseFollower =
        reqresService.fetchFollowerList()
}