package com.nktnsmn.listadapter.diff.item

import android.support.v7.util.DiffUtil

open class DiffItemCallback<ITEM : Any, ITEM_ID : Any>(
    private val itemIdProvider: ItemIdProvider<ITEM, ITEM_ID>,
    private val itemComparator: ItemComparator<ITEM> = ByEqualsItemComparator(),
    private val changePayloadProvider: ItemChangePayloadProvider<ITEM>? = null
) : DiffUtil.ItemCallback<ITEM>() {

    override fun areItemsTheSame(oldItem: ITEM, newItem: ITEM): Boolean =
        itemIdProvider.getItemId(oldItem) == itemIdProvider.getItemId(newItem)

    override fun areContentsTheSame(oldItem: ITEM, newItem: ITEM): Boolean =
        itemComparator.compare(oldItem, newItem)

    override fun getChangePayload(oldItem: ITEM, newItem: ITEM): Any? =
        changePayloadProvider?.getChangePayload(oldItem, newItem)
}