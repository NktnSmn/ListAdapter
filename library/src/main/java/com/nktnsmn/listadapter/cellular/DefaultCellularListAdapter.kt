package com.nktnsmn.listadapter.cellular

import android.view.ViewGroup
import com.nktnsmn.listadapter.base.viewholder.BindableViewHolder
import com.nktnsmn.listadapter.cellular.itemviewcell.ItemViewCell
import com.nktnsmn.listadapter.cellular.itemviewcell.ItemViewCells
import com.nktnsmn.listadapter.cellular.itemviewcell.createBinding

class DefaultCellularListAdapter(
    itemViewCells: ItemViewCells
) : CellularListAdapter<BindableViewHolder>(itemViewCells) {

    constructor(vararg itemViewCells: ItemViewCell) : this(ItemViewCells(*itemViewCells))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder =
        itemViewCells.getForViewType(viewType).let { itemViewCell ->
            BindableViewHolder(
                itemViewCell.createBinding(parent),
                itemViewCell.cellBindingVariables
            )
        }
}