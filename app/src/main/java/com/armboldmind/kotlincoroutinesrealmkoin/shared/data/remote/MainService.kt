package com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote

import androidx.lifecycle.MutableLiveData
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.BaseService
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.IMainService

class MainService(private val mService: IMainService) : BaseService() {

    suspend fun testCall(liveData: MutableLiveData<Any>) {
        autoCallAsync(mService::testCallAsync, liveData)
    }
}