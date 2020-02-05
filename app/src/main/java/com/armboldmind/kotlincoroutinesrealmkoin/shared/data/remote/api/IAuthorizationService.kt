package com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.api

import com.armboldmind.kotlincoroutinesrealmkoin.model.ModelClass
import retrofit2.Response
import retrofit2.http.GET

interface IAuthorizationService {
    @GET("todos/2")
    suspend fun testCallAsync(): Response<ModelClass>
}