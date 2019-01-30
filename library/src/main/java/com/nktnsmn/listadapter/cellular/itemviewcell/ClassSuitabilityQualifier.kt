package com.nktnsmn.listadapter.cellular.itemviewcell

class ClassSuitabilityQualifier(private val itemClass: Class<*>) : ItemViewCell.SuitabilityQualifier {

    override fun isSuitableForItem(item: Any): Boolean = item.javaClass === itemClass
}