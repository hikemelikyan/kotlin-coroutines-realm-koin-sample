package com.armboldmind.kotlincoroutinesrealmkoin.shared.helpers.scopeHelper

import android.app.Application
import com.armboldmind.kotlincoroutinesrealmkoin.viewmodel.base.BaseViewModel
import org.koin.core.scope.Scope

/**
 * function initializes application context in [ScopeManager] class
 * */
fun Application.initScopeManager(): Application {
    ScopeManager.applicationContext = this
    return ScopeManager.applicationContext
}

/**
 * function gets type of viewModel, and passes to [ScopeManager] class
 * */
inline fun <reified T : BaseViewModel> T.getScope(): Scope? {
    return ScopeManager.getScope(this::class.java)
}