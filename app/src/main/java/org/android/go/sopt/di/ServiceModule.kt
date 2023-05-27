package org.android.go.sopt.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.android.go.sopt.data.service.AuthService
import org.android.go.sopt.data.service.FollwerService
import org.android.go.sopt.data.service.ImageService
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

    @Singleton
    @Provides
    fun provideImageService(
        @NetworkModule.Retrofit2(BaseUrlType.AUTH)
        retrofit: Retrofit,
    ): ImageService =
        retrofit.create(ImageService::class.java)
}