package com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.services

import androidx.lifecycle.MutableLiveData
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.api.IMainService
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.services.root.BaseService

class MainService(private val mService: IMainService) : BaseService() {

    suspend fun testCall(liveData: MutableLiveData<Any>) {
        autoCallAsync(mService::testCallAsync, liveData)
    }
}