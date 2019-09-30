package com.nktnsmn.listadapter.viewholder

import android.databinding.ViewDataBinding
import android.util.SparseArray

abstract class BaseBindingViewHolder<DATA : Any, VDB : ViewDataBinding>(
    protected val viewDataBinding: VDB
) : ViewHolder<DATA>(viewDataBinding.root) {

    fun setBindingVariable(variableId: Int, variable: Any) {
        viewDataBinding.setVariable(variableId, variable)
        viewDataBinding.executePendingBindings()
    }

    fun setBindingVariables(bindingVariables: SparseArray<Any>) {
        for (i in 0 until bindingVariables.size()) {
            val variableId = bindingVariables.keyAt(i)
            val variable = bindingVariables.get(variableId)
            viewDataBinding.setVariable(variableId, variable)
        }
        viewDataBinding.executePendingBindings()
    }
}