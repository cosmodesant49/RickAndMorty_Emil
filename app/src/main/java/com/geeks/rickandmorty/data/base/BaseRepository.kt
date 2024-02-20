package com.geeks.rickandmorty.data.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.geeks.rickandmorty.data.model.Character
import com.geeks.rickandmorty.utils.Resource
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import java.io.IOException


abstract class BaseRepository {
    protected fun <T> doRequest(
        request: suspend () -> Response<T>
    ): LiveData<Resource<T>> = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val response = request.invoke()
            if (response.isSuccessful && response.body() != null && response.code() in 200..300){
            emit(Resource.Success(response.body()!!))
            }
        } catch (io: IOException) {
            emit(Resource.Error(io.message ?: "Unknown Error!"))

        }
    }
}