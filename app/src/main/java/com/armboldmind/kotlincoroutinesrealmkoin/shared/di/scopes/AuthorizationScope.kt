package com.armboldmind.kotlincoroutinesrealmkoin.shared.di.scopes

import com.armboldmind.kotlincoroutinesrealmkoin.shared.di.scopes.root.KoinScope

@KoinScope("authorization_scope")
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class AuthorizationScope