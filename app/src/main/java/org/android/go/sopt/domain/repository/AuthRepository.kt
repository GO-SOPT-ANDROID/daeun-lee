package org.android.go.sopt.domain.repository

import org.android.go.sopt.data.model.response.ResponseSignIn
import org.android.go.sopt.data.model.response.ResponseSignUp

interface AuthRepository {
    suspend fun signUp(
        id: String,
        password: String,
        name: String,
        skill: String,
    ): Result<ResponseSignUp.InfoData>

    suspend fun signIn(id: String, password: String): Result<ResponseSignIn.InfoData>
}