package com.jayasuryat.characterdetails

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jayasuryat.base.shrinkOnClick
import com.jayasuryat.characterdetails.databinding.ItemEpisodeBinding

class EpisodeListAdapter : ListAdapter<EpisodeData,
        EpisodeListAdapter.EpisodeViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder =
        ItemEpisodeBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
            .let(::EpisodeViewHolder)

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class EpisodeViewHolder(private val item: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(item.root) {

        init {
            item.cvRoot.shrinkOnClick { }
        }

        @SuppressLint("SetTextI18n")
        fun bind(data: EpisodeData) {
            item.tvEpisodeName.text = data.episodeName
            item.tvSeasonNumber.text = "S${data.season}"
            item.tvEpisodeNumber.text = "E${data.episode}"
        }
    }

    companion object {

        private val diffCallback = object : DiffUtil.ItemCallback<EpisodeData>() {
            override fun areItemsTheSame(oldItem: EpisodeData, newItem: EpisodeData): Boolean =
                oldItem.episode == newItem.episode

            override fun areContentsTheSame(oldItem: EpisodeData, newItem: EpisodeData): Boolean =
                oldItem == newItem
        }
    }
}

data class EpisodeData(
    val episodeName: String,
    val season: Int,
    val episode: Int,
    val url: String,
)