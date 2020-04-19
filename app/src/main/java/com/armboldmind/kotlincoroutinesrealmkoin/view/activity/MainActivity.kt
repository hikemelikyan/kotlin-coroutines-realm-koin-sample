package com.armboldmind.kotlincoroutinesrealmkoin.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.armboldmind.kotlincoroutinesrealmkoin.R
import com.armboldmind.kotlincoroutinesrealmkoin.databinding.ActivityMainBinding
import com.armboldmind.kotlincoroutinesrealmkoin.model.TestModel
import com.armboldmind.kotlincoroutinesrealmkoin.shared.helpers.scopeHelper.getScope
import com.armboldmind.kotlincoroutinesrealmkoin.view.activity.base.BaseActivity
import com.armboldmind.kotlincoroutinesrealmkoin.viewmodel.MainViewModel
import com.google.gson.Gson
import io.realm.Realm

class MainActivity : BaseActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mViewModel: MainViewModel
    private lateinit var realmInstance: Realm
    private lateinit var testModel: TestModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mViewModel = getViewModel()
        initView()
        observingToLiveData()
        mViewModel.testCall("lover")
    }

    private fun initView() {
        realmInstance = Realm.getDefaultInstance()

        /*mBinding.btnOk.setOnClickListener {
            if (mBinding.progressBar.visibility == View.GONE)
                if (testModel.getBannerContent) {
                    val intent = Intent(this, WebViewActivity::class.java)
                    intent.putExtra("webUrl", testModel.urlToDisplay)
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                } else {
                    showMessageToast("Sorry we need to stay safe.")
                }
        }*/

        mBinding.getSavedItems.setOnClickListener {
            startActivity(Intent(this, ViewSavedDataActivity::class.java))
        }

        mBinding.authorization.setOnClickListener {
            startActivity(Intent(this, AuthorizationActivity::class.java))
        }

        mBinding.callAgain.setOnClickListener {
            mBinding.progressBar.visibility = View.VISIBLE
            mBinding.text.visibility = View.INVISIBLE
            mViewModel.testCall("lover")
        }

        mBinding.clearDb.setOnClickListener { clearDb() }
    }

    private fun clearDb() {
        realmInstance.beginTransaction()
        realmInstance.deleteAll()
        realmInstance.commitTransaction()
        realmInstance.addChangeListener {
            showMessageToast("Deleted!")
        }
    }

    private fun observingToLiveData() {
        mViewModel.liveData.observe(this, Observer {
            mBinding.progressBar.visibility = View.GONE
            mBinding.text.visibility = View.VISIBLE
//            realmInstance.beginTransaction()
//            realmInstance.copyToRealm(it)
//            realmInstance.commitTransaction()
            mBinding.text.text = Gson().toJson(it)
            testModel = it
            showMessageToast(mViewModel.getScope()!!.scopeDefinition?.qualifier.toString())
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        realmInstance.close()
    }
}
