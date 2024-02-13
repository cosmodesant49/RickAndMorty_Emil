package com.geeks.rickandmorty.di

import com.geeks.rickandmorty.BuildConfig
import com.geeks.rickandmorty.data.RMRepository
import com.geeks.rickandmorty.data.api.CartoonApiService
import com.geeks.rickandmorty.ui.character_activity.CharacterViewModel
import com.geeks.rickandmorty.ui.second_activity.DetailsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val cartoonModule = module {
    single {
        provideInterceptor()
    }
    factory {
        provideOkHttpClient(get())
    }
    single {
        provideRetrofit(get())
    }
    single {
        provideCartoonApiService(get())
    }
    single {
        RMRepository(get())
    }
    viewModel{
        CharacterViewModel(get())
    }
    viewModel{
        DetailsViewModel(get())
    }
}
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .callTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

    fun provideInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    fun provideCartoonApiService(retrofit: Retrofit): CartoonApiService =
        retrofit.create(CartoonApiService::class.java)