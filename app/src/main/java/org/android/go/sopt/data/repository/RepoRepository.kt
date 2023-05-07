package org.android.go.sopt.data.repository

import org.android.go.sopt.data.datasource.remote.RepoDatasource
import org.android.go.sopt.domain.model.Repo

class RepoRepository(
    private val repoDatasource: RepoDatasource,
) {
    fun fetchRepo(): Result<List<Repo>> =
        runCatching {
            repoDatasource.fetchRepoContent().map { repo ->
                repo.toRepo()
            }
        }
}