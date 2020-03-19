package com.nktnsmn.sample.base

import androidx.lifecycle.ViewModel

class PresenterHolder<PRESENTER : BasePresenter<*>>(val presenter: PRESENTER) : ViewModel() {

    override fun onCleared() {
        super.onCleared()
        presenter.destroy()
    }
}