package com.armboldmind.kotlincoroutinesrealmkoin.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.services.AuthorizationService
import com.armboldmind.kotlincoroutinesrealmkoin.shared.di.scopes.AuthorizationScope
import com.armboldmind.kotlincoroutinesrealmkoin.viewmodel.base.BaseViewModel
import kotlinx.coroutines.launch

@AuthorizationScope
class AuthorizationViewModel(application: Application) : BaseViewModel(application) {

    private var mService: AuthorizationService =
        getScope(this@AuthorizationViewModel.javaClass)!!.get()

    val liveData: MutableLiveData<Any> = MutableLiveData()

    fun testCall() {
        coroutineScope.launch {
            mService.testCall(liveData)
        }
    }
}