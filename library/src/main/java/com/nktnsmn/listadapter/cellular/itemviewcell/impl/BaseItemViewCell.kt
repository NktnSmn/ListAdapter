package com.nktnsmn.listadapter.cellular.itemviewcell.impl

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nktnsmn.listadapter.cellular.itemviewcell.ItemViewCell
import com.nktnsmn.listadapter.viewholder.ViewHolder

abstract class BaseItemViewCell<VH : ViewHolder<*>>(
    override val viewType: Int,
    private val itemClass: Class<*>
) : ItemViewCell<VH> {

    override fun isSuitableForItem(item: Any): Boolean = item.javaClass === itemClass

    override fun createViewHolder(parent: ViewGroup): VH = createViewHolder(LayoutInflater.from(parent.context), parent)

    abstract fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup): VH
}

