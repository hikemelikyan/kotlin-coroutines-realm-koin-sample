package com.armboldmind.kotlincoroutinesrealmkoin.shared.helpers

import android.content.Context

class BiometricManager private constructor(builder: Builder) {


    inner class Builder(context: Context) {
        private lateinit var title: String
        private lateinit var cancelationTitle: String

        fun setTitle(title: String) = apply { this.title = title }

        fun setCancelationTitle(cancelationTitle: String) = apply { this.cancelationTitle = cancelationTitle }

        fun build() = BiometricManager(this)
    }
}