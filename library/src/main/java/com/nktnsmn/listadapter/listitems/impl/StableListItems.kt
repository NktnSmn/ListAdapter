package com.nktnsmn.listadapter.listitems.impl

import com.nktnsmn.listadapter.listitems.ListItems

open class StableListItems<ITEM : Any>(private val items: List<ITEM> = emptyList()) : ListItems<ITEM> {

    override fun getAll(): List<ITEM> = items

    object Empty : StableListItems<Nothing>()
}