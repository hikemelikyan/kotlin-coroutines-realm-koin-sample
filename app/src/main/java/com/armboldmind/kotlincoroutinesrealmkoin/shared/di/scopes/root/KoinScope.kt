package com.armboldmind.kotlincoroutinesrealmkoin.shared.di.scopes.root

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.ANNOTATION_CLASS)
annotation class KoinScope(val scopeName: String)