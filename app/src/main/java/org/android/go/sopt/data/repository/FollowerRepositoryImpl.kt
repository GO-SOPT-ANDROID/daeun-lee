package org.android.go.sopt.data.repository

import org.android.go.sopt.data.datasource.remote.FollowerDataSource
import org.android.go.sopt.domain.model.Follower
import org.android.go.sopt.domain.repository.FollowerRepository
import javax.inject.Inject

class FollowerRepositoryImpl @Inject constructor(
    private val followerDataSource: FollowerDataSource,
) : FollowerRepository {
    override suspend fun fetchFollowerList(): Result<List<Follower>> =
        runCatching {
            followerDataSource.fetchfollowerList().toFollower()
        }
}