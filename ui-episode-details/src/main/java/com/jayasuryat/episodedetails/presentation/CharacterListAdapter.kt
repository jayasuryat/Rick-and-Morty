package com.jayasuryat.episodedetails.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jayasuryat.base.shrinkOnClick
import com.jayasuryat.episodedetails.databinding.ItemEpisodeCharacterBinding
import com.jayasuryat.episodedetails.domain.model.Character
import com.jayasuryat.episodedetails.presentation.UiUtils.loadImage

class CharacterListAdapter(
    private val onCharacterClicked: (character: Character, image: View, name: View, container: View) -> Unit,
) : ListAdapter<Character, CharacterListAdapter.CharacterViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
        ItemEpisodeCharacterBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
            .let(::CharacterViewHolder)

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class CharacterViewHolder(private val item: ItemEpisodeCharacterBinding) :
        RecyclerView.ViewHolder(item.root) {

        init {
            item.cvRoot.shrinkOnClick {
                val position = bindingAdapterPosition
                if (position >= 0) {
                    onCharacterClicked(
                        getItem(position),
                        item.ivCharacter,
                        item.tvCharacterName,
                        item.cvRoot
                    )
                }
            }
        }

        fun bind(data: Character) {
            item.apply {
                ivCharacter.loadImage(data.image)
                tvCharacterName.text = data.name

                ivCharacter.transitionName = "image_#${data.id}"
                tvCharacterName.transitionName = "name_#${data.id}"
                cvRoot.transitionName = "container_#${data.id}"
            }
        }
    }

    companion object {

        private val diffCallback = object : DiffUtil.ItemCallback<Character>() {

            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem == newItem
        }
    }
}