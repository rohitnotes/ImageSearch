package com.ch.yoon.imagesearch.data.local.room.error

import com.ch.yoon.imagesearch.data.local.room.error.mapper.RoomExceptionMapper
import io.reactivex.*

class SingleExceptionTransformer<T> : SingleTransformer<T, T> {

    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream.onErrorResumeNext { throwable ->
            Single.error(RoomExceptionMapper.toRepositoryException(throwable))
        }
    }
}

