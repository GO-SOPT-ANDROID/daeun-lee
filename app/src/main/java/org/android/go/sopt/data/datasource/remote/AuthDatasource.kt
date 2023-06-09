package org.android.go.sopt.data.datasource.remote

import org.android.go.sopt.data.model.request.RequestSignIn
import org.android.go.sopt.data.model.request.RequestSignUp
import org.android.go.sopt.data.model.response.ResponseSignIn
import org.android.go.sopt.data.model.response.ResponseSignUp
import org.android.go.sopt.data.service.AuthService
import javax.inject.Inject

class AuthDatasource @Inject constructor(
    private val authService: AuthService,
) {
    suspend fun signUp(requestSignUp: RequestSignUp): ResponseSignUp = authService.signUp(requestSignUp)

    suspend fun signIn(requestSignIn: RequestSignIn): ResponseSignIn = authService.signIn(requestSignIn)
}