package com.ch.yoon.imagesearch.presentation.imagedetail

import android.app.Application
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ch.yoon.imagesearch.R
import com.ch.yoon.imagesearch.data.repository.image.ImageRepository
import com.ch.yoon.imagesearch.data.repository.image.model.ImageDocument
import com.ch.yoon.imagesearch.presentation.base.BaseViewModel
import com.ch.yoon.imagesearch.presentation.common.livedata.SingleLiveEvent
import com.ch.yoon.imagesearch.util.extension.TAG
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Creator : ch-yoon
 * Date : 2019-10-29.
 */
class ImageDetailViewModel(
    application: Application,
    private val imageRepository: ImageRepository
) : BaseViewModel(application) {

    private val _imageUrlInfo = MutableLiveData<String>()
    val imageUrlInfo: LiveData<String> = _imageUrlInfo

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    private val _moveToWebEvent = SingleLiveEvent<String>()
    val moveToWebEvent: LiveData<String> = _moveToWebEvent

    private val _finishEventWithNotUpdate = SingleLiveEvent<Unit>()
    val finishEventWithNotUpdate: LiveData<Unit> = _finishEventWithNotUpdate

    private val _finishEventWithUpdate = SingleLiveEvent<ImageDocument>()
    val finishEventWithUpdate: LiveData<ImageDocument> = _finishEventWithUpdate

    private var imageDocument: ImageDocument? = null
    private var isUpdate = false

    fun showImageDetailInfo(receivedImageDocument: ImageDocument?) {
        receivedImageDocument?.let { document ->
            imageDocument = document
            _imageUrlInfo.value = document.imageUrl
            _isFavorite.value = document.isFavorite
        } ?: run {
            updateShowMessage(R.string.unknown_error)
            _finishEventWithNotUpdate.call()
        }
    }

    fun onClickFavorite() {
        imageDocument?.let { document ->
            document.isFavorite = true
            imageRepository.saveFavoriteImageDocument(document)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    isUpdate = true
                    imageDocument = document
                    _isFavorite.value = document.isFavorite
                }, { throwable ->
                    Log.d(TAG, throwable.message)
                })
        }
    }

    fun onClickWebButton() {
        imageDocument?.let { document ->
            if(TextUtils.isEmpty(document.docUrl)) {
                updateShowMessage(R.string.non_existent_url_error)
            } else {
                _moveToWebEvent.value = document.docUrl
            }
        }
    }

    fun onClickBackPress() {
        if(isUpdate) {
            _finishEventWithUpdate.value = imageDocument
        } else {
            _finishEventWithNotUpdate.call()
        }
    }

}