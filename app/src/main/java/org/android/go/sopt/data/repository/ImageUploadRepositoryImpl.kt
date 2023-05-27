package org.android.go.sopt.data.repository

import okhttp3.MultipartBody
import org.android.go.sopt.data.datasource.remote.ImageUploadDatasource
import org.android.go.sopt.domain.repository.ImageUploadRepository
import javax.inject.Inject

class ImageUploadRepositoryImpl @Inject constructor(
    private val imageUploadDatasource: ImageUploadDatasource,
) : ImageUploadRepository {
    override suspend fun uploadImage(image: MultipartBody.Part): Result<Unit> =
        runCatching {
            imageUploadDatasource.uploadImage(image)
        }
}