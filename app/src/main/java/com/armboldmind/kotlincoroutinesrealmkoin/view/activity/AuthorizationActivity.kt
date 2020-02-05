package com.armboldmind.kotlincoroutinesrealmkoin.view.activity

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.armboldmind.kotlincoroutinesrealmkoin.R
import com.armboldmind.kotlincoroutinesrealmkoin.databinding.ActivityAuthorizationBinding
import com.armboldmind.kotlincoroutinesrealmkoin.model.ModelClass
import com.armboldmind.kotlincoroutinesrealmkoin.view.activity.base.BaseActivity
import com.armboldmind.kotlincoroutinesrealmkoin.viewmodel.AuthorizationViewModel
import com.google.gson.Gson
import retrofit2.HttpException

class AuthorizationActivity : BaseActivity() {
    private lateinit var mBinding: ActivityAuthorizationBinding
    private lateinit var mViewModel: AuthorizationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_authorization)
        mViewModel = getViewModel(AuthorizationViewModel::class.java)
        observingToLiveData()
        mViewModel.testCall()
    }

    private fun observingToLiveData() {
        mViewModel.liveData.observe(this, Observer {
            if (it is ModelClass) {
                mBinding.progressBar.visibility = View.GONE
                mBinding.text.visibility = View.VISIBLE
                mBinding.text.text = Gson().toJson(it)
                showMessageToast("Ok")
                showMessageToast("Authorization Scope")
            } else if (it is HttpException) {
                mBinding.text.text = it.message()
                showMessageToast(it.message())
            }
        })
    }
}