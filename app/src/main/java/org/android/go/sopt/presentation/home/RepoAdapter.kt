package org.android.go.sopt.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.android.go.sopt.databinding.ItemRepoBinding
import org.android.go.sopt.domain.model.Repo
import org.android.go.sopt.presentation.util.ItemDiffCallback

class RepoAdapter : ListAdapter<Repo, RepoAdapter.RepoViewHolder>(
    ItemDiffCallback<Repo>(
        onItemsTheSame = { old, new -> old.title == new.title },
        onContentsTheSame = { old, new -> old == new }
    )
) {

    class RepoViewHolder(
        private val binding: ItemRepoBinding,
    ) : ViewHolder(binding.root) {
        fun onBind(repo: Repo) {
            binding.repo = repo
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding = ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}