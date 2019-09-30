package com.nktnsmn.listadapter.diff.item

interface ItemChangePayloadProvider<ITEM : Any> {

    fun getChangePayload(firstItem: ITEM, secondItem: ITEM): Any?
}

class UnitChangePayloadProvider : ItemChangePayloadProvider<Any> {

    override fun getChangePayload(firstItem: Any, secondItem: Any): Any? = Unit
}
