package org.android.go.sopt.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.android.go.sopt.databinding.ItemFollowerBinding
import org.android.go.sopt.domain.model.Follower
import org.android.go.sopt.presentation.util.ItemDiffCallback

class FollowerAdapter : ListAdapter<Follower, FollowerAdapter.FollowerViewHolder>(
    ItemDiffCallback<Follower>(
        onItemsTheSame = { old, new -> old.email == new.email},
        onContentsTheSame = { old, new -> old == new }
    )
) {

    class FollowerViewHolder(
        private val binding: ItemFollowerBinding,
    ) : ViewHolder(binding.root) {
        fun onBind(follower: Follower) {
            binding.follower = follower
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding =
            ItemFollowerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}