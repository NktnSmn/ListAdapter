package com.nktnsmn.listadapter.base.listitems

interface MutableListItems<T> : UpdatableListItems<T> {

    fun changeFully(newItems: List<T>): Boolean

    fun set(newItem: T, position: Int): Boolean

    fun add(newItem: T): Boolean = add(newItem, size())

    fun add(newItem: T, position: Int): Boolean

    fun add(newItems: List<T>): Boolean = add(newItems, size())

    fun add(newItems: List<T>, position: Int): Boolean

    fun remove(position: Int): Boolean

    fun remove(position: Int, count: Int): Boolean

    fun removeAll()
}