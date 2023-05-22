package org.android.go.sopt.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.android.go.sopt.R
import org.android.go.sopt.databinding.FragmentHomeBinding
import org.android.go.sopt.presentation.util.binding.BindingFragment

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var headerAdapter: HeaderAdapter
    private lateinit var followerAdapter: FollowerAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()
        collectData()
    }

    private fun collectData() {
        viewModel.followerList.flowWithLifecycle(lifecycle).onEach { followerList ->
            followerAdapter.submitList(followerList.toMutableList())
        }.launchIn(lifecycleScope)
    }

    private fun initLayout() {
        headerAdapter = HeaderAdapter()
        followerAdapter = FollowerAdapter()
        binding.rvHome.adapter = ConcatAdapter(headerAdapter, followerAdapter)
    }
}