package com.nktnsmn.listadapter.listitems

import android.support.annotation.UiThread

interface MutableListItems<ITEM : Any> : ObservableListItems<ITEM> {

    @UiThread
    fun changeFully(newItems: List<ITEM>): Boolean

    @UiThread
    fun set(newItem: ITEM, position: Int): Boolean

    @UiThread
    fun add(newItem: ITEM, position: Int): Boolean

    @UiThread
    fun add(newItem: ITEM): Boolean = add(newItem, size())

    @UiThread
    fun add(newItems: List<ITEM>, position: Int): Boolean

    @UiThread
    fun add(newItems: List<ITEM>): Boolean = add(newItems, size())

    @UiThread
    fun remove(position: Int, count: Int): Boolean

    @UiThread
    fun remove(position: Int): Boolean = remove(position, 1)

    @UiThread
    fun removeAll()
}