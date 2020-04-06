package com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.newtorking

interface ResponseCallBack<T> {

    fun onSuccess(data: T?)

    fun onFailure(error: NetworkError)
}