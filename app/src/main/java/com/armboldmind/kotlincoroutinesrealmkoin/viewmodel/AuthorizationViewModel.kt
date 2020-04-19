package com.armboldmind.kotlincoroutinesrealmkoin.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.armboldmind.kotlincoroutinesrealmkoin.model.ModelClass
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.newtorking.NetworkError
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.newtorking.ResponseCallBack
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.services.AuthorizationService
import com.armboldmind.kotlincoroutinesrealmkoin.shared.di.scopes.AuthorizationScope
import com.armboldmind.kotlincoroutinesrealmkoin.shared.helpers.scopeHelper.getScope
import com.armboldmind.kotlincoroutinesrealmkoin.viewmodel.base.BaseViewModel
import kotlinx.coroutines.launch

@AuthorizationScope
class AuthorizationViewModel(application: Application) : BaseViewModel(application) {

    private var mService: AuthorizationService? = getScope()?.get()

    val liveData: MutableLiveData<ModelClass> = MutableLiveData()

    fun testCall() {
        coroutineScope.launch {
            mService?.testCall(object : ResponseCallBack<ModelClass> {
                override fun onSuccess(data: ModelClass?) {
                    liveData.postValue(data)
                }

                override fun onFailure(error: NetworkError) {

                }
            })
        }
    }
}