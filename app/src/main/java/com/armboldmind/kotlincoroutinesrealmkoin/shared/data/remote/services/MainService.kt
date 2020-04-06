package com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.services

import com.armboldmind.kotlincoroutinesrealmkoin.model.ModelClass
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.api.IMainService
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.newtorking.ResponseCallBack
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.services.root.BaseService

class MainService(private val mService: IMainService) : BaseService() {

    suspend fun testCall(callBack: ResponseCallBack<ModelClass>) {
        callAsync(mService::testCallAsync, callBack)
    }
}