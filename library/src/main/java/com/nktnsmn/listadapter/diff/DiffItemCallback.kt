package com.nktnsmn.listadapter.diff

import androidx.recyclerview.widget.DiffUtil
import com.nktnsmn.listadapter.diff.item.*

open class DiffItemCallback<ITEM : Any>(
    private val itemIdProvider: ItemIdProvider<ITEM>,
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

fun diffItemCallback(
    itemIdProvider: ItemIdProvider<ItemIdModel> = ItemIdByModelProvider(),
    itemComparator: ItemComparator<ItemIdModel> = ByEqualsItemComparator(),
    changePayloadProvider: ItemChangePayloadProvider<ItemIdModel>? = null
): DiffItemCallback<ItemIdModel> =
    DiffItemCallback(itemIdProvider, itemComparator, changePayloadProvider)

inline fun <ITEM : Any, ID : Any> diffItemCallback(
    crossinline getItemId: (ITEM) -> ID,
    crossinline compareItems: (ITEM, ITEM) -> Boolean,
    crossinline getChangePayload: (ITEM, ITEM) -> Any?
): DiffItemCallback<ITEM> =
    DiffItemCallback(
        itemIdProvider(getItemId),
        itemComparator(compareItems),
        itemChangePayloadProvider(getChangePayload)
    )

inline fun <ITEM : Any, ID : Any> diffItemCallback(
    crossinline getItemId: (ITEM) -> ID,
    crossinline compareItems: (ITEM, ITEM) -> Boolean
): DiffItemCallback<ITEM> =
    DiffItemCallback(
        itemIdProvider(getItemId),
        itemComparator(compareItems)
    )

