package com.armboldmind.kotlincoroutinesrealmkoin.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.services.PagingService
import com.armboldmind.kotlincoroutinesrealmkoin.shared.di.scopes.PagingScope
import com.armboldmind.kotlincoroutinesrealmkoin.viewmodel.base.BaseViewModel
import kotlinx.coroutines.launch

@PagingScope
class PagingViewModel(application: Application) : BaseViewModel(application) {

    private var mService: PagingService = getScope(this@PagingViewModel.javaClass)!!.get()

    var liveData: MutableLiveData<Any> = MutableLiveData()

    fun getPageData(page: Int) {
        coroutineScope.launch {
            mService.testCall(page, "c510900dfb1e5b148e34f464453019cd", liveData)
        }
    }
}