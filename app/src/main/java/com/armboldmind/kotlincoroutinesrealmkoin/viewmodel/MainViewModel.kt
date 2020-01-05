package com.armboldmind.kotlincoroutinesrealmkoin.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.armboldmind.kotlincoroutinesrealmkoin.viewmodel.base.BaseViewModel
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : BaseViewModel(application) {


    val liveData: MutableLiveData<Any> = MutableLiveData()

    fun testCall() {
        scope.launch {
            mService.testCall(liveData)
        }
    }
}