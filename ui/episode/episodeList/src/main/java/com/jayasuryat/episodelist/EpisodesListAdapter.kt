package com.jayasuryat.episodelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jayasuryat.base.shrinkOnClick
import com.jayasuryat.episodelist.databinding.ItemEpisodeNestedBinding
import com.jayasuryat.episodelist.databinding.ItemSeasonBinding

internal class EpisodesListAdapter(
    private val onSeasonClicked: (EpisodeListData.Season) -> Unit,
    private val onEpisodeClicked: (EpisodeListData.Episode) -> Unit,
) : ListAdapter<EpisodeListData, EpisodesListAdapter.BaseEpisodeHolder>(difCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseEpisodeHolder =
        when (viewType) {

            VIEW_TYPE_SEASON -> ItemSeasonBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
                .let(::SeasonViewHolder)


            VIEW_TYPE_EPISODE -> ItemEpisodeNestedBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
                .let(::EpisodeViewHolder)

            else -> throw IllegalArgumentException("Invalid view type : $viewType")
        }

    override fun onBindViewHolder(holder: BaseEpisodeHolder, position: Int) = when (holder) {
        is SeasonViewHolder -> (getItem(position) as EpisodeListData.Season).let(holder::bind)
        is EpisodeViewHolder -> (getItem(position) as EpisodeListData.Episode).let(holder::bind)
    }

    internal sealed class BaseEpisodeHolder(view: View) : RecyclerView.ViewHolder(view)

    internal inner class SeasonViewHolder(private val item: ItemSeasonBinding) :
        BaseEpisodeHolder(item.root) {

        init {
            item.cvRoot.shrinkOnClick {
                val position = bindingAdapterPosition
                if (position >= 0) onSeasonClicked(getItem(position) as EpisodeListData.Season)
            }
        }

        fun bind(data: EpisodeListData.Season) {
            item.tvSeason.text = data.seasonName
        }
    }

    internal inner class EpisodeViewHolder(private val item: ItemEpisodeNestedBinding) :
        BaseEpisodeHolder(item.root) {

        init {
            item.cvRoot.shrinkOnClick {
                val position = bindingAdapterPosition
                if (position >= 0) onEpisodeClicked(getItem(position) as EpisodeListData.Episode)
            }
        }

        fun bind(data: EpisodeListData.Episode) {
            item.cEpisodeNumber.text = data.episodeNumber.toString()
            item.tvEpisodeName.text = data.episodeName
        }
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is EpisodeListData.Season -> VIEW_TYPE_SEASON
        is EpisodeListData.Episode -> VIEW_TYPE_EPISODE
    }

    companion object {

        private const val VIEW_TYPE_SEASON: Int = 1
        private const val VIEW_TYPE_EPISODE: Int = 2

        private val difCallback = object : DiffUtil.ItemCallback<EpisodeListData>() {

            override fun areItemsTheSame(
                oldItem: EpisodeListData,
                newItem: EpisodeListData
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: EpisodeListData,
                newItem: EpisodeListData
            ): Boolean =
                oldItem == newItem
        }
    }
}