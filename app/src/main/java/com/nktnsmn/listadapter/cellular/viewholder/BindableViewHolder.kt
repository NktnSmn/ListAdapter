package com.nktnsmn.listadapter.cellular.viewholder

import android.databinding.ViewDataBinding
import android.util.SparseArray
import com.nktnsmn.listadapter.base.viewholder.ViewHolder

open class BindableViewHolder @JvmOverloads constructor(
    private val viewDataBinding: ViewDataBinding,
    globalBindingVariables: SparseArray<Any>? = null
) : ViewHolder(viewDataBinding.root) {

    init {
        if (globalBindingVariables != null) {
            for (i in 0 until globalBindingVariables.size()) {
                val variableId = globalBindingVariables.keyAt(i)
                val variable = globalBindingVariables.get(variableId)
                viewDataBinding.setVariable(variableId, variable)
            }
        }
    }

    open fun bind(bindingVariableId: Int, bindingVariable: Any) {
        viewDataBinding.setVariable(bindingVariableId, bindingVariable)
        viewDataBinding.executePendingBindings()
    }
}