package com.nktnsmn.listadapter.cellular.itemviewcell

import android.view.ViewGroup
import com.nktnsmn.listadapter.viewholder.ViewHolder

interface ItemViewCell<VH : ViewHolder<*>> : SuitabilityQualifier {

    val viewType: Int

    fun createViewHolder(parent: ViewGroup): VH
}