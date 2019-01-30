package com.nktnsmn.sample.base

interface BasePresenter<VIEW> {

    fun attachView(view: VIEW)

    fun detachView()

    fun destroy()
}