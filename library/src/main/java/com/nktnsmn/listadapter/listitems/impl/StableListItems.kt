package com.nktnsmn.listadapter.listitems.impl

import com.nktnsmn.listadapter.listitems.ListItems
import com.nktnsmn.listadapter.observer.ListItemsObserver

open class StableListItems<ITEM : Any>(
    private val items: List<ITEM> = emptyList()
) : ListItems<ITEM> {

    override var observer: ListItemsObserver? = null

    override fun getAll(): List<ITEM> = items
}