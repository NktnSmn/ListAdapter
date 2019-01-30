package com.nktnsmn.listadapter.base.listitems.impl

import com.nktnsmn.listadapter.base.listitems.ListItems

open class StableListItems<T>(private val items: List<T> = emptyList()) : ListItems<T> {

    override fun getAll(): List<T> = items
}