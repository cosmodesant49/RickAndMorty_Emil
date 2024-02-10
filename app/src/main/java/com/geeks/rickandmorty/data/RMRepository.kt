package com.geeks.rickandmorty.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geeks.rickandmorty.data.api.CartoonApiService
import com.geeks.rickandmorty.data.model.Character
import com.geeks.rickandmorty.data.model.CharacterResponse
import com.geeks.rickandmorty.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RMRepository @Inject constructor(private val api: CartoonApiService) {
    fun getCharacters(): MutableLiveData<Resource<List<Character>>> {
        val characters = MutableLiveData<Resource<List<Character>>>()
        characters.postValue((Resource.Loading()))

        api.getCharacters().enqueue(object : Callback<CharacterResponse/*<Character>*/> {
            override fun onResponse(
                call: Call<CharacterResponse/*<Character>*/>,
                response: Response<CharacterResponse/*<Character>*/>
            ) {
                if (response.isSuccessful && response.body() != null && response.code() in 200 ..300) {
                    response.body()?.let {
                        characters.postValue(
                            Resource.Success(it.results)
                        )
                    }
                }
            }

            override fun onFailure(call: Call<CharacterResponse/*<Character>*/>, t: Throwable) {
                characters.postValue(Resource.Error(t.message?:"Unknown error!"))
            }

        })
        return characters
    }
    fun getCharacter(id: Int): LiveData<Character> {
        val characterLv = MutableLiveData<Character>()
        api.getCharacter(id).enqueue(object : Callback<Character> {
            override fun onResponse(
                call: Call<Character>,
                response: Response<Character>
            ) {
                response.body()?.let {
                    characterLv.postValue(it)
                }
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                Log.e("ololo", t.message.toString())
            }
        })
        return characterLv
    }
}
