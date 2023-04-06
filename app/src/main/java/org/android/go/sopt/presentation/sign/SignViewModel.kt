package org.android.go.sopt.presentation.sign

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.android.go.sopt.data.datasource.local.GSDataStore
import org.android.go.sopt.presentation.model.UserInfo

class SignViewModel(
    private val gsDataStore: GSDataStore
) : ViewModel() {
    val inputId = MutableStateFlow("")
    val inputPassword = MutableStateFlow("")
    val inputName = MutableStateFlow("")
    val inputFavoriteSong = MutableStateFlow("")

    var userInput: UserInfo? = null

    private var _isValidSign = MutableStateFlow(false)
    val isValidSign get() = _isValidSign.asStateFlow()

    fun isValid() {
        if (inputId.value.length in 6..10 && inputPassword.value.length in 8..12)
            _isValidSign.value = true
    }
}