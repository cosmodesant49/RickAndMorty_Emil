package com.geeks.rickandmorty.data.api

import com.geeks.rickandmorty.data.model.BaseResponse
import com.geeks.rickandmorty.data.model.Character
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CartoonApiService {
    @GET("character")
    fun getCharacters(): Call<BaseResponse<Character>>

    @GET("character/{id}")
    fun getCharacter(@Path("id") id: Int):Call<Character>
}