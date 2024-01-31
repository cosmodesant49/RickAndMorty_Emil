package com.geeks.rickandmorty.character_activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.geeks.rickandmorty.data.RMRepository
import com.geeks.rickandmorty.data.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val repository: RMRepository) : ViewModel() {

    fun getCaracters(): LiveData<List<Character>>{
        return repository.getCharacters()
    }
}
