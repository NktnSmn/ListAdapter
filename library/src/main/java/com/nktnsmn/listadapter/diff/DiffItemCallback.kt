package com.nktnsmn.listadapter.diff

import androidx.recyclerview.widget.DiffUtil
import com.nktnsmn.listadapter.diff.item.*

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

inline fun <ITEM : Any, ID : Any> diffItemCallback(
    crossinline getItemId: (ITEM) -> ID,
    crossinline compareItems: (ITEM, ITEM) -> Boolean,
    crossinline getChangePayload: (ITEM, ITEM) -> Any?
): DiffItemCallback<ITEM, ID> =
    DiffItemCallback(
        itemIdProvider(getItemId),
        itemComparator(compareItems),
        itemChangePayloadProvider(getChangePayload)
    )

inline fun <ITEM : Any, ID : Any> diffItemCallback(
    crossinline getItemId: (ITEM) -> ID,
    crossinline compareItems: (ITEM, ITEM) -> Boolean
): DiffItemCallback<ITEM, ID> =
    DiffItemCallback(
        itemIdProvider(getItemId),
        itemComparator(compareItems)
    )

fun diffItemCallback(
    itemIdProvider: ItemIdProvider<IdentifiableByAnyItem, Any> = IdentifiableItemIdProvider(),
    itemComparator: ItemComparator<IdentifiableByAnyItem> = ByEqualsItemComparator(),
    changePayloadProvider: ItemChangePayloadProvider<IdentifiableByAnyItem>? = null
): DiffItemCallback<IdentifiableByAnyItem, Any> =
    DiffItemCallback(itemIdProvider, itemComparator, changePayloadProvider)