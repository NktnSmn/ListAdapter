package com.nktnsmn.listadapter.base.updatecallback

import android.support.v7.util.ListUpdateCallback

interface ListItemsUpdateCallback: ListUpdateCallback {

    fun onChangedFully()
}