package com.armboldmind.kotlincoroutinesrealmkoin.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.armboldmind.kotlincoroutinesrealmkoin.model.TestModel
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.newtorking.NetworkError
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.newtorking.ResponseCallBack
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.services.TestService
import com.armboldmind.kotlincoroutinesrealmkoin.shared.di.scopes.TestScope
import com.armboldmind.kotlincoroutinesrealmkoin.shared.helpers.scopeHelper.getScope
import com.armboldmind.kotlincoroutinesrealmkoin.viewmodel.base.BaseViewModel
import kotlinx.coroutines.launch

@TestScope
class MainViewModel(application: Application) : BaseViewModel(application) {

    private val mService: TestService? = getScope()?.get()

    val liveData: MutableLiveData<TestModel> = MutableLiveData()

    fun testCall(id: String) {
        coroutineScope.launch {
            mService?.testCall(id, object : ResponseCallBack<TestModel> {
                override fun onSuccess(data: TestModel?) {
                    liveData.postValue(data)
                }

                override fun onFailure(error: NetworkError) {

                }
            })
        }
    }
}