package com.geeks.rickandmorty.second_activity

import android.R
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.geeks.rickandmorty.data.model.Character
import com.geeks.rickandmorty.databinding.ActivityDetailsBinding
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

        val id = intent.getIntExtra(CHARACTER_ID_ARG, 0)

        viewModel.getData(id).observe(this) {
            it?.let {
                setCharacterData(it)
            }

        }

    }

    private fun setCharacterData(it: Character) = with(binding) {
        tvCharacterName.text = it.name
        tvSpecies.text = it.species
        tvStatus.text = it.status
        tvGenderAnswer.text = it.gender
        tvLastKnowLocationAnswer.text = it.location.name
        tvFirstSeenInAnswer.text = it.origin.name
        // Glide.with(imageCharacter).load(it.image)./*circleCrop().*/into(imageCharacter)
        val episodeData = arrayOf(
            it.name,
            it.species,
            it.gender,
            it.location.name,
            it.origin.name,
        )


        val spinner1 = binding.spinner1
        val adapter =
            ArrayAdapter(this@DetailsActivity, android.R.layout.simple_spinner_item, episodeData)
        spinner1.adapter = adapter

        spinner1.setOnTouchListener { _, event ->
            spinner1.performClick()
            true
        }
    }

    companion object {
        const val CHARACTER_ID_ARG = "characterIdArg"
    }
}