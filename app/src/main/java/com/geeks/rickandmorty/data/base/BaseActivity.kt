package com.geeks.rickandmorty.data.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import com.geeks.rickandmorty.utils.Resource

abstract class BaseActivity : AppCompatActivity() {
    fun <T> LiveData<Resource<T>>.stateObserver(
        success: (data: T) -> Unit,
        state: ((res: Resource<T>) -> Unit)? = null
    ) {
        observe(this@BaseActivity) { response ->
            if (state != null && response != null) {
                state(response)
            }
            when (response) {
                is Resource.Error -> {
                    response.message.let {
                        Toast.makeText(this@BaseActivity, response.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                is Resource.Loading -> {}
                is Resource.Success -> {
                    if (response.data != null) {
                        success(response.data)
                    }
                }
            }
        }
    }
}