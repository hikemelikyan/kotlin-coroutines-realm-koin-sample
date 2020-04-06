package com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.services

import com.armboldmind.kotlincoroutinesrealmkoin.model.ModelClass
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.api.IAuthorizationService
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.newtorking.ResponseCallBack
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.services.root.BaseService

class AuthorizationService(private var authorizationService: IAuthorizationService) :
    BaseService() {

    suspend fun testCall(callBack: ResponseCallBack<ModelClass>) {
        callAsync(authorizationService::testCallAsync, callBack)
    }
}