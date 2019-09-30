package com.nktnsmn.listadapter.cellular.itemviewcell.impl

import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.util.SparseArray
import com.nktnsmn.listadapter.viewholder.BaseBindingViewHolder
import com.nktnsmn.listadapter.viewholder.BindableModelViewHolder

open class BindableModelViewCell(
    viewType: Int,
    itemClass: Class<*>,
    @LayoutRes layoutResId: Int,
    private val modelBindingVariableId: Int,
    globalBindingVariables: SparseArray<Any>? = null
) : BaseBindingItemViewCell<ViewDataBinding, BaseBindingViewHolder<*, ViewDataBinding>>(
    viewType,
    itemClass,
    layoutResId,
    globalBindingVariables
) {

    override fun createBindingViewHolder(
        viewDataBinding: ViewDataBinding
    ): BaseBindingViewHolder<*, ViewDataBinding> =
        BindableModelViewHolder(viewDataBinding, modelBindingVariableId)
}