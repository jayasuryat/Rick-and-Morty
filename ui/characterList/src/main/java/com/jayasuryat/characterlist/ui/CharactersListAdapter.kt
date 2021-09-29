package com.jayasuryat.characterlist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jayasuryat.base.shrinkOnClick
import com.jayasuryat.characterlist.UiUtils.loadImage
import com.jayasuryat.characterlist.databinding.ItemCharacterBinding
import com.jayasuryat.data.models.domain.Character

class CharactersListAdapter : ListAdapter<Character,
        CharactersListAdapter.CharacterViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
        ItemCharacterBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
            .let(::CharacterViewHolder)

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class CharacterViewHolder(private val item: ItemCharacterBinding) :
        RecyclerView.ViewHolder(item.root) {

        init {
            item.cvRoot.shrinkOnClick { }
        }

        fun bind(data: Character) {
            item.tvName.text = data.name
            item.ivAvatar.loadImage(data.image)
        }
    }

    private companion object {

        private val diffCallback = object : DiffUtil.ItemCallback<Character>() {

            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem == newItem
        }
    }
}