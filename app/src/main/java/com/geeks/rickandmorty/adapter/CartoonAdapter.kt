package com.geeks.rickandmorty.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.geeks.rickandmorty.R
import com.geeks.rickandmorty.data.model.Character
import com.geeks.rickandmorty.databinding.ItemCharacterCardBinding


class CartoonAdapter(
    private val onCharacterClick: (Int) -> Unit
) : RecyclerView.Adapter<CartoonAdapter.CharacterViewHolder>(
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

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characters[position])
    }
    fun setCharacters(list: List<Character>){
        characters = list
        notifyDataSetChanged()
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

            when (character.status) {
                "Alive" -> binding.imgCircleStatus.setBackgroundResource(R.drawable.circle_green)
                "Dead" -> binding.imgCircleStatus.setBackgroundResource(R.drawable.circle_red)
                "unknown" -> binding.imgCircleStatus.setBackgroundResource(R.drawable.circle)
            }
        }
    }
}