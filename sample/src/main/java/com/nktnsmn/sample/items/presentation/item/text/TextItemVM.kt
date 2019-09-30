package com.nktnsmn.sample.items.presentation.item.text

import com.nktnsmn.listadapter.diff.item.IdentifiableByAnyItem

class TextItemVM(
    override val id: String,
    val content: Content
) : IdentifiableByAnyItem {

    data class Content(val content: String)

    //region equals&hashCode
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as TextItemVM
        if (content != other.content) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
    //endregion
}