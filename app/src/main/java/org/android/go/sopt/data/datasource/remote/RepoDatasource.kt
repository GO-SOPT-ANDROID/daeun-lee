package org.android.go.sopt.data.datasource.remote

import MockRepoDto
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.android.go.sopt.presentation.util.AssetLoader

class RepoDatasource(
    private val assetLoader: AssetLoader,
) {
    val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    fun fetchRepoContent(): Array<MockRepoDto> =
        assetLoader.getJsonString(FAKE_REPO_LIST)?.let { jsonString ->
            json.decodeFromString<Array<MockRepoDto>>(jsonString)
        } ?: emptyArray()

    companion object {
        private const val FAKE_REPO_LIST = "fake_repo_list.json"
    }
}