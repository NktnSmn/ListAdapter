package com.nktnsmn.listadapter.diff.item

interface ItemComparator<ITEM : Any> {

    fun compare(firstItem: ITEM, secondItem: ITEM): Boolean
}

class ByEqualsItemComparator<ITEM : Any> : ItemComparator<ITEM> {

    override fun compare(firstItem: ITEM, secondItem: ITEM): Boolean = firstItem == secondItem
}