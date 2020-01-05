package com.armboldmind.kotlincoroutinesrealmkoin.viewmodel.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.IMainService
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.MainService
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    val scope: CoroutineScope by lazy { CoroutineScope(Job() + Dispatchers.Default) }

    val mService: MainService by lazy {
        val callAssistant = RetrofitInstance.getInstance()
        MainService(callAssistant.create(IMainService::class.java))
    }
}