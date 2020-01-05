package com.armboldmind.kotlincoroutinesrealmkoin.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.armboldmind.kotlincoroutinesrealmkoin.R
import com.armboldmind.kotlincoroutinesrealmkoin.databinding.ActivityViewSavedDataActivityBinding
import com.armboldmind.kotlincoroutinesrealmkoin.model.ModelClass
import com.armboldmind.kotlincoroutinesrealmkoin.view.adapter.SavedItemsAdapter
import com.armboldmind.kotlincoroutinesrealmkoin.view.activity.base.BaseActivity
import io.realm.Realm

class ViewSavedDataActivity : BaseActivity() {
    private lateinit var mBinding: ActivityViewSavedDataActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_view_saved_data_activity)

        mBinding.recycler.layoutManager = LinearLayoutManager(this)

        val mRealm = Realm.getDefaultInstance()
        mRealm.beginTransaction()
        mRealm.commitTransaction()

        val savedItems = mRealm.where(ModelClass::class.java).findAll()

        showMessageToast("${savedItems.size}")

        mBinding.recycler.adapter = SavedItemsAdapter(savedItems)
        mBinding.recycler.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
    }
}
