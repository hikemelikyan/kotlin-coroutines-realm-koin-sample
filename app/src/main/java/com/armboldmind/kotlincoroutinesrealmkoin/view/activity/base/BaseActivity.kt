package com.armboldmind.kotlincoroutinesrealmkoin.view.activity.base

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.armboldmind.kotlincoroutinesrealmkoin.viewmodel.base.BaseViewModel
import org.koin.android.viewmodel.ext.android.viewModel

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    inline fun <reified T : BaseViewModel> getViewModel(): T {
        val viewModel: T by viewModels()
        return viewModel
    }

    fun showMessageToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun hasPermission(permission: String): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }
}