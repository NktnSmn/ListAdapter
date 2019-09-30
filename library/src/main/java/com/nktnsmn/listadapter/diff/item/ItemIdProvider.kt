package com.nktnsmn.listadapter.diff.item

interface ItemIdProvider<in ITEM : Any, out ID : Any> {

    fun getItemId(item: ITEM): ID
}

class IdentifiableItemIdProvider<ID : Any> : ItemIdProvider<IdentifiableItem<ID>, ID> {

    override fun getItemId(item: IdentifiableItem<ID>): ID = item.id
}