package com.nktnsmn.listadapter.cellular.itemviewcell

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.nktnsmn.listadapter.viewholder.BindingViewHolder

abstract class BindingItemViewCell<ITEM : Any, VDB : ViewDataBinding, VH : BindingViewHolder<ITEM, VDB>>(
    @LayoutRes protected val layoutResId: Int,
    override val viewType: Int = layoutResId,
    protected val globalBindingVariables: Map<Int, Any>? = null
) : ItemViewCell<ITEM, VH> {

    companion object {
        inline fun <reified MODEL : Any> forModel(
            @LayoutRes layoutResId: Int,
            modelBindingVariableId: Int,
            globalBindingVariables: Map<Int, Any>? = null
        ) =
            object : BindingItemViewCell<MODEL, ViewDataBinding, BindingViewHolder<MODEL, ViewDataBinding>>(
                layoutResId = layoutResId,
                globalBindingVariables = globalBindingVariables
            ) {
                override fun isSuitableForItem(item: Any): Boolean = item is MODEL

                override fun createBindingViewHolder(viewDataBinding: ViewDataBinding): BindingViewHolder<MODEL, ViewDataBinding> =
                    BindingViewHolder.forModel(viewDataBinding, modelBindingVariableId)
            }
    }

    override fun createViewHolder(parent: ViewGroup): VH {
        val viewDataBinding: VDB = inflateViewDataBinding(LayoutInflater.from(parent.context), parent)
        val viewHolder = createBindingViewHolder(viewDataBinding)
        if (globalBindingVariables != null) {
            viewHolder.setBindingVariables(globalBindingVariables)
        }
        return viewHolder
    }

    protected open fun inflateViewDataBinding(inflater: LayoutInflater, parent: ViewGroup): VDB =
        DataBindingUtil.inflate(inflater, layoutResId, parent, false)

    protected abstract fun createBindingViewHolder(viewDataBinding: VDB): VH
}