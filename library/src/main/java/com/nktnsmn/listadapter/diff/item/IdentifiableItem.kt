package com.nktnsmn.listadapter.diff.item

interface IdentifiableItem<ID> {
    val id: ID
}

interface IdentifiableByAnyItem : IdentifiableItem<Any>