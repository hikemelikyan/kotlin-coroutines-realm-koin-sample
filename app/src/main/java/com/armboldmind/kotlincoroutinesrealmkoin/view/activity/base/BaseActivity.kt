package com.armboldmind.kotlincoroutinesrealmkoin.view.activity.base

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.armboldmind.kotlincoroutinesrealmkoin.viewmodel.base.BaseViewModel

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    fun <T : BaseViewModel> getViewModel(viewModelClass: Class<T>): T {
        return ViewModelProviders.of(this).get(viewModelClass)
    }

    fun showMessageToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}