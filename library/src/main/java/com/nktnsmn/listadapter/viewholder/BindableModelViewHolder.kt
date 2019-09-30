package com.nktnsmn.listadapter.viewholder

import android.databinding.ViewDataBinding

internal class BindableModelViewHolder(
    viewDataBinding: ViewDataBinding,
    private val modelBindingVariableId: Int
) : BaseBindingViewHolder<Any, ViewDataBinding>(viewDataBinding) {

    override fun bindDataInternal(data: Any) {
        setBindingVariable(modelBindingVariableId, data)
    }
}