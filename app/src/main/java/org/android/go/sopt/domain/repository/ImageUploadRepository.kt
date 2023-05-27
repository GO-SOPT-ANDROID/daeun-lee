package org.android.go.sopt.domain.repository

import okhttp3.MultipartBody

interface ImageUploadRepository {
    suspend fun uploadImage(image: MultipartBody.Part): Result<Unit>
}