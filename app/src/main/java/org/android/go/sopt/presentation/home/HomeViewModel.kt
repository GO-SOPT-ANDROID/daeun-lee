package org.android.go.sopt.presentation.home

import androidx.lifecycle.ViewModel
import org.android.go.sopt.R
import org.android.go.sopt.presentation.model.Repo

class HomeViewModel() : ViewModel(
) {
    val mockRepoList = listOf(
        Repo(
            image = R.drawable.profile,
            title = "Keep Go Eat!",
            author = "Dani43"
        ),
        Repo(
            image = R.drawable.profile,
            title = "EXIT",
            author = "Dani43"
        ),
        Repo(
            image = R.drawable.profile,
            title = "IN-SOPT",
            author = "Dani43"
        ),
        Repo(
            image = R.drawable.profile,
            title = "GO-SOPT",
            author = "Dani43"
        ),
        Repo(
            image = R.drawable.profile,
            title = "Danini",
            author = "Dani43"
        ),
        Repo(
            image = R.drawable.profile,
            title = "shoppi",
            author = "Dani43"
        ),
        Repo(
            image = R.drawable.profile,
            title = "Dani",
            author = "Dani43"
        ),
        Repo(
            image = R.drawable.profile,
            title = "Dani",
            author = "Dani43"
        ),
    )
}