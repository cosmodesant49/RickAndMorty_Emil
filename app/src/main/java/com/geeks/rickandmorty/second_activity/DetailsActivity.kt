package com.geeks.rickandmorty.second_activity

import com.geeks.rickandmorty.R
import android.os.Bundle
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

        val episodeData = arrayOf(
            it.name,
            it.species,
            it.gender,
            it.location.name,
            it.origin.name,
        )

        val spinner1 = binding.spinner1


        val adapter = ArrayAdapter(
            this@DetailsActivity,
            R.layout.custom_spinner_dropdown_item,
            R.id.tv_custom_spinner,
            episodeData
        )

        spinner1.adapter = adapter

        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                spinner1.setSelection(0, true)
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
            }
        }
    }

    companion object {
        const val CHARACTER_ID_ARG = "characterIdArg"
    }
}
