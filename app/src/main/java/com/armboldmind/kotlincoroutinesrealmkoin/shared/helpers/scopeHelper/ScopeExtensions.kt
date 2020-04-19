package com.armboldmind.kotlincoroutinesrealmkoin.shared.helpers.scopeHelper

import android.app.Application
import com.armboldmind.kotlincoroutinesrealmkoin.viewmodel.base.BaseViewModel
import org.koin.core.scope.Scope

/**
 * function initializes application context in [ScopeManager] class
 * */
fun initScopeManager(application: Application): Application {
    ScopeManager.applicationContext = application
    return ScopeManager.applicationContext
}

/**
 * function gets type of viewModel, and passes tu [ScopeManager] class
 * */
inline fun <reified T : BaseViewModel> T.getScope(): Scope? {
    return ScopeManager.getScope(T::class.java)
}