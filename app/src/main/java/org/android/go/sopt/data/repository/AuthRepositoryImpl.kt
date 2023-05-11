package org.android.go.sopt.data.repository

import org.android.go.sopt.data.datasource.remote.AuthDatasource
import org.android.go.sopt.data.model.request.RequestSignIn
import org.android.go.sopt.data.model.request.RequestSignUp
import org.android.go.sopt.data.model.response.ResponseSignIn
import org.android.go.sopt.data.model.response.ResponseSignUp
import org.android.go.sopt.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDatasource: AuthDatasource): AuthRepository {
    override suspend fun signUp(
        id: String,
        password: String,
        name: String,
        skill: String,
    ): Result<ResponseSignUp.InfoData> =
        runCatching {
            authDatasource.signUp(RequestSignUp(id, password, name, skill)).data
        }

    override suspend fun signIn(
        id: String,
        password: String,
    ): Result<ResponseSignIn.InfoData> =
        runCatching {
            authDatasource.signIn(RequestSignIn(id, password)).data
        }
}