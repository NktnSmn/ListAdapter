package com.nktnsmn.listadapter.base.listitems

import com.nktnsmn.listadapter.base.updatecallback.ListItemsUpdateCallback

interface UpdatableListItems<T>: ListItems<T> {

    fun bindUpdateCallback(updateCallback: ListItemsUpdateCallback)

    fun unbindUpdateCallback()
}