package com.geeks.rickandmorty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.geeks.rickandmorty.SecondActivity.DetailsActivity
import com.geeks.rickandmorty.adapter.RickAdapter
import com.geeks.rickandmorty.data.model.Character
import com.geeks.rickandmorty.databinding.ActivityMainBinding
import com.geeks.rickandmorty.keys.CharacterKeys
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getCaracters().observe(this) {
            val adapter = RickAdapter(this::onClickItem, it )
            binding.rvRick.adapter = adapter
        }

    }

    private fun onClickItem(character: Character) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(CharacterKeys.CHARACTER_ID_ARG, character.id)
        startActivity(intent)
    }


}

