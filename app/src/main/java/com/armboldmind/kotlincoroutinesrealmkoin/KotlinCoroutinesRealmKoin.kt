package com.armboldmind.kotlincoroutinesrealmkoin

import android.app.Application
import com.armboldmind.kotlincoroutinesrealmkoin.shared.di.modules.AuthorizationModule
import com.armboldmind.kotlincoroutinesrealmkoin.shared.di.modules.NetModule
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KotlinCoroutinesRealmKoin : Application() {

    companion object {
        private lateinit var mInstance: KotlinCoroutinesRealmKoin

        fun getInstance(): KotlinCoroutinesRealmKoin {
            return mInstance
        }
    }

    override fun onCreate() {
        super.onCreate()

        mInstance = this

        Realm.init(this)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder().build())

        startKoin {
            androidContext(this@KotlinCoroutinesRealmKoin)
            printLogger()
            modules(arrayListOf(NetModule, AuthorizationModule))
        }
    }
}