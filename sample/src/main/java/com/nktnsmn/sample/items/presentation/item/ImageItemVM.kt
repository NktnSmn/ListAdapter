package com.nktnsmn.sample.items.presentation.item

import com.nktnsmn.listadapter.diff.item.IdentifiableByAnyItem

data class ImageItemVM(
    override val id: String,
    val uri: String,
    val title: String,
    val description: String? = null
) : IdentifiableByAnyItem {

    //region equals&hashCode
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as ImageItemVM
        if (uri != other.uri) return false
        if (title != other.title) return false
        if (description != other.description) return false
        return true
    }

    override fun hashCode(): Int {
        var result = uri.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        return result
    }
    //endregion
}