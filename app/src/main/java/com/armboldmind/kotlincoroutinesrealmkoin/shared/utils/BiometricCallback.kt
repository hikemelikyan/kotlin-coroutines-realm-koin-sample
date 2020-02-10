package com.armboldmind.kotlincoroutinesrealmkoin.shared.utils

import android.annotation.TargetApi
import android.hardware.biometrics.BiometricPrompt
import android.os.Build

@TargetApi(Build.VERSION_CODES.P)
class BiometricCallback: BiometricPrompt.AuthenticationCallback() {
    override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
        super.onAuthenticationError(errorCode, errString)
    }

    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
        super.onAuthenticationSucceeded(result)
    }

    override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?) {
        super.onAuthenticationHelp(helpCode, helpString)
    }

    override fun onAuthenticationFailed() {
        super.onAuthenticationFailed()
    }
}
