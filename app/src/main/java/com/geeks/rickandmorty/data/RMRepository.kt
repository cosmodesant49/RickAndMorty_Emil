package com.geeks.rickandmorty.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.geeks.rickandmorty.data.api.CartoonApiService
import com.geeks.rickandmorty.data.model.BaseResponse
import com.geeks.rickandmorty.data.model.Character
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RMRepository @Inject constructor(private val api: CartoonApiService) {
    fun getCharacters(): MutableLiveData<List<Character>> {
        val characters = MutableLiveData<List<Character>>()

        api.getCharacters().enqueue(object : Callback<BaseResponse<Character>> {
            override fun onResponse(
                call: Call<BaseResponse<Character>>,
                response: Response<BaseResponse<Character>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let {
                        characters.postValue(it.results)
                    }
                }
            }

            override fun onFailure(call: Call<BaseResponse<Character>>, t: Throwable) {
                Log.e("ololo", t.message.toString())
            }

        })
        return characters
    }
}
