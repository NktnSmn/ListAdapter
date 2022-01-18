package com.nktnsmn.listadapter.viewholder

import androidx.databinding.ViewDataBinding

abstract class BindingViewHolder<DATA : Any, VDB : ViewDataBinding>(
    protected val viewDataBinding: VDB
) : ViewHolder<DATA>(viewDataBinding.root) {

    companion object {
        fun <MODEL : Any> forModel(viewDataBinding: ViewDataBinding, modelBindingVariableId: Int) =
            object : BindingViewHolder<MODEL, ViewDataBinding>(viewDataBinding) {
                override fun bindData(data: MODEL) {
                    setBindingVariable(modelBindingVariableId, data)
                }
            }
    }

    fun setBindingVariable(variableId: Int, variable: Any) {
        viewDataBinding.setVariable(variableId, variable)
        viewDataBinding.executePendingBindings()
    }

    fun setBindingVariables(bindingVariables: Map<Int, Any>) {
        bindingVariables.forEach { viewDataBinding.setVariable(it.key, it.value) }
        viewDataBinding.executePendingBindings()
    }
}