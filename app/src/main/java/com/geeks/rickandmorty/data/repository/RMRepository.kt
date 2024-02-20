package com.geeks.rickandmorty.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.geeks.rickandmorty.data.api.CartoonApiService
import com.geeks.rickandmorty.data.base.BaseRepository
import com.geeks.rickandmorty.data.model.Character
import com.geeks.rickandmorty.data.model.CharacterResponse
import com.geeks.rickandmorty.utils.Resource
import kotlinx.coroutines.Dispatchers
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class RMRepository (private val api: CartoonApiService):BaseRepository() {
    fun getCharacters(): LiveData<Resource<CharacterResponse>> = doRequest{
        api.getCharacters()
    }

    fun getCharacter(id: Int): LiveData<Resource<Character>> = doRequest {
        api.getCharacter(id)
    }
}
