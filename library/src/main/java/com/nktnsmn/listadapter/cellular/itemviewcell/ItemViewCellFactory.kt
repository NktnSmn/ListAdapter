package com.nktnsmn.listadapter.cellular.itemviewcell

import android.util.SparseArray
import androidx.annotation.LayoutRes
import com.nktnsmn.listadapter.cellular.itemviewcell.impl.BindableModelViewCell

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