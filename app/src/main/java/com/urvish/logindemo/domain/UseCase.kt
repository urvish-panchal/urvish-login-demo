package com.urvish.logindemo.domain

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

abstract class UseCase<T, Params> protected constructor() {

    private var compositeDisposable: CompositeDisposable? = null

    init {
        initCompositeSubscription()
    }

    private fun initCompositeSubscription() {
        compositeDisposable = CompositeDisposable()
    }

    private fun clearAllSubscription() {
        if (compositeDisposable != null) {
            compositeDisposable!!.clear()
        }
    }

    fun executeFlowable(subscriber: ResourceSubscriber<T>): CompositeDisposable {
        return executeFlowable(subscriber, null)
    }

    fun executeFlowable(subscriber: ResourceSubscriber<T>, params: Params?): CompositeDisposable {
        return addSubscription(
            executeAsFlowable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(subscriber)
        )
    }

    private fun executeAsFlowable(params: Params?): Flowable<T> {
        return buildUseCaseFlowable(params)
    }

    protected abstract fun buildUseCaseFlowable(params: Params?): Flowable<T>

    private fun addSubscription(subscription: ResourceSubscriber<T>?): CompositeDisposable {
        if (null != compositeDisposable && compositeDisposable!!.size() != 0) {
            initCompositeSubscription()
        }
        if (subscription != null && compositeDisposable!!.size() != 0) {
            assert(compositeDisposable != null)
            compositeDisposable!!.remove(subscription)
        }
        assert(subscription != null)
        assert(compositeDisposable != null)
        compositeDisposable!!.add(subscription!!)
        return compositeDisposable!!
    }
}