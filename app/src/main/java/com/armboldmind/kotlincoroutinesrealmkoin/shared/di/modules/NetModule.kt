package com.armboldmind.kotlincoroutinesrealmkoin.shared.di.modules

import android.app.Application
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.api.IAuthorizationService
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.api.IMainService
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.services.AuthorizationService
import com.armboldmind.kotlincoroutinesrealmkoin.shared.data.remote.services.MainService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.core.context.GlobalContext
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

    scope(named("authorization_scope")) {
        scoped {
            AuthorizationService(get())
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

    single {
        OkHttpClient.Builder().addInterceptor { chain ->
            val original = chain.request()
            // Customize the request
            val request = original.newBuilder()
                .header("Content-Type", "application/json")
                .header(
                    "Authorization",
                    "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhMWZkOWNkMC1lOTNlLTQyY2YtOTk3Zi1kZWMyZjZmMjNhMWMiLCJ1bmlxdWVfbmFtZSI6Im1hbmVAbWFpbC5jb20iLCJqdGkiOiJjMTdlY2YxZi04YTliLTQwY2EtODQ4MS1jMzQ4MzFkN2I1NDciLCJpYXQiOjE1NzgzOTI4MjcsIm5iZiI6MTU3ODM5MjgyNywiZXhwIjoxNTgxMDcxMjI3LCJpc3MiOiJ3ZWJBcGkiLCJhdWQiOiJodHRwOi8vbG9jYWxob3N0OjUwMDIvIn0.MQrG7kZVCxvCwNl59sWfhbgVon-bm_-Y-u3BKOtPGoQ"
                )
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
        Cache(get<Application>().cacheDir, (10*1024*1024).toLong())
    }

    single {
        get<Retrofit>().create(IMainService::class.java)
    }

    single {
        get<Retrofit>().create(IAuthorizationService::class.java)
    }
}