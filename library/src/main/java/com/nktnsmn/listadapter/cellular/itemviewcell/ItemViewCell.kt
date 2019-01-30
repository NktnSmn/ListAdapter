package com.nktnsmn.listadapter.cellular.itemviewcell

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.ViewGroup

class ItemViewCell(
    val suitabilityQualifier: SuitabilityQualifier,
    val viewType: Int,
    @LayoutRes val layoutResId: Int,
    val itemBindingVariableId: Int,
    val cellBindingVariables: SparseArray<Any>? = null
) {

    constructor(
        itemClass: Class<*>,
        @LayoutRes layoutResId: Int,
        itemBindingVariableId: Int = 0,
        viewType: Int = layoutResId,
        vararg varIdToVarPairs: Pair<Int, Any>
    ) : this(
        ClassSuitabilityQualifier(itemClass),
        viewType,
        layoutResId,
        itemBindingVariableId,
        SparseArray<Any>(varIdToVarPairs.size).apply { varIdToVarPairs.forEach { put(it.first, it.second) } }
    )

    interface SuitabilityQualifier {

        fun isSuitableForItem(item: Any): Boolean
    }
}

fun ItemViewCell.isSuitableForItem(item: Any): Boolean = suitabilityQualifier.isSuitableForItem(item)

fun ItemViewCell.isSameViewType(viewType: Int): Boolean = this.viewType == viewType

fun ItemViewCell.createBinding(parent: ViewGroup): ViewDataBinding =
    DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutResId, parent, false)