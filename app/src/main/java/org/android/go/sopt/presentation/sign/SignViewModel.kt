package org.android.go.sopt.presentation.sign

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.android.go.sopt.data.datasource.local.GSDataStore
import org.android.go.sopt.presentation.model.UserInfo

class SignViewModel(
    private val gsDataStore: GSDataStore,
) : ViewModel() {
    val inputId = MutableStateFlow("")
    val inputPassword = MutableStateFlow("")
    val inputName = MutableStateFlow("")
    val inputFavoriteSong = MutableStateFlow("")

    var userInput: UserInfo? = null

    private var _isValidSign = MutableStateFlow<Boolean?>(null)
    val isValidSign get() = _isValidSign.asStateFlow()

    private var _isCompleteSign = MutableStateFlow<Boolean?>(null)
    val isCompleteSign get() = _isCompleteSign.asStateFlow()

    private var _isAutoSignIn = MutableStateFlow(gsDataStore.isLogin)
    val isAutoSignIn = _isAutoSignIn.asStateFlow()

    fun isValid() {
        viewModelScope.launch {
            if (inputId.value.length in 6..10 && inputPassword.value.length in 8..12)
                _isValidSign.value = true
            else {
                _isValidSign.value = false
            }
        }
    }

    fun signIn() {
        viewModelScope.launch {
            if (inputId.value == userInput?.id && inputPassword.value == userInput?.password) {
                _isCompleteSign.value = true
                gsDataStore.isLogin = true
            } else
                _isCompleteSign.value = false
        }
    }

    fun getUserInfo(): UserInfo {
        return UserInfo(
            requireNotNull(inputId.value),
            requireNotNull(inputPassword.value),
            inputName.value,
            inputFavoriteSong.value
        )
    }

    fun setUserInfo(userInput: UserInfo) {
        this.userInput = userInput
    }

    fun saveUserInfo() {
        gsDataStore.userName = inputName.value
        gsDataStore.userFavoriteSong = inputFavoriteSong.value
    }
}