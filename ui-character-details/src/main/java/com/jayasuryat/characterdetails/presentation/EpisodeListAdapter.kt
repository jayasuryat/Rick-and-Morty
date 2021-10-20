package com.jayasuryat.characterdetails.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jayasuryat.base.shrinkOnClick
import com.jayasuryat.characterdetails.databinding.ItemEpisodeBinding

class EpisodeListAdapter(
    private val onClicked: (episode: EpisodeData, name: View, nameContainer: View) -> Unit,
) : ListAdapter<EpisodeData, EpisodeListAdapter.EpisodeViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder =
        ItemEpisodeBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
            .let(::EpisodeViewHolder)

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class EpisodeViewHolder(private val item: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(item.root) {

        init {
            item.cvRoot.shrinkOnClick {
                val position = adapterPosition
                if (position >= 0)
                    onClicked(
                        getItem(position),
                        item.tvEpisodeName,
                        item.cvRoot
                    )
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(data: EpisodeData) {
            item.tvEpisodeName.text = data.episodeName
            item.tvSeasonNumber.text = "S${data.season}"
            item.tvEpisodeNumber.text = "E${data.episode}"
            item.tvEpisodeName.transitionName = "name_${data.episodeId}"
            item.cvRoot.transitionName = "name_container_${data.episodeId}"
        }
    }

    companion object {

        private val diffCallback = object : DiffUtil.ItemCallback<EpisodeData>() {
            override fun areItemsTheSame(oldItem: EpisodeData, newItem: EpisodeData): Boolean =
                oldItem.episodeId == newItem.episodeId

            override fun areContentsTheSame(oldItem: EpisodeData, newItem: EpisodeData): Boolean =
                oldItem == newItem
        }
    }
}

data class EpisodeData(
    val episodeId: Long,
    val episodeName: String,
    val season: Int,
    val episode: Int,
)