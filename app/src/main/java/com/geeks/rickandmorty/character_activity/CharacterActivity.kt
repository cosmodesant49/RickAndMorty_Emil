package com.geeks.rickandmorty.character_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeks.rickandmorty.second_activity.DetailsActivity
import com.geeks.rickandmorty.adapter.CartoonAdapter
import com.geeks.rickandmorty.data.model.Character
import com.geeks.rickandmorty.databinding.ActivityMainBinding
import com.geeks.rickandmorty.keys.CharacterKeys
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[CharacterViewModel::class.java]
    }
    private val cartoonAdapter =
        CartoonAdapter(this::onClickItem)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setpRecycler()

        viewModel.getCaracters().observe(this) { characters ->
            cartoonAdapter.setCharacters(characters)
        }

    }

    private fun setpRecycler() = with(binding.rvRick) {
        layoutManager = LinearLayoutManager(
            this@CharacterActivity,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter=cartoonAdapter
    }

    private fun onClickItem(characterId: Int) {
        startActivity(Intent(this,DetailsActivity::class.java).apply {
            putExtra(DetailsActivity.CHARACTER_ID_ARG,characterId)
        })
    }
}

