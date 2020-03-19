package com.nktnsmn.listadapter.listitems

import androidx.annotation.AnyThread
import com.nktnsmn.listadapter.observer.ListItemsObserver

interface ListItems<ITEM : Any> {

    var observer: ListItemsObserver?

    @AnyThread
    fun getAll(): List<ITEM>

    @AnyThread
    fun size(): Int = getAll().size

    @AnyThread
    fun isEmpty(): Boolean = size() == 0

    @AnyThread
    @Throws(IndexOutOfBoundsException::class)
    operator fun get(position: Int): ITEM = getAll()[position]

    @AnyThread
    fun getOrNull(position: Int): ITEM? = getAll().getOrNull(position)
}