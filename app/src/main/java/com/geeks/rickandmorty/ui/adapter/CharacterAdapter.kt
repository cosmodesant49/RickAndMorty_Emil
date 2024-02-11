package com.geeks.rickandmorty.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.geeks.rickandmorty.utils.CharacterStatus
import com.geeks.rickandmorty.data.model.Character
import com.geeks.rickandmorty.databinding.ItemCharacterCardBinding
import java.util.Locale


class CharacterAdapter(
    private val onCharacterClick: (Int) -> Unit
) : ListAdapter<Character,CharacterAdapter.CharacterViewHolder>(
    CharacterDiffCallback()
) {

    private var characters = listOf<Character>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding =
            ItemCharacterCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(
            binding,
            onCharacterClick
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CharacterViewHolder(
        private val binding: ItemCharacterCardBinding,
        private val onCharacterClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            binding.tvName.text = character.name
            binding.tvStatus.text = character.status
            Glide.with(binding.ivChar).load(character.image).into(binding.ivChar)

            binding.cardView.setOnClickListener {
                onCharacterClick(character.id)
            }


            when (CharacterStatus.valueOf(character.status.uppercase(Locale.getDefault()))) {
                CharacterStatus.ALIVE -> binding.imgCircleStatus.setBackgroundResource(
                    CharacterStatus.ALIVE.drawableResource)
                CharacterStatus.DEAD -> binding.imgCircleStatus.setBackgroundResource(
                    CharacterStatus.DEAD.drawableResource)
                CharacterStatus.UNKNOWN -> binding.imgCircleStatus.setBackgroundResource(
                    CharacterStatus.UNKNOWN.drawableResource)
            }
        }
    }
}
class CharacterDiffCallback : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
        oldItem == newItem
}