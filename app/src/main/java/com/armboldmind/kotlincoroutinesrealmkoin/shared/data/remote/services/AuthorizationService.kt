package com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.services

import androidx.lifecycle.MutableLiveData
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.api.IAuthorizationService
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.services.root.BaseService

class AuthorizationService(private var authorizationService: IAuthorizationService) :
    BaseService() {

    suspend fun testCall(liveData: MutableLiveData<Any>) {
        autoCallAsync(authorizationService::testCallAsync, liveData)
    }
}