package com.nktnsmn.listadapter.diff.item

interface DiffSameItemHandler<ITEM : Any, in NEW_ITEMS : List<ITEM>> {

    fun handleItemConversion(
        oldList: List<ITEM>,
        oldPosition: Int,
        oldItem: ITEM,
        newList: NEW_ITEMS,
        newPosition: Int,
        newItem: ITEM
    )

    companion object {

        fun <ITEM : Any> insertionIntoNewList(): DiffSameItemHandler<ITEM, MutableList<ITEM>> =
            object : DiffSameItemHandler<ITEM, MutableList<ITEM>> {
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

        fun <ITEM : Any> simple(handler: (ITEM, ITEM) -> Unit): DiffSameItemHandler<ITEM, List<ITEM>> =
            object : DiffSameItemHandler<ITEM, List<ITEM>> {
                override fun handleItemConversion(
                    oldList: List<ITEM>,
                    oldPosition: Int,
                    oldItem: ITEM,
                    newList: List<ITEM>,
                    newPosition: Int,
                    newItem: ITEM
                ) {
                    handler(oldItem, newItem)
                }
            }
    }
}

