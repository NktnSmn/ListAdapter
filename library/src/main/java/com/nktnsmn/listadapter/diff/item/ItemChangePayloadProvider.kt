package com.nktnsmn.listadapter.diff.item

interface ItemChangePayloadProvider<in ITEM : Any> {

    fun getChangePayload(firstItem: ITEM, secondItem: ITEM): Any?
}

class UnitChangePayloadProvider : ItemChangePayloadProvider<Any> {

    override fun getChangePayload(firstItem: Any, secondItem: Any): Any? = Unit
}

inline fun <ITEM : Any> itemChangePayloadProvider(
    crossinline getChangePayload: (ITEM, ITEM) -> Any?
): ItemChangePayloadProvider<ITEM> =
    object : ItemChangePayloadProvider<ITEM> {
        override fun getChangePayload(firstItem: ITEM, secondItem: ITEM): Any? =
            getChangePayload(firstItem, secondItem)
    }
