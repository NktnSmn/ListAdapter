package com.nktnsmn.listadapter.base.item.comparator

class ByEqualsItemComparator : ItemComparator {

    override fun compare(firstItem: Any?, secondItem: Any?): Boolean = firstItem == secondItem
}