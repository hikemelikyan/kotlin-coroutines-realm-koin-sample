package com.armboldmind.kotlincoroutinesrealmkoin.shared.utils

import android.annotation.TargetApi
import android.content.Context
import android.hardware.fingerprint.FingerprintManager
import android.os.Build

object FingerprintUtil {

    fun isSdkSupported() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

    fun isFingerprintDialogAllowed() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P


    @TargetApi(Build.VERSION_CODES.M)
    fun isFingerprintSensorAvailable(context: Context): Boolean {
        val fManager = (context.getSystemService(Context.FINGERPRINT_SERVICE)) as FingerprintManager
        return fManager.isHardwareDetected
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun isThereRegisteredFingerprints(context: Context): Boolean {
        val fManager = (context.getSystemService(Context.FINGERPRINT_SERVICE)) as FingerprintManager
        return fManager.hasEnrolledFingerprints()
    }
}