package com.nktnsmn.listadapter.base.listitems

interface ConvertibleListItems<T> : UpdatableListItems<T> {

    fun convertTo(newItems: List<T>)
}