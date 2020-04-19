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
    protected val coroutineScope: CoroutineScope by lazy { CoroutineScope(Job() + Dispatchers.Default) }
}