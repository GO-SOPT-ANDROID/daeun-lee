package org.android.go.sopt.presentation.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.android.go.sopt.data.datasource.local.GSDataStore

class HomeViewModel(
    private val gsDataStore: GSDataStore
) : ViewModel(
) {
    private var _name = MutableStateFlow("")
    val name get() = _name.asStateFlow()
    private var _song = MutableStateFlow("")
    val song get() = _song.asStateFlow()

    init {
        setUserInfo()
    }

    private fun setUserInfo() {
        _name.value = gsDataStore.userName
        _song.value = gsDataStore.userFavoriteSong
    }
}