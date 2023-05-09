package org.android.go.sopt.data.repository

import org.android.go.sopt.data.datasource.remote.FollowerDataSource
import org.android.go.sopt.domain.model.Follower

class FollowerRepository(private val followerDataSource: FollowerDataSource) {
    suspend fun fetchFollowerList(): Result<List<Follower>> =
        runCatching {
            followerDataSource.fetchfollowerList().toFollower()
        }
}