package com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.services

import com.armboldmind.kotlincoroutinesrealmkoin.model.TestModel
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.api.ITestService
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.newtorking.ResponseCallBack
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.services.root.BaseService

class TestService(private val mService: ITestService) : BaseService() {

    suspend fun testCall(id: String, callBack: ResponseCallBack<TestModel>) {
        callAsync({ mService.testCallAsync(id) }, callBack)
    }
}