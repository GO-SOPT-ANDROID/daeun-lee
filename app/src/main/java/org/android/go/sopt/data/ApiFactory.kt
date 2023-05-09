package org.android.go.sopt.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.android.go.sopt.BuildConfig
import org.android.go.sopt.data.service.AuthService
import org.android.go.sopt.data.service.FollwerService
import retrofit2.Retrofit

object ApiFactory {
    val retrofitAuth by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.AUTH_BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    val retrofitReqres by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.REQRES_BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> createAuth(): T = retrofitAuth.create<T>(T::class.java)
    inline fun <reified T> createReqres(): T = retrofitReqres.create<T>(T::class.java)
}

object ServicePool {
    val authService = ApiFactory.createAuth<AuthService>()
    val reqresService = ApiFactory.createReqres<FollwerService>()
}