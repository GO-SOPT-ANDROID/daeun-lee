package org.android.go.sopt.data.service

import org.android.go.sopt.data.model.request.RequestSignIn
import org.android.go.sopt.data.model.request.RequestSignUp
import org.android.go.sopt.data.model.response.ResponseSignIn
import org.android.go.sopt.data.model.response.ResponseSignUp
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("sign-up")
    suspend fun signUp(@Body requestSignUp: RequestSignUp): ResponseSignUp
}