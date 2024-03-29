package com.nktnsmn.sample.items.presentation.item.attachment

import androidx.annotation.DrawableRes
import com.nktnsmn.listadapter.diff.item.ItemIdModel

class AttachmentItemVM(
    override val id: String,
    @DrawableRes val iconResId: Int,
    val iconColor: Int,
    val name: String
) : ItemIdModel {

    //region equals&hashCode
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as AttachmentItemVM
        if (iconResId != other.iconResId) return false
        if (name != other.name) return false
        return true
    }

    override fun hashCode(): Int {
        var result = iconResId
        result = 31 * result + name.hashCode()
        return result
    }
    //endregion
}