package com.armboldmind.kotlincoroutinesrealmkoin.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.armboldmind.kotlincoroutinesrealmkoin.R
import com.armboldmind.kotlincoroutinesrealmkoin.databinding.ActivityMainBinding
import com.armboldmind.kotlincoroutinesrealmkoin.model.ModelClass
import com.armboldmind.kotlincoroutinesrealmkoin.view.activity.base.BaseActivity
import com.armboldmind.kotlincoroutinesrealmkoin.viewmodel.MainViewModel
import com.google.gson.Gson
import io.realm.Realm
import retrofit2.HttpException

class MainActivity : BaseActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mViewModel: MainViewModel
    private lateinit var realmInstance: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mViewModel = getViewModel(MainViewModel::class.java)
        init()
        observingToLiveData()
        mViewModel.testCall()
    }

    private fun init() {
        realmInstance = Realm.getDefaultInstance()

        mBinding.getSavedItems.setOnClickListener {
            startActivity(Intent(this, ViewSavedDataActivity::class.java))
        }

        mBinding.authorization.setOnClickListener {
            startActivity(Intent(this, AuthorizationActivity::class.java))
        }

        mBinding.callAgain.setOnClickListener {
            mBinding.progressBar.visibility = View.VISIBLE
            mBinding.text.visibility = View.INVISIBLE
            mViewModel.testCall()
        }

        mBinding.clearDb.setOnClickListener {
            realmInstance.beginTransaction()
            realmInstance.deleteAll()
            realmInstance.commitTransaction()
            realmInstance.addChangeListener {
                showMessageToast("Deleted!")
            }
        }
    }

    private fun observingToLiveData() {
        mViewModel.liveData.observe(this, Observer {
            if (it is ModelClass) {
                mBinding.progressBar.visibility = View.GONE
                mBinding.text.visibility = View.VISIBLE
                realmInstance.beginTransaction()
                realmInstance.copyToRealm(it)
                realmInstance.commitTransaction()
                mBinding.text.text = Gson().toJson(it)
                showMessageToast("Ok")
                showMessageToast("Main scope")
            } else if (it is HttpException) {
                mBinding.text.text = it.message()
                showMessageToast(it.message())
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        realmInstance.close()
    }
}
