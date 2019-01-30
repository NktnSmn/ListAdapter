package com.nktnsmn.sample.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View

abstract class BasePresenterFragment<VIEW, PRESENTER : BasePresenter<VIEW>> : Fragment() {

    protected lateinit var presenter: PRESENTER
        private set

    abstract fun createPresenter(): PRESENTER

    abstract fun getPresenterView(): VIEW

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter =
            ViewModelProvider(
                this,
                object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return PresenterHolder(createPresenter()) as T
                    }
                }
            )
                .get(PresenterHolder::class.java).presenter as PRESENTER
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(getPresenterView())
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }
}