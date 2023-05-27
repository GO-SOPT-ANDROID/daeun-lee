package org.android.go.sopt.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.android.go.sopt.data.repository.AuthRepositoryImpl
import org.android.go.sopt.data.repository.FollowerRepositoryImpl
import org.android.go.sopt.data.repository.ImageUploadRepositoryImpl
import org.android.go.sopt.domain.repository.AuthRepository
import org.android.go.sopt.domain.repository.FollowerRepository
import org.android.go.sopt.domain.repository.ImageUploadRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl,
    ): AuthRepository

    @Binds
    @Singleton
    fun bindFollowerRepository(
        followerRepositoryImpl: FollowerRepositoryImpl,
    ): FollowerRepository

    @Binds
    @Singleton
    fun bindImageUploadRepository(
        imageUploadRepositoryImpl: ImageUploadRepositoryImpl,
    ): ImageUploadRepository
}