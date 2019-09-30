package com.nktnsmn.sample.base

import android.arch.lifecycle.ViewModel

class PresenterHolder<PRESENTER : BasePresenter<*>>(val presenter: PRESENTER) : ViewModel() {

    override fun onCleared() {
        super.onCleared()
        presenter.destroy()
    }
}