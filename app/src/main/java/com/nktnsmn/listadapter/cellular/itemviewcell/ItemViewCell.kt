package com.nktnsmn.listadapter.cellular.itemviewcell

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.ViewGroup

class ItemViewCell(
    val suitabilityQualifier: SuitabilityQualifier,
    val viewType: Int,
    val layoutResId: Int,
    val itemBindingVariableId: Int,
    val cellBindingVariables: SparseArray<Any>? = null
) {

    interface SuitabilityQualifier {

        fun isSuitableForItem(item: Any): Boolean
    }
}

fun ItemViewCell.isSuitableForItem(item: Any): Boolean = suitabilityQualifier.isSuitableForItem(item)

fun ItemViewCell.isSameViewType(viewType: Int): Boolean = this.viewType == viewType

fun ItemViewCell.createBinding(parent: ViewGroup): ViewDataBinding =
    DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutResId, parent, false)