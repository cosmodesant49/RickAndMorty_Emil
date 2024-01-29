package com.geeks.rickandmorty

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geeks.rickandmorty.data.RMRepository
import com.geeks.rickandmorty.data.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: RMRepository) : ViewModel() {

    fun getCaracters(): LiveData<List<Character>>{
        return repository.getCharacters()
    }
}
