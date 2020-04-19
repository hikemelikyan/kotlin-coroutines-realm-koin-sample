package com.armboldmind.kotlincoroutinesrealmkoin.shared.di.scopes

import com.armboldmind.kotlincoroutinesrealmkoin.shared.di.scopes.root.KoinScope

@KoinScope("main_scope")
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class MainScope
