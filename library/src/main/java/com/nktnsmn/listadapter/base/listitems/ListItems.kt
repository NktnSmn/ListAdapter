package com.nktnsmn.listadapter.base.listitems

interface ListItems<T> {

    fun getAll(): List<T>

    fun size(): Int = getAll().size

    fun isEmpty(): Boolean = size() == 0

    @Throws(IndexOutOfBoundsException::class)
    operator fun get(position: Int): T = getAll()[position]

    fun getOrNull(position: Int): T? = getAll().getOrNull(position)
}