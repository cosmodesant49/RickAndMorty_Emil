package com.geeks.rickandmorty.SecondActivity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.geeks.rickandmorty.data.model.Character
import com.geeks.rickandmorty.databinding.ActivityDetailsBinding
import com.geeks.rickandmorty.keys.CharacterKeys
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var viewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[DetailsViewModel::class.java]

        val id = intent.getIntExtra(CharacterKeys.CHARACTER_ID_ARG, 0)

        viewModel.getData(id).observe(this){
            it?.let {
                setCharacterData(it)
            }

        }

    }

    private fun setCharacterData(it: Character) = with(binding){
        Log.e("ololo", "Data is not null")
        tvCharacterName.text = it.name
        tvStatus.text = it.status
        Glide.with(imageCharacter).load(it.image).into(imageCharacter)
    }
}