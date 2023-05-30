package org.android.go.sopt.presentation.sign

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.android.go.sopt.data.datasource.local.GSDataStore
import org.android.go.sopt.data.repository.AuthRepositoryImpl
import org.android.go.sopt.presentation.model.UserInfo
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SignViewModel @Inject constructor(
    private val gsDataStore: GSDataStore,
    private val authRepositoryImpl: AuthRepositoryImpl,
) : ViewModel() {
    val inputId = MutableStateFlow("")
    val inputPassword = MutableStateFlow("")
    val inputName = MutableStateFlow("")
    val inputFavoriteSong = MutableStateFlow("")

    var userInput: UserInfo? = null

    val isValidId: StateFlow<Boolean?> = inputId.map {
        it.matches(Regex(ID_PATTERN))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    val isValidPW: StateFlow<Boolean?> = inputPassword.map {
        it.matches(Regex(PASSWORD_PATTERN))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)


    val isValidInput: StateFlow<Boolean> =
        combine(
            isValidId,
            isValidPW,
            inputName,
            inputFavoriteSong
        ) { isValidId, isValidPassword, name, favoriteSong ->
            isValidId == true && isValidPassword == true && name.isNotBlank() && favoriteSong.isNotBlank()
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)

    private var _isCompleteSignUp = MutableStateFlow<Boolean?>(null)
    val isCompleteSignUp get() = _isCompleteSignUp.asStateFlow()

    private var _isCompleteSignIn = MutableStateFlow<Boolean?>(null)
    val isCompleteSignIn get() = _isCompleteSignIn.asStateFlow()

    private var _isAutoSignIn = MutableStateFlow(gsDataStore.isLogin)
    val isAutoSignIn = _isAutoSignIn.asStateFlow()

    fun signUp() {
        viewModelScope.launch {
            authRepositoryImpl.signUp(
                inputId.value,
                inputPassword.value,
                inputName.value,
                inputFavoriteSong.value
            )
                .onSuccess {
                    _isCompleteSignUp.value = true
                }
                .onFailure { throwable ->
                    _isCompleteSignUp.value = false
                    Timber.e(throwable.message)
                }
        }
    }

    fun signIn() {
        viewModelScope.launch {
            authRepositoryImpl.signIn(inputId.value, inputPassword.value)
                .onSuccess {
                    _isCompleteSignIn.value = true
                    gsDataStore.isLogin = true
                }
                .onFailure { throwable ->
                    _isCompleteSignIn.value = false
                    Timber.e(throwable.message)
                }
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

    companion object {
        const val ID_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,10}$"
        const val PASSWORD_PATTERN =
            "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{6,12}$"
    }

}