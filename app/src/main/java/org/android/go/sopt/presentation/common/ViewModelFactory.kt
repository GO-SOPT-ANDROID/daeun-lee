package org.android.go.sopt.presentation.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.android.go.sopt.data.datasource.local.GSDataStore
import org.android.go.sopt.presentation.home.HomeViewModel
import org.android.go.sopt.presentation.sign.SignViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignViewModel::class.java)) {
            return SignViewModel(GSDataStore(context)) as T
        } else if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(GSDataStore(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}