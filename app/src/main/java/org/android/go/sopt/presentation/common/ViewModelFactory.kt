package org.android.go.sopt.presentation.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.android.go.sopt.data.datasource.local.GSDataStore
import org.android.go.sopt.data.datasource.remote.AuthDatasource
import org.android.go.sopt.data.datasource.remote.RepoDatasource
import org.android.go.sopt.data.repository.AuthRepository
import org.android.go.sopt.data.repository.RepoRepository
import org.android.go.sopt.presentation.home.HomeViewModel
import org.android.go.sopt.presentation.sign.SignViewModel
import org.android.go.sopt.presentation.util.AssetLoader

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignViewModel::class.java)) {
            return SignViewModel(GSDataStore(context), AuthRepository(AuthDatasource())) as T
        } else if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(RepoRepository(RepoDatasource(AssetLoader(context)))) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}