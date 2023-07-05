package org.android.go.sopt.presentation.sign

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.android.go.sopt.data.datasource.local.GSDataStore
import org.android.go.sopt.data.repository.AuthRepositoryImpl
import org.android.go.sopt.presentation.model.UserInfo
import org.android.go.sopt.presentation.util.UiState
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

    private val _signUpUiState = MutableSharedFlow<UiState<Boolean>>()
    val signUpUiState get() = _signUpUiState.asSharedFlow()

    private val _signInUiState = MutableSharedFlow<UiState<Boolean>>()
    val signInUiState get() = _signInUiState.asSharedFlow()

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
                    _signUpUiState.emit(UiState.Success(true))
                }
                .onFailure { throwable ->
                    _signUpUiState.emit(UiState.Error(throwable.message))
                }
        }
    }

    fun signIn() {
        viewModelScope.launch {
            authRepositoryImpl.signIn(inputId.value, inputPassword.value)
                .onSuccess {
                    _signInUiState.emit(UiState.Success(true))
                    gsDataStore.isLogin = true
                }
                .onFailure { throwable ->
                    _signInUiState.emit(UiState.Error(throwable.message))
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