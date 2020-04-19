package com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.api

import com.armboldmind.kotlincoroutinesrealmkoin.model.ModelClass
import com.armboldmind.kotlincoroutinesrealmkoin.model.TestModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ITestService {
    @GET("test_task/test_do_not_run.php")
    suspend fun testCallAsync(@Query("id") id: String): Response<TestModel>
}