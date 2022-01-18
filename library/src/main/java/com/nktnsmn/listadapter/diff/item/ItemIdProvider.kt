package com.nktnsmn.listadapter.diff.item

interface ItemIdProvider<ITEM : Any> {

    fun getItemId(item: ITEM): Any
}

class ItemIdByModelProvider : ItemIdProvider<ItemIdModel> {

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun getItemId(itemIdModel: ItemIdModel): Any = itemIdModel.id
}

inline fun <ITEM : Any> itemIdProvider(
    crossinline getItemId: (ITEM) -> Any
): ItemIdProvider<ITEM> =
    object : ItemIdProvider<ITEM> {
        override fun getItemId(item: ITEM): Any = getItemId(item)
    }