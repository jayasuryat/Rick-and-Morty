package com.jayasuryat.characterdetails.presentation.episodes

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jayasuryat.base.shrinkOnClick
import com.jayasuryat.characterdetails.databinding.ItemEpisodeBinding
import com.jayasuryat.characterdetails.databinding.ItemSeasonBreakBinding

class EpisodeListAdapter(
    private val onClicked: (episode: CharacterEpisodeData.EpisodeData, name: View, nameContainer: View) -> Unit,
) : ListAdapter<CharacterEpisodeData, RecyclerView.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder = when (viewType) {

        ITEM_TYPE_EPISODE -> ItemEpisodeBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
            .let(::EpisodeViewHolder)

        ITEM_TYPE_SEASON_BREAK -> ItemSeasonBreakBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
            .let(::SeasonBreakViewHolder)

        else -> throw IllegalStateException("Invalid view type $viewType")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is EpisodeViewHolder -> holder.bind(getItem(position) as CharacterEpisodeData.EpisodeData)
            is SeasonBreakViewHolder -> Unit
        }
    }

    inner class EpisodeViewHolder(private val item: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(item.root) {

        init {
            item.cvRoot.shrinkOnClick {
                val position = adapterPosition
                if (position >= 0)
                    onClicked(
                        (getItem(position) as CharacterEpisodeData.EpisodeData),
                        item.tvEpisodeName,
                        item.cvRoot
                    )
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(data: CharacterEpisodeData.EpisodeData) {
            item.tvEpisodeName.text = data.episodeName
            item.tvSeasonNumber.text = "S${data.season}"
            item.tvEpisodeNumber.text = "E${data.episode}"
            item.tvEpisodeName.transitionName = "name_${data.episodeId}"
            item.cvRoot.transitionName = "name_container_${data.episodeId}"
        }
    }

    inner class SeasonBreakViewHolder(
        item: ItemSeasonBreakBinding,
    ) : RecyclerView.ViewHolder(item.root)

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is CharacterEpisodeData.EpisodeData -> ITEM_TYPE_EPISODE
        is CharacterEpisodeData.SeasonDivider -> ITEM_TYPE_SEASON_BREAK
    }

    private companion object {

        const val ITEM_TYPE_EPISODE: Int = 1
        const val ITEM_TYPE_SEASON_BREAK: Int = 2

        private val diffCallback = object : DiffUtil.ItemCallback<CharacterEpisodeData>() {
            override fun areItemsTheSame(
                oldItem: CharacterEpisodeData,
                newItem: CharacterEpisodeData,
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: CharacterEpisodeData,
                newItem: CharacterEpisodeData,
            ): Boolean = oldItem == newItem
        }
    }
}