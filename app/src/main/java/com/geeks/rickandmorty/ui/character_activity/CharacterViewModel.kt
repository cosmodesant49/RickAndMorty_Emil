package com.geeks.rickandmorty.ui.character_activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.geeks.rickandmorty.data.RMRepository
import com.geeks.rickandmorty.data.model.Character
import com.geeks.rickandmorty.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class CharacterViewModel (private val repository: RMRepository) : ViewModel() {

    fun getCaracters(): LiveData<Resource<List<Character>>>{
        return repository.getCharacters()
    }
}
