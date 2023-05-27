package org.android.go.sopt.presentation.gallery

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.android.go.sopt.data.repository.ImageUploadRepositoryImpl
import org.android.go.sopt.presentation.util.ContentUriRequestBody
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val imageUploadRepositoryImpl: ImageUploadRepositoryImpl,
) : ViewModel() {
    private val _image = MutableStateFlow<ContentUriRequestBody?>(null)
    val image get() = _image.asStateFlow()

    fun setRequestBody(requestBody: ContentUriRequestBody) {
        _image.value = requestBody
    }

    fun uploadImage() {
        if (image.value == null) {
        } else {
            viewModelScope.launch {
                kotlin.runCatching {
                    imageUploadRepositoryImpl.uploadImage(image.value!!.toFormData())
                }.onSuccess {
                    Log.d("danii", "success !!!")
                }.onFailure { throwable ->
                    Log.d("danii", throwable.message.toString())
                }
            }
        }
    }
}