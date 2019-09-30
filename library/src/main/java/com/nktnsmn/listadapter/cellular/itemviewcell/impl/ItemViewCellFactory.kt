package com.nktnsmn.listadapter.cellular.itemviewcell.impl

import android.support.annotation.LayoutRes
import android.util.SparseArray

object ItemViewCellFactory {

    inline fun <reified MODEL : Any> forModel(
        @LayoutRes layoutResId: Int,
        modelBindingVariableId: Int
    ): BindableModelViewCell =
        BindableModelViewCell(
            layoutResId,
            MODEL::class.java,
            layoutResId,
            modelBindingVariableId
        )

    inline fun <reified MODEL : Any> forModel(
        @LayoutRes layoutResId: Int,
        modelBindingVariableId: Int,
        buildGlobalBindingVariables: ((SparseArray<Any>) -> Unit)
    ): BindableModelViewCell =
        BindableModelViewCell(
            layoutResId,
            MODEL::class.java,
            layoutResId,
            modelBindingVariableId,
            SparseArray<Any>().apply { buildGlobalBindingVariables(this) }
        )
}