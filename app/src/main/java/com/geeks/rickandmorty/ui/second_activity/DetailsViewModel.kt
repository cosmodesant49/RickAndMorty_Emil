package com.geeks.rickandmorty.ui.second_activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.geeks.rickandmorty.data.repository.RMRepository
import com.geeks.rickandmorty.data.model.Character
import com.geeks.rickandmorty.utils.Resource


class DetailsViewModel (private val repository: RMRepository) : ViewModel() {

    fun getData(id: Int): LiveData<Resource<Character>> {
        return repository.getCharacter(id)
    }
}
