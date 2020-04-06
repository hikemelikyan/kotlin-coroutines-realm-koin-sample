package com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.services.root

import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.newtorking.NetworkError
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.newtorking.ResponseCallBack
import retrofit2.HttpException
import retrofit2.Response

open class BaseService {

    suspend fun <T : Any> callAsync(method: suspend () -> Response<T>, callback: ResponseCallBack<T>) {
        try {
            val response = method()

            if (!response.isSuccessful) {
                callback.onFailure(NetworkError(HttpException(response)))
                return
            }

            callback.onSuccess(response.body())

        } catch (e: Throwable) {
            callback.onFailure(NetworkError(e))
        }
    }

}