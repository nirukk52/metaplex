package com.ql.giantbomb.game.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.metaplex.lib.modules.nfts.models.NFT
import com.ql.giantbomb.base.models.Game
import com.ql.giantbomb.databinding.ItemGameBinding
import com.ql.giantbomb.game.GamesViewModel

/**
 * Adapter for the task list. Has a reference to the [GamesViewModel] to send actions back to it.
 */
class GamesAdapter(private val viewModel: GamesViewModel) :
    ListAdapter<NFT, GamesAdapter.GameViewHolder>(GameDiffCallback()) {

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(viewModel, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        return GameViewHolder.from(parent)
    }

    class GameViewHolder private constructor(val binding: ItemGameBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: GamesViewModel, item: NFT) {

//            binding.viewmodel = viewModel
            binding.game = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): GameViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemGameBinding.inflate(layoutInflater, parent, false)

                return GameViewHolder(binding)
            }
        }
    }

}

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class GameDiffCallback : DiffUtil.ItemCallback<NFT>() {
    override fun areItemsTheSame(oldItem: NFT, newItem: NFT): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: NFT, newItem: NFT): Boolean {
        return false
    }
}
