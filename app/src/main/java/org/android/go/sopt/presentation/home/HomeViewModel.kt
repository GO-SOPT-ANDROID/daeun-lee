package org.android.go.sopt.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.android.go.sopt.data.repository.RepoRepository
import org.android.go.sopt.domain.model.Repo
import timber.log.Timber

class HomeViewModel(
    private val repoRepository: RepoRepository,
) : ViewModel(
) {
    private var _repoList = MutableStateFlow<List<Repo>>(listOf())
    val repoList get() = _repoList.asStateFlow()

    init {
        fetchRepo()
    }

    private fun fetchRepo() {
        viewModelScope.launch {
            repoRepository.fetchRepo()
                .onSuccess { repoList ->
                    _repoList.value = repoList
                }
                .onFailure { throwable ->
                    Timber.e(throwable.message)
                }
        }
    }
}