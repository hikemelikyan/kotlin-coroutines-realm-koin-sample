package com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private var instance: Retrofit? = null

    fun getInstance(): Retrofit {
        return if (instance != null) {
            instance!!
        } else {
            instance = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            instance!!
        }
    }
}