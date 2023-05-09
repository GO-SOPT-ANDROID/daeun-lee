package org.android.go.sopt.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.android.go.sopt.data.repository.FollowerRepository
import org.android.go.sopt.domain.model.Follower
import timber.log.Timber

class HomeViewModel(
    private val followerRepository: FollowerRepository,
) : ViewModel(
) {
    private var _followerList = MutableStateFlow<List<Follower>>(listOf())
    val followerList get() = _followerList.asStateFlow()

    init {
        fetchFollowerList()
    }

    private fun fetchFollowerList() {
        viewModelScope.launch {
            followerRepository.fetchFollowerList()
                .onSuccess { followerList ->
                    _followerList.value = followerList
                }
                .onFailure { throwable ->
                    Timber.e(throwable.message)
                }
        }
    }
}