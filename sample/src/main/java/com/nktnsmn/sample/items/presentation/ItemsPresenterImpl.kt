package com.nktnsmn.sample.items.presentation

import android.content.Context
import android.util.Log
import com.nktnsmn.listadapter.diff.DiffCallbackWithHandler
import com.nktnsmn.listadapter.diff.diffItemCallback
import com.nktnsmn.listadapter.diff.item.DiffSameItemHandler
import com.nktnsmn.listadapter.diff.item.ItemIdModel
import com.nktnsmn.listadapter.listitems.ConvertibleListItems
import com.nktnsmn.listadapter.listitems.impl.ConvertibleListItemsImpl
import com.nktnsmn.sample.items.bl.ItemsInteractor
import com.nktnsmn.sample.items.presentation.item.ImageItemVM
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class ItemsPresenterImpl(appContext: Context) : ItemsPresenter {

    private val itemsInteractor = ItemsInteractor(appContext)
    private val listItems: ConvertibleListItems<ItemIdModel> = ConvertibleListItemsImpl(diffItemCallback())
    private val sameItemConversionHandler = DiffSameItemHandler.insertionIntoNewList<ItemIdModel>()
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
        listItems.observer = null
    }

    override fun destroy() {
        fullLifecycleDisposable.dispose()
    }

    override fun refreshItems() {
        fullLifecycleDisposable.add(
            itemsInteractor.getItems()
                .subscribeOn(Schedulers.computation())
                .doOnSuccess { items ->
                    Log.d("Looog", "${items.size}")
                    listItems.convertTo(items) { oldItems, diffItemCallback ->
                        DiffCallbackWithHandler(oldItems, items, diffItemCallback, sameItemConversionHandler)
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer {
                    view?.hideRefresher()
                })
        )
    }

    override fun onImageItemClick(imageItemVM: ImageItemVM) {
        //listItems.set(
        //    itemsInteractor.randomItem(imageItemVM.id),
        //    listItems.getAll().indexOf(imageItemVM)
        //)
    }
}