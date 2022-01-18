package com.nktnsmn.sample.items.presentation.item

import com.nktnsmn.listadapter.diff.item.ItemIdModel

class ColorItemVM(
    override val id: String,
    val color: Int
) : ItemIdModel {

    //region equals&hashCode
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as ColorItemVM
        if (color != other.color) return false
        return true
    }

    override fun hashCode(): Int {
        return color
    }
    //endregion
}