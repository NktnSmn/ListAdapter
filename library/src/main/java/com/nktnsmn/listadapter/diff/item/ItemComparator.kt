package com.nktnsmn.listadapter.diff.item

interface ItemComparator<in ITEM> {

    fun compare(first: ITEM, second: ITEM): Boolean
}

class ByEqualsItemComparator<in ITEM> : ItemComparator<ITEM> {

    override fun compare(first: ITEM, second: ITEM): Boolean = first == second
}

inline fun <ITEM : Any> itemComparator(
    crossinline compareItems: (first: ITEM, second: ITEM) -> Boolean
): ItemComparator<ITEM> =
    object : ItemComparator<ITEM> {
        override fun compare(first: ITEM, second: ITEM): Boolean = compareItems(first, second)
    }