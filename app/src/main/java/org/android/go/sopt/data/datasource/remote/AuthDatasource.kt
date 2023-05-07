package org.android.go.sopt.data.datasource.remote

import org.android.go.sopt.data.ServicePool
import org.android.go.sopt.data.model.request.RequestSignIn
import org.android.go.sopt.data.model.request.RequestSignUp
import org.android.go.sopt.data.model.response.ResponseSignIn
import org.android.go.sopt.data.model.response.ResponseSignUp

class AuthDatasource {
    private val authService = ServicePool.authService

    suspend fun signUp(requestSignUp: RequestSignUp): ResponseSignUp =
        authService.signUp(requestSignUp)
}