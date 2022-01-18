package com.nktnsmn.listadapter.cellular.itemviewcell

import android.view.ViewGroup
import com.nktnsmn.listadapter.viewholder.ViewHolder

interface ItemViewCell<out ITEM : Any, out VH : ViewHolder<ITEM>> {

    val viewType: Int

    fun isSuitableForItem(item: Any): Boolean

    fun createViewHolder(parent: ViewGroup): VH
}