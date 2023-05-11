package org.android.go.sopt.domain.repository

import org.android.go.sopt.domain.model.Follower

interface FollowerRepository {
    suspend fun fetchFollowerList(): Result<List<Follower>>
}