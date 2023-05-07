package org.android.go.sopt.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.android.go.sopt.R
import org.android.go.sopt.databinding.FragmentHomeBinding
import org.android.go.sopt.presentation.common.ViewModelFactory
import org.android.go.sopt.presentation.util.binding.BindingFragment

class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels { ViewModelFactory(requireContext()) }
    private lateinit var headerAdapter: HeaderAdapter
    private lateinit var repoAdapter: RepoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()
        collectData()
    }

    private fun collectData() {
        viewModel.repoList.flowWithLifecycle(lifecycle).onEach { repoList ->
            repoAdapter.submitList(repoList.toMutableList())
        }.launchIn(lifecycleScope)
    }

    private fun initLayout() {
        headerAdapter = HeaderAdapter()
        repoAdapter = RepoAdapter()
        binding.rvHome.adapter = ConcatAdapter(headerAdapter, repoAdapter)
    }
}