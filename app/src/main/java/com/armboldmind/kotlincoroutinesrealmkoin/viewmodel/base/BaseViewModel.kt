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
    private var authorizationScope: Scope = app.getKoin().getOrCreateScope("authorization", named("authorization_scope"))

    @PagingScope
    private var pageingScope: Scope = app.getKoin().getOrCreateScope("paging", named("paging_scope"))

    /**
     * custom scopes, using java reflection
     * */

    protected fun <T : BaseViewModel> getScope(classJav: Class<T>): Scope? {
        val annotations = ArrayList(classJav.annotations.asList())
        var sameAnnotations: ArrayList<Annotation>

        for (field in BaseViewModel::class.java.declaredFields) {
            sameAnnotations = ArrayList(field.annotations.asList())
            sameAnnotations.retainAll(annotations)
            if (!sameAnnotations.isNullOrEmpty()) {
                return field.get(this) as Scope
            } else {
                sameAnnotations = annotations
            }
        }

        return null
    }
}