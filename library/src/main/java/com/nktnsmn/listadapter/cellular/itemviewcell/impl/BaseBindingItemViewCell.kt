package com.nktnsmn.listadapter.cellular.itemviewcell.impl

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.nktnsmn.listadapter.viewholder.BaseBindingViewHolder

abstract class BaseBindingItemViewCell<VDB : ViewDataBinding, VH : BaseBindingViewHolder<*, VDB>>(
    viewType: Int,
    itemClass: Class<*>,
    @LayoutRes private val layoutResId: Int,
    private val globalBindingVariables: SparseArray<Any>? = null
) : BaseItemViewCell<VH>(viewType, itemClass) {

    override fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup): VH {
        val viewDataBinding: VDB = inflateViewDataBinding(inflater, parent)
        val viewHolder = createBindingViewHolder(viewDataBinding)
        if (globalBindingVariables != null) {
            viewHolder.setBindingVariables(globalBindingVariables)
        }
        return viewHolder
    }

    protected abstract fun createBindingViewHolder(viewDataBinding: VDB): VH

    protected open fun inflateViewDataBinding(inflater: LayoutInflater, parent: ViewGroup): VDB =
        DataBindingUtil.inflate(inflater, layoutResId, parent, false)
}