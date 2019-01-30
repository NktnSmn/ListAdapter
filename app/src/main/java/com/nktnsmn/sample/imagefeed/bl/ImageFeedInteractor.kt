package com.nktnsmn.sample.imagefeed.bl

import com.nktnsmn.sample.imagefeed.ui.listitem.ImageListItemVM
import io.reactivex.Single

class ImageFeedInteractor {

    fun getNewYorkPhotos(): Single<List<ImageListItemVM>> = Single.fromCallable {
        mutableListOf(
            imageListItemVM("https://bit.ly/2R7aV9C", "New York 1"),
            imageListItemVM("https://bit.ly/2sDOkI1", "New York 2"),
            imageListItemVM("https://bit.ly/2FEr19q", "New York 3"),
            imageListItemVM("https://bit.ly/2Mnyydt", "New York 4"),
            imageListItemVM("https://bit.ly/2CGKgev", "New York 5")
        )
    }

    private fun imageListItemVM(uri: String, title: String): ImageListItemVM = ImageListItemVM(uri, uri, title)
}