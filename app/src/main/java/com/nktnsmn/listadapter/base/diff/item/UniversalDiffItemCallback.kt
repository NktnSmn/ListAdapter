package com.nktnsmn.listadapter.base.diff.item

import android.support.v7.util.DiffUtil
import com.nktnsmn.listadapter.base.item.IdentifiableItem
import com.nktnsmn.listadapter.base.item.comparator.ByEqualsItemComparator
import com.nktnsmn.listadapter.base.item.comparator.ItemComparator

class UniversalDiffItemCallback(
    private val itemsComparator: ItemComparator = ByEqualsItemComparator()
) : DiffUtil.ItemCallback<IdentifiableItem>() {

    override fun areItemsTheSame(oldItem: IdentifiableItem?, newItem: IdentifiableItem?): Boolean =
        oldItem?.id == newItem?.id

    override fun areContentsTheSame(oldItem: IdentifiableItem?, newItem: IdentifiableItem?): Boolean =
        itemsComparator.compare(oldItem, newItem)
}