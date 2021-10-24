package com.jayasuryat.characterlist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jayasuryat.base.shrinkOnClick
import com.jayasuryat.characterlist.UiUtils.loadImage
import com.jayasuryat.characterlist.databinding.ItemCharacterBinding

internal class CharactersListAdapter(
    private val onItemSelected: (character: CharacterDef, image: View, name: View, container: View) -> Unit,
) : PagingDataAdapter<CharacterDef, CharactersListAdapter.CharacterViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
        ItemCharacterBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
            .let(::CharacterViewHolder)

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class CharacterViewHolder(private val item: ItemCharacterBinding) :
        RecyclerView.ViewHolder(item.root) {

        init {
            item.cvRoot.shrinkOnClick {
                val position = bindingAdapterPosition
                if (position >= 0) {
                    val data = getItem(position) ?: return@shrinkOnClick
                    onItemSelected(data, item.cvAvatar, item.tvName, item.cvRoot)
                }
            }
        }

        fun bind(data: CharacterDef?) {
            data ?: return
            item.tvName.text = data.name
            item.ivAvatar.loadImage(data.imageUrl)
            item.cvAvatar.transitionName = "avatar_#${data.id}"
            item.tvName.transitionName = "name_#${data.id}"
            item.cvRoot.transitionName = "container_#${data.id}"
        }
    }

    private companion object {

        private val diffCallback = object : DiffUtil.ItemCallback<CharacterDef>() {

            override fun areItemsTheSame(oldItem: CharacterDef, newItem: CharacterDef): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: CharacterDef, newItem: CharacterDef): Boolean =
                oldItem == newItem
        }
    }
}