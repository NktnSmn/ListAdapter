package com.nktnsmn.listadapter.cellular

import android.view.ViewGroup
import com.nktnsmn.listadapter.base.diff.item.UniversalDiffItemCallback
import com.nktnsmn.listadapter.base.item.IdentifiableItem
import com.nktnsmn.listadapter.base.adapteritems.AdapterItemsWithAsyncDiffer
import com.nktnsmn.listadapter.cellular.itemviewcell.ItemViewCells
import com.nktnsmn.listadapter.cellular.itemviewcell.createBinding
import com.nktnsmn.listadapter.base.viewholder.BindableViewHolder

class DefaultCellularListAdapter(
    itemViewCells: ItemViewCells
) : CellularListAdapter<AdapterItemsWithAsyncDiffer<IdentifiableItem>, BindableViewHolder>(
    AdapterItemsWithAsyncDiffer(UniversalDiffItemCallback()), itemViewCells
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder =
        itemViewCells.getForViewType(viewType).let { itemViewCell ->
            BindableViewHolder(
                itemViewCell.createBinding(parent),
                itemViewCell.cellBindingVariables
            )
        }
}