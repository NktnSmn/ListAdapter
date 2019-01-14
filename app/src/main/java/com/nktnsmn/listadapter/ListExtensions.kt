package com.nktnsmn.listadapter

fun List<*>.checkIndexOfBounds(index: Int): Boolean = index in 0 until size