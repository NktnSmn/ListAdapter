package com.nktnsmn.listadapter.base.diff.item

import android.support.v7.util.DiffUtil
import com.nktnsmn.listadapter.base.item.ItemIdentifier
import com.nktnsmn.listadapter.base.item.comparator.ByEqualsItemComparator
import com.nktnsmn.listadapter.base.item.comparator.ItemComparator

class DiffItemCallback<T>(
    private val itemIdentifier: ItemIdentifier<T>,
    private val itemComparator: ItemComparator = ByEqualsItemComparator()
) : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        itemIdentifier.getItemId(oldItem) == itemIdentifier.getItemId(newItem)

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        itemComparator.compare(oldItem, newItem)
}