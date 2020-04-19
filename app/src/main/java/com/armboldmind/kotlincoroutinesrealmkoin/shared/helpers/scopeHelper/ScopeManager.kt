package com.armboldmind.kotlincoroutinesrealmkoin.shared.helpers.scopeHelper

import android.app.Application
import com.armboldmind.kotlincoroutinesrealmkoin.shared.di.scopes.root.KoinScope
import com.armboldmind.kotlincoroutinesrealmkoin.viewmodel.base.BaseViewModel
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

object ScopeManager {

    lateinit var applicationContext: Application

    fun <T : BaseViewModel> getScope(classJav: Class<T>): Scope? {
        if (!this::applicationContext.isInitialized)
            throwError("Initialize Scope Manager in your application class.")

        val callerAnnotations = ArrayList(classJav.annotations.asList())

        val allScopes = ArrayList(ScopeRegister::class.java.annotations.asList())

        allScopes.retainAll(callerAnnotations)

        if (!allScopes.isNullOrEmpty()) {
            if (allScopes.size > 1)
                throwError("Check scopes, scope must be only one")

            val scopeAnnotations = allScopes[0].annotationClass.annotations as ArrayList

            val a = scopeAnnotations.find {
                it is KoinScope
            }
            if (a != null) {
                return applicationContext.getKoin().getOrCreateScope(allScopes[0].javaClass.name, named((a as KoinScope).scopeName))
            }
        }
        throwError("Unable to find scope")
    }

    private fun throwError(message: String): Nothing {
        throw ScopeException(message)
    }
}