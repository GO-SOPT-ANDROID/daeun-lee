package org.android.go.sopt.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.android.go.sopt.data.repository.FollowerRepositoryImpl
import org.android.go.sopt.domain.model.Follower
import org.android.go.sopt.presentation.util.UiState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val followerRepositoryImpl: FollowerRepositoryImpl,
) : ViewModel(
) {
    private var _followerListState = MutableStateFlow<UiState<List<Follower>>>(UiState.Loading)
    val followerListState get() = _followerListState.asStateFlow()

    init {
        fetchFollowerList()
    }

    private fun fetchFollowerList() {
        viewModelScope.launch {
            followerRepositoryImpl.fetchFollowerList()
                .onSuccess { followerList ->
                    _followerListState.value = UiState.Success(followerList)
                }
                .onFailure { throwable ->
                    _followerListState.value = UiState.Error(throwable.message)
                }
        }
    }
}