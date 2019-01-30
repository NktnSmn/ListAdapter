package com.nktnsmn.listadapter.base.listitems.impl.base

import com.nktnsmn.listadapter.base.listitems.UpdatableListItems
import com.nktnsmn.listadapter.base.updatecallback.ListItemsUpdateCallback

abstract class BaseUpdatableListItems<T> : UpdatableListItems<T> {

    protected var updateCallback: ListItemsUpdateCallback? = null

    override fun bindUpdateCallback(updateCallback: ListItemsUpdateCallback) {
        this.updateCallback = updateCallback
    }

    override fun unbindUpdateCallback() {
        this.updateCallback = null
    }
}