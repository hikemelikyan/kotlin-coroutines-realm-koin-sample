package com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote

import com.armboldmind.kotlincoroutinesrealmkoin.model.ModelClass
import retrofit2.Response
import retrofit2.http.GET

interface IMainService {
    @GET("todos/1")
    suspend fun testCallAsync(): Response<ModelClass>
}