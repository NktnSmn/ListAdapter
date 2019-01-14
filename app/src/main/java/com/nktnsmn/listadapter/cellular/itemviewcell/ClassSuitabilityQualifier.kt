package com.nktnsmn.listadapter.cellular.itemviewcell

class ClassSuitabilityQualifier<T>(private val itemClass: Class<T>) : ItemViewCell.SuitabilityQualifier {

    override fun isSuitableForItem(item: Any): Boolean = item.javaClass === itemClass
}