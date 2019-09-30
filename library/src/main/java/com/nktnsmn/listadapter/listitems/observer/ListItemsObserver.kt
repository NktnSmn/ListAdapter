package com.nktnsmn.listadapter.listitems.observer

import android.support.v7.util.ListUpdateCallback

interface ListItemsObserver: ListUpdateCallback {

    fun onChangedFully()
}