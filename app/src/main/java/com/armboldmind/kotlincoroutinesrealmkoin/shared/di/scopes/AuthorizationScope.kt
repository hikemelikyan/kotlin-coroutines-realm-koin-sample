package com.armboldmind.kotlincoroutinesrealmkoin.shared.di.scopes

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class AuthorizationScope {

    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.FIELD)
    annotation class Field

}
