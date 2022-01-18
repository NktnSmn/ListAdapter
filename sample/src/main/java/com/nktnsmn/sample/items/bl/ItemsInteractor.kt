package com.nktnsmn.sample.items.bl

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.nktnsmn.listadapter.diff.item.ItemIdModel
import com.nktnsmn.sample.R
import com.nktnsmn.sample.items.presentation.item.ColorItemVM
import com.nktnsmn.sample.items.presentation.item.ImageItemVM
import com.nktnsmn.sample.items.presentation.item.attachment.AttachmentItemVM
import com.nktnsmn.sample.items.presentation.item.text.TextItemVM
import io.reactivex.Single
import kotlin.random.Random

private const val IMAGE_ITEM_TYPE: Int = 0
private const val LABEL_ITEM_TYPE: Int = 1
private const val COLOR_ITEM_TYPE: Int = 2
private const val ATTACHMENT_ITEM_TYPE: Int = 3

private const val ATTACHMENT_TYPES_NUMBER: Int = 5

class ItemsInteractor(private val appContext: Context) {

    private val itemTypes: Array<Int> = arrayOf(
        IMAGE_ITEM_TYPE,
        LABEL_ITEM_TYPE,
        COLOR_ITEM_TYPE,
        ATTACHMENT_ITEM_TYPE
    )

    private val uris: Array<String> = arrayOf(
        "https://bit.ly/2R7aV9C",
        "https://bit.ly/2sDOkI1",
        "https://bit.ly/2FEr19q",
        "https://bit.ly/2Mnyydt",
        "https://bit.ly/2CGKgev"
    )
    private val text: Array<String> = arrayOf(
        "Lorem ipsum dolor sit amet",
        "Phasellus ultricies tempor nisl vel mollis.",
        "Ut et enim sit amet mi sagittis efficitur vel iaculis nisl",
        "Vivamus id dui metus",
        "Proin et dapibus ante"
    )
    private val colorIds: Array<Int> = arrayOf(
        R.color.colorPrimary,
        R.color.colorPrimary2,
        R.color.colorAccent,
        R.color.colorAccent2,
        R.color.colorAccent3
    )
    private val attachmentNames: Array<String> = arrayOf(
        "Image Attachment.png",
        "Text Attachment.txt",
        "Pdf Attachment.pdf",
        "Table Attachment.xls",
        "Archive Attachment.rar"
    )

    fun getItems(): Single<MutableList<ItemIdModel>> =
        Single.fromCallable {
            if (Random.nextDouble(0.0, 1.0) <= 0.25) {
                mutableListOf()
            } else {
                mutableListOf<ItemIdModel>().also { list ->
                    for (i in 0..Random.nextInt(3000)) {
                        list.add(randomItem(i.toString()))
                    }
                }
            }
        }

    fun randomItem(id: String): ItemIdModel =
        when (itemTypes[Random.nextInt(itemTypes.size)]) {
            IMAGE_ITEM_TYPE -> {
                val uriType: Int = Random.nextInt(uris.size)
                ImageItemVM(id, uris[uriType], "New York $uriType")
            }
            LABEL_ITEM_TYPE -> TextItemVM(
                id,
                TextItemVM.Content(
                    text[Random.nextInt(
                        text.size
                    )]
                )
            )
            COLOR_ITEM_TYPE -> ColorItemVM(id, randomColor())
            ATTACHMENT_ITEM_TYPE -> {
                val attachmentType = Random.nextInt(ATTACHMENT_TYPES_NUMBER)
                AttachmentItemVM(
                    id,
                    R.drawable.ic_file,
                    colorByResId(colorIds[attachmentType]),
                    attachmentNames[attachmentType]
                )
            }
            else -> throw IllegalStateException()
        }

    private fun randomColor(): Int = colorByResId(colorIds[Random.nextInt(colorIds.size)])

    private fun colorByResId(@ColorRes colorRes: Int): Int = ContextCompat.getColor(appContext, colorRes)
}