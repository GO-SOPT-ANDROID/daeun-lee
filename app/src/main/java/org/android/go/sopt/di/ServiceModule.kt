package org.android.go.sopt.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.android.go.sopt.data.service.AuthService
import org.android.go.sopt.data.service.FollwerService
import org.android.go.sopt.data.type.BaseUrlType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Singleton
    @Provides
    fun provideAuthService(
        @NetworkModule.Retrofit2(BaseUrlType.AUTH)
        retrofit: Retrofit,
    ): AuthService =
        retrofit.create(AuthService::class.java)

    @Singleton
    @Provides
    fun provideFollowerService(
        @NetworkModule.Retrofit2(BaseUrlType.REQRES)
        retrofit: Retrofit,
    ): FollwerService =
        retrofit.create(FollwerService::class.java)
}