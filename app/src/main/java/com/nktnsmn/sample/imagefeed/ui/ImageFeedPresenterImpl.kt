package com.nktnsmn.sample.imagefeed.ui

import com.nktnsmn.listadapter.base.diff.item.DiffIdentifiableItemCallback
import com.nktnsmn.listadapter.base.listitems.impl.AsyncConvertibleListItems
import com.nktnsmn.sample.base.NullableView
import com.nktnsmn.sample.imagefeed.bl.ImageFeedInteractor
import com.nktnsmn.sample.imagefeed.ui.listitem.ImageListItemVM
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class ImageFeedPresenterImpl : ImageFeedPresenter {

    private val imageFeedInteractor = ImageFeedInteractor()
    private val listItems = AsyncConvertibleListItems(DiffIdentifiableItemCallback())
    private val fullLifecycleDisposable = CompositeDisposable()
    private val view = NullableView<ImageFeedView>()

    override fun attachView(view: ImageFeedView) {
        this.view.set(view)
        view.setupImageFeedItems(listItems)
        if (listItems.isEmpty()) {
            loadImageFeed()
        }
    }

    override fun detachView() {
        view.reset()
        listItems.unbindUpdateCallback()
    }

    override fun destroy() {
        fullLifecycleDisposable.dispose()
        listItems.release()
    }

    override fun refreshImageFeed() {
        loadImageFeed()
    }

    private fun loadImageFeed() {
        fullLifecycleDisposable.add(
            imageFeedInteractor.getNewYorkPhotos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                    view.action { hideRefresher() }
                    listItems.convertTo(it)
                })
        )
    }

    override fun onImageListItemClick(imageListItemVM: ImageListItemVM) {
        view.action { showToast("Click on ${imageListItemVM.title}") }
    }
}