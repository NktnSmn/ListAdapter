package com.nktnsmn.listadapter.diff.item

interface SameItemConversionHandler<ITEM : Any, NEW_ITEMS : List<ITEM>> {

    fun handleItemConversion(
        oldList: List<ITEM>,
        oldPosition: Int,
        oldItem: ITEM,
        newList: NEW_ITEMS,
        newPosition: Int,
        newItem: ITEM
    )

    abstract class Simple<ITEM : Any> : SameItemConversionHandler<ITEM, List<ITEM>> {

        override fun handleItemConversion(
            oldList: List<ITEM>,
            oldPosition: Int,
            oldItem: ITEM,
            newList: List<ITEM>,
            newPosition: Int,
            newItem: ITEM
        ) {
            handleItemConversion(oldItem, newItem)
        }

        abstract fun handleItemConversion(oldItem: ITEM, newItem: ITEM)
    }

    companion object {

        fun <ITEM : Any> insertionIntoNewList(): SameItemConversionHandler<ITEM, MutableList<ITEM>> =
            object : SameItemConversionHandler<ITEM, MutableList<ITEM>> {
                override fun handleItemConversion(
                    oldList: List<ITEM>,
                    oldPosition: Int,
                    oldItem: ITEM,
                    newList: MutableList<ITEM>,
                    newPosition: Int,
                    newItem: ITEM
                ) {
                    newList[newPosition] = oldItem
                }
            }

        fun <ITEM : Any> simple(handler: (ITEM, ITEM) -> Unit): SameItemConversionHandler<ITEM, List<ITEM>> =
            object : Simple<ITEM>() {
                override fun handleItemConversion(oldItem: ITEM, newItem: ITEM) {
                    handler(oldItem, newItem)
                }
            }
    }
}

