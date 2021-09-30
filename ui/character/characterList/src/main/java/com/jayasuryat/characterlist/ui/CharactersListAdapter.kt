package com.jayasuryat.characterlist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jayasuryat.base.shrinkOnClick
import com.jayasuryat.characterlist.UiUtils.loadImage
import com.jayasuryat.characterlist.databinding.ItemCharacterBinding

internal class CharactersListAdapter(
    private val onItemSelected: (character: CharacterDef, sharedView: View) -> Unit,
) : ListAdapter<CharacterDef, CharactersListAdapter.CharacterViewHolder>(diffCallback) {

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
                if (position >= 0) onItemSelected(getItem(position), item.tvName)
            }
        }

        fun bind(data: CharacterDef) {
            item.tvName.text = data.name
            item.ivAvatar.loadImage(data.imageUrl)
            item.tvName.transitionName = data.id.toString()
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

internal data class CharacterDef(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val lastSelectedCharacter: Boolean,
)