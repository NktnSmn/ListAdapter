package com.nktnsmn.listadapter.base.item

interface ItemIdentifier<T> {

    fun getItemId(item: T): String
}