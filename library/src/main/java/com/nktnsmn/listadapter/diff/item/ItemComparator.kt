package com.nktnsmn.listadapter.diff.item

interface ItemComparator<in ITEM : Any> {

    fun compare(firstItem: ITEM, secondItem: ITEM): Boolean
}

class ByEqualsItemComparator<in ITEM : Any> : ItemComparator<ITEM> {

    override fun compare(firstItem: ITEM, secondItem: ITEM): Boolean = firstItem == secondItem
}

inline fun <ITEM : Any> itemComparator(
    crossinline compareItems: (ITEM, ITEM) -> Boolean
): ItemComparator<ITEM> =
    object : ItemComparator<ITEM> {
        override fun compare(firstItem: ITEM, secondItem: ITEM): Boolean =
            compareItems(firstItem, secondItem)
    }