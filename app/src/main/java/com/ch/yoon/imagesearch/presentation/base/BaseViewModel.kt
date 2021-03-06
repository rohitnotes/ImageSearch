package com.ch.yoon.imagesearch.presentation.base

import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ch.yoon.imagesearch.presentation.common.livedata.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


/**
 * Creator : ch-yoon
 * Date : 2019-10-25.
 */
open class BaseViewModel(application: Application) : AndroidViewModel(application){

    private val compositeDisposable = CompositeDisposable()

    private val _showMessageEvent = SingleLiveEvent<String>()
    val showMessageEvent: LiveData<String> = _showMessageEvent

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    protected fun Disposable.disposeByOnCleared() {
        compositeDisposable.add(this)
    }

    protected fun updateShowMessage(@StringRes stringResId: Int) {
        _showMessageEvent.value = getApplication<Application>().getString(stringResId)
    }

}