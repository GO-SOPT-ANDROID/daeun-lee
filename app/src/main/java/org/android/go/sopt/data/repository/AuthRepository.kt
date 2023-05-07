package org.android.go.sopt.data.repository

import org.android.go.sopt.data.datasource.remote.AuthDatasource
import org.android.go.sopt.data.model.request.RequestSignIn
import org.android.go.sopt.data.model.request.RequestSignUp
import org.android.go.sopt.data.model.response.ResponseSignIn
import org.android.go.sopt.data.model.response.ResponseSignUp

class AuthRepository(val authDatasource: AuthDatasource) {
    suspend fun signUp(
        id: String,
        password: String,
        name: String,
        skill: String,
    ): Result<ResponseSignUp.InfoData> =
        runCatching {
            authDatasource.signUp(RequestSignUp(id, password, name, skill)).data
        }

    suspend fun signIn(
        id: String,
        password: String,
    ): Result<ResponseSignIn.InfoData> =
        runCatching {
            authDatasource.signIn(RequestSignIn(id, password)).data
        }
}