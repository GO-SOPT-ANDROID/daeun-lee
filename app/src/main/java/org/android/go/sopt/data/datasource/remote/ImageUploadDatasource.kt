package org.android.go.sopt.data.datasource.remote

import okhttp3.MultipartBody
import org.android.go.sopt.data.service.ImageService
import javax.inject.Inject

class ImageUploadDatasource @Inject constructor(
    private val imageService: ImageService,
) {
    suspend fun uploadImage(image: MultipartBody.Part) =
        imageService.uploadImage(image)
}