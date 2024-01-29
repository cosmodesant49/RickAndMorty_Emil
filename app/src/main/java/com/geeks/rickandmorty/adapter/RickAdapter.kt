package com.geeks.rickandmorty.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.geeks.rickandmorty.data.model.Character
import com.geeks.rickandmorty.databinding.ItemCharacterCardBinding


class RickAdapter(private val characters: List<Character>) :
    RecyclerView.Adapter<RickAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCharacterCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    inner class ViewHolder(private val binding: ItemCharacterCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            binding.tvName.text = character.name
            binding.tvStatus.text = character.status
            Glide.with(binding.ivChar).load(character.image)
                .into(binding.ivChar)
        }
    }
}