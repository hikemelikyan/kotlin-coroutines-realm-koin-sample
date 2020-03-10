package com.armboldmind.kotlincoroutinesrealmkoin.viewmodel.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.armboldmind.kotlincoroutinesrealmkoin.shared.di.scopes.AuthorizationScope
import com.armboldmind.kotlincoroutinesrealmkoin.shared.di.scopes.MainScope
import com.armboldmind.kotlincoroutinesrealmkoin.shared.di.scopes.PagingScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    private var app = application
    protected val coroutineScope: CoroutineScope by lazy { CoroutineScope(Job() + Dispatchers.Default) }

    @MainScope
    private var mainScope: Scope = app.getKoin().getOrCreateScope("main", named("main_scope"))

    @AuthorizationScope
    private var authorizationScope: Scope =
        app.getKoin().getOrCreateScope("authorization", named("authorization_scope"))

    @PagingScope
    private var pageingScope: Scope =
        app.getKoin().getOrCreateScope("paging", named("paging_scope"))

    protected fun <T : BaseViewModel> getScope(classJav: Class<T>): Scope? {
        for (annotation in classJav.annotations) {
            when (annotation) {
                is MainScope -> {
                    for (scope in BaseViewModel::class.java.declaredFields) {
                        if (scope.isAnnotationPresent(MainScope::class.java)) {
                            return scope.get(this) as Scope
                        }
                    }
                }
                is AuthorizationScope -> {
                    for (scope in BaseViewModel::class.java.declaredFields) {
                        if (scope.isAnnotationPresent(AuthorizationScope::class.java)) {
                            return scope.get(this) as Scope
                        }
                    }
                }
                is PagingScope -> {
                    for (scope in BaseViewModel::class.java.declaredFields) {
                        if (scope.isAnnotationPresent(PagingScope::class.java)) {
                            return scope.get(this) as Scope
                        }
                    }
                }
            }
        }
        return null
    }
}