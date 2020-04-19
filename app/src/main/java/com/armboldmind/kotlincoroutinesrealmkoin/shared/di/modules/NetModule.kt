package com.armboldmind.kotlincoroutinesrealmkoin.shared.di.modules

import android.app.Application
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.api.IAuthorizationService
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.api.IMainService
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.api.IPagingService
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.api.ITestService
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.services.AuthorizationService
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.services.MainService
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.services.PagingService
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.services.TestService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

var NetModule = module {

    scope(named("main_scope")) {
        scoped {
            MainService(get())
        }
    }

    scope(named("paging_scope")) {
        scoped {
            PagingService(get())
        }
    }

    scope(named("authorization_scope")) {
        scoped {
            AuthorizationService(get())
        }
    }

    scope(named("test_scope")) {
        scoped {
            TestService(get())
        }
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(get())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single(named("paging")) {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .client(get())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single(named("testApplication")) {
        Retrofit.Builder()
            .baseUrl("http://click-safety.com/")
            .client(get())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        OkHttpClient.Builder().addInterceptor { chain ->
            val original = chain.request()
            // Customize the request
            val request = original.newBuilder()
                .header("Content-Type", "application/json")
                .build()
            val response = chain.proceed(request)
            response.cacheResponse()
            // Customize or return the response
            response
        }
            .cache(get())
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    single {
        Cache(get<Application>().cacheDir, (10 * 1024 * 1024).toLong())
    }

    single {
        get<Retrofit>().create(IMainService::class.java)
    }

    single {
        get<Retrofit>().create(IAuthorizationService::class.java)
    }

    single {
        get<Retrofit>(named("paging")).create(IPagingService::class.java)
    }

    single {
        get<Retrofit>(named("testApplication")).create(ITestService::class.java)
    }
}