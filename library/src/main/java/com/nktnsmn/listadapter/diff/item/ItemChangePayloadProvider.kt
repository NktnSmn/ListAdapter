package com.nktnsmn.listadapter.diff.item

interface ItemChangePayloadProvider<in ITEM> {

    fun getChangePayload(first: ITEM, second: ITEM): Any?
}

class UnitChangePayloadProvider : ItemChangePayloadProvider<Any> {

    override fun getChangePayload(first: Any, second: Any): Any? = Unit
}

inline fun <ITEM : Any> itemChangePayloadProvider(
    crossinline getChangePayload: (first: ITEM, second: ITEM) -> Any?
): ItemChangePayloadProvider<ITEM> =
    object : ItemChangePayloadProvider<ITEM> {
        override fun getChangePayload(first: ITEM, second: ITEM): Any? = getChangePayload(first, second)
    }
