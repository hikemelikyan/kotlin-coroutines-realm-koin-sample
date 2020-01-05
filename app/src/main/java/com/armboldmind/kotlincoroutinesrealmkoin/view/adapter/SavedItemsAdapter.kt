package com.armboldmind.kotlincoroutinesrealmkoin.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.armboldmind.kotlincoroutinesrealmkoin.R
import com.armboldmind.kotlincoroutinesrealmkoin.databinding.AdapterSavedItemBinding
import com.armboldmind.kotlincoroutinesrealmkoin.model.ModelClass

class SavedItemsAdapter(private val mList: List<ModelClass>) :
    RecyclerView.Adapter<SavedItemsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.adapter_saved_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mBinding.model = mList[position]
    }

    class ViewHolder(var mBinding: AdapterSavedItemBinding) : RecyclerView.ViewHolder(mBinding.root)
}