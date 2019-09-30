package com.nktnsmn.listadapter.listitems

import android.support.annotation.AnyThread

interface ListItems<ITEM : Any> {

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