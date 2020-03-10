package com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.services

import androidx.lifecycle.MutableLiveData
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.api.IPagingService
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.services.root.BaseService

class PagingService(private var mService: IPagingService) : BaseService() {

    suspend fun testCall(page: Int, apiKey: String, liveData: MutableLiveData<Any>) {
//        autoCallAsync({ mService.getPagedData(page, apiKey) }, liveData)
    }
}