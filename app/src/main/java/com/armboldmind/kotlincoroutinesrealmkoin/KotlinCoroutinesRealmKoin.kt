package com.armboldmind.kotlincoroutinesrealmkoin

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.armboldmind.kotlincoroutinesrealmkoin.shared.di.modules.AuthorizationModule
import com.armboldmind.kotlincoroutinesrealmkoin.shared.di.modules.NetModule
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KotlinCoroutinesRealmKoin : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder().build())

        startKoin {
            androidContext(this@KotlinCoroutinesRealmKoin)
            printLogger()
            modules(arrayListOf(NetModule, AuthorizationModule))
        }
    }
}