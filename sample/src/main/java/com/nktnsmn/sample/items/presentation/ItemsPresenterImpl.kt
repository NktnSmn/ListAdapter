package com.nktnsmn.sample.items.presentation

import android.content.Context
import com.nktnsmn.listadapter.diff.DiffFactory
import com.nktnsmn.listadapter.listitems.impl.DefaultConvertibleListItems
import com.nktnsmn.sample.items.bl.ItemsInteractor
import com.nktnsmn.sample.items.presentation.item.ImageItemVM
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class ItemsPresenterImpl(appContext: Context) : ItemsPresenter {

    private val itemsInteractor = ItemsInteractor(appContext)
    private val listItems = DefaultConvertibleListItems(DiffFactory.diffItemCallback())
    private val fullLifecycleDisposable = CompositeDisposable()
    private var view: ItemsView? = null

    override fun attachView(view: ItemsView) {
        this.view = view
        view.setupItems(listItems)
        if (listItems.isEmpty()) {
            refreshItems()
        }
    }

    override fun detachView() {
        view = null
        listItems.releaseObserver()
    }

    override fun destroy() {
        fullLifecycleDisposable.dispose()
    }

    override fun refreshItems() {
        fullLifecycleDisposable.add(
            itemsInteractor.getItems()
                .subscribeOn(Schedulers.io())
                .doOnSuccess { items ->
                    listItems.convertTo(items)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                    view?.hideRefresher()
                })
        )
    }

    override fun onImageItemClick(imageItemVM: ImageItemVM) {
        listItems.set(
            itemsInteractor.randomItem(imageItemVM.id),
            listItems.getAll().indexOf(imageItemVM)
        )
    }
}