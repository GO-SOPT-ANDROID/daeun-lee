package org.android.go.sopt.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.android.go.sopt.databinding.FragmentHomeBinding
import org.android.go.sopt.presentation.common.ViewModelFactory

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding!!) { "${this::class.java.simpleName}에서 에러가 발생했습니다." }
    private val viewModel: HomeViewModel by viewModels { ViewModelFactory(requireContext()) }
    private lateinit var headerAdapter: HeaderAdapter
    private lateinit var repoAdapter: RepoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}