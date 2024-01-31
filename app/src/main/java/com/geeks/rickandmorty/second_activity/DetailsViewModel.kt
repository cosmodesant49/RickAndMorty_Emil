package com.geeks.rickandmorty.second_activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.geeks.rickandmorty.data.RMRepository
import com.geeks.rickandmorty.data.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: RMRepository) : ViewModel() {

    fun getData(id: Int): LiveData<Character> {
        return repository.getCharacter(id)
    }
}
