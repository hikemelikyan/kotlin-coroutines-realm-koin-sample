package com.armboldmind.kotlincoroutinesrealmkoin.shared.di.modules

import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.api.IAuthorizationService
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.api.IMainService
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.services.AuthorizationService
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.services.MainService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var NetModule = module {

    scope(named("main_scope")) {
        scoped {
            MainService(get())
        }
    }

    scope(named("authorization_scope")) {
        scoped {
            AuthorizationService(get())
        }
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(IMainService::class.java)
    }

    single {
        get<Retrofit>().create(IAuthorizationService::class.java)
    }
}