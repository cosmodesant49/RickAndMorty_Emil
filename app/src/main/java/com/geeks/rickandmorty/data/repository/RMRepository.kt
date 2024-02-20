package com.geeks.rickandmorty.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.geeks.rickandmorty.data.api.CartoonApiService
import com.geeks.rickandmorty.data.base.BaseRepository
import com.geeks.rickandmorty.data.model.Character
import com.geeks.rickandmorty.data.model.CharacterResponse
import com.geeks.rickandmorty.utils.Resource
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class RMRepository (private val api: CartoonApiService):BaseRepository() {
    fun getCharacters(): LiveData<Resource<List<Character>>> = doRequest{
        api.getCharacters().results
    }

    fun getCharacter(id: Int): LiveData<Resource<Character>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                val response = api.getCharacter(id)
                if (response.isSuccessful && response.body() != null && response.code() in 200..300) {
                    emit(Resource.Success(response.body()!!))
                }
            } catch (io: IOException) {
                emit(Resource.Error(io.message ?: "Unknown Error!"))
            }
        }
    }
}
