package com.jayasuryat.locationlist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jayasuryat.base.shrinkOnClick
import com.jayasuryat.locationlist.databinding.ItemLocationBinding
import com.jayasuryat.locationlist.domain.model.Location

internal class LocationListAdapter(
    private val onItemSelected: (location: Location, name: View, container: View) -> Unit,
) : PagingDataAdapter<Location, LocationListAdapter.CharacterViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
        ItemLocationBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
            .let(::CharacterViewHolder)

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class CharacterViewHolder(private val item: ItemLocationBinding) :
        RecyclerView.ViewHolder(item.root) {

        init {
            item.cvRoot.shrinkOnClick {
                val position = bindingAdapterPosition
                if (position >= 0) {
                    val data = getItem(position) ?: return@shrinkOnClick
                    onItemSelected(data, item.tvName, item.cvRoot)
                }
            }
        }

        fun bind(data: Location?) {
            data ?: return
            item.cId.text = data.id.toString()
            item.tvName.text = data.name
            item.tvDimension.text = data.dimension
            item.tvName.transitionName = "name_#${data.id}"
            item.cvRoot.transitionName = "container_#${data.id}"
        }
    }

    private companion object {

        private val diffCallback = object : DiffUtil.ItemCallback<Location>() {

            override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean =
                oldItem == newItem
        }
    }
}