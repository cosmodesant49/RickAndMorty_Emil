package com.geeks.rickandmorty.ui.character_activity

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeks.rickandmorty.data.base.BaseActivity
import com.geeks.rickandmorty.ui.second_activity.DetailsActivity
import com.geeks.rickandmorty.data.model.Character
import com.geeks.rickandmorty.databinding.ActivityMainBinding
import com.geeks.rickandmorty.keys.CharacterKeys
import com.geeks.rickandmorty.ui.adapter.CharacterAdapter
import com.geeks.rickandmorty.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<CharacterViewModel>()
    private val cartoonAdapter =
        CharacterAdapter(this::onClickItem)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecycler()

        viewModel.getCharacters()
        viewModel.charactersLv.stateObserver(
            success = {
                cartoonAdapter.submitList(it.results)
            },
            state = {
                binding.progressIndicator.isVisible = it is Resource.Loading
            }
        )

    }

    private fun setUpRecycler() = with(binding.rvRick) {
        layoutManager = LinearLayoutManager(
            this@CharacterActivity,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = cartoonAdapter
    }

    private fun onClickItem(characterId: Int) {
        startActivity(Intent(this, DetailsActivity::class.java).apply {
            putExtra(DetailsActivity.CHARACTER_ID_ARG, characterId)
        })
    }
}

