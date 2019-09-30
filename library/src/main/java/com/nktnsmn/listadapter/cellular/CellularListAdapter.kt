package com.nktnsmn.listadapter.cellular

import android.view.ViewGroup
import com.nktnsmn.listadapter.ListAdapter
import com.nktnsmn.listadapter.cellular.itemviewcell.ItemViewCell
import com.nktnsmn.listadapter.cellular.itemviewcell.ItemViewCells
import com.nktnsmn.listadapter.viewholder.ViewHolder

open class CellularListAdapter<VH : ViewHolder<*>>(
    protected val itemViewCells: ItemViewCells<VH>
) : ListAdapter<Any, VH>() {

    constructor(vararg itemViewCells: ItemViewCell<out VH>) : this(ItemViewCells(*itemViewCells))

    override fun getItemViewType(position: Int): Int = itemViewCells.getForItem(items[position]).viewType

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        itemViewCells.getForViewType(viewType).createViewHolder(parent)
}