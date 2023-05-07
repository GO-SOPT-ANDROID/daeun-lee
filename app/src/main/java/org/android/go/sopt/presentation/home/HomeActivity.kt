package org.android.go.sopt.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivityHomeBinding
import org.android.go.sopt.presentation.gallery.GalleryFragment
import org.android.go.sopt.presentation.search.SearchFragment
import org.android.go.sopt.presentation.util.binding.BindingActivity

class HomeActivity : BindingActivity<ActivityHomeBinding>(R.layout.activity_home) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        changeFragment(HomeFragment())

        binding.bnvHome.run {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_home -> changeFragment(HomeFragment())
                    R.id.menu_gallery -> changeFragment(GalleryFragment())
                    R.id.menu_search -> changeFragment(SearchFragment())
                }
                true
            }
        }
    }

    private fun changeFragment(fragment: Fragment) {
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.home_container, fragment)
            .commit()
    }
}