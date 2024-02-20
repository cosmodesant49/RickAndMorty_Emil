package com.geeks.rickandmorty.ui.character_activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geeks.rickandmorty.data.repository.RMRepository
import com.geeks.rickandmorty.data.model.Character
import com.geeks.rickandmorty.utils.Resource

class CharacterViewModel (private val repository: RMRepository) : ViewModel() {
    private val _charactersLv =  MutableLiveData<Resource<List<Character>>>()
    val charactersLv: LiveData<Resource<List<Character>>> = _charactersLv

    fun getCaracters() {
        _charactersLv.value = repository.getCharacters().value
    }
}
