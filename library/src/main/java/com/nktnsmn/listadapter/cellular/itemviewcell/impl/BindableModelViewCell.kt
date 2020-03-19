package com.nktnsmn.listadapter.cellular.itemviewcell.impl

import android.util.SparseArray
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
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