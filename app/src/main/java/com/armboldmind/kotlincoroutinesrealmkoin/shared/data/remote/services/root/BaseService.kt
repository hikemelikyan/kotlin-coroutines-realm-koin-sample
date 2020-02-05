package com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.services.root

import androidx.lifecycle.MutableLiveData
import retrofit2.HttpException
import retrofit2.Response

open class BaseService {

    suspend fun <T : Any> autoCallAsync(
        method: suspend () -> Response<T>,
        liveData: MutableLiveData<Any>
    ) {
        try {
            val response = method()

            if (!response.isSuccessful) {
                liveData.postValue(HttpException(response))
                return
            }

            liveData.postValue(response.body())


        } catch (e: Throwable) {
            liveData.postValue(e.message)
        }
    }

}