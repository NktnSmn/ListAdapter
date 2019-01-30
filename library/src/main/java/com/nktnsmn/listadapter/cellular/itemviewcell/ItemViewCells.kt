package com.nktnsmn.listadapter.cellular.itemviewcell

class ItemViewCells(vararg itemViewCell: ItemViewCell) {

    val itemViewCells: List<ItemViewCell> = listOf(*itemViewCell)

    fun getForItem(item: Any): ItemViewCell =
        itemViewCells.find { it.isSuitableForItem(item) } ?: throw Exception("Ячейка для элемента $item не найдена")

    fun getForViewType(viewType: Int): ItemViewCell =
        itemViewCells.find { it.isSameViewType(viewType) } ?: throw Exception("Ячейка для $viewType не найдена")
}