package org.android.go.sopt.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.android.go.sopt.data.repository.FollowerRepositoryImpl
import org.android.go.sopt.domain.model.Follower
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val followerRepositoryImpl: FollowerRepositoryImpl,
) : ViewModel(
) {
    private var _followerList = MutableStateFlow<List<Follower>>(listOf())
    val followerList get() = _followerList.asStateFlow()

    init {
        fetchFollowerList()
    }

    private fun fetchFollowerList() {
        viewModelScope.launch {
            followerRepositoryImpl.fetchFollowerList()
                .onSuccess { followerList ->
                    _followerList.value = followerList
                }
                .onFailure { throwable ->
                    Timber.e(throwable.message)
                }
        }
    }
}