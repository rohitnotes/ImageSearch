package com.ch.yoon.kakao.pay.imagesearch.data.repository

import com.ch.yoon.kakao.pay.imagesearch.data.local.room.entity.SearchLog
import com.ch.yoon.kakao.pay.imagesearch.data.remote.kakao.request.ImageSearchRequest
import com.ch.yoon.kakao.pay.imagesearch.data.remote.kakao.response.ImageSearchResponse
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 * Creator : ch-yoon
 * Date : 2019-10-28
 **/
class ImageRepositoryImpl(
    private val imageRemoteDataSource: ImageRemoteDataSource,
    private val imageLocalDataSource: ImageLocalDataSource
) : ImageRepository {

    override fun requestSearchLogList(): Single<List<SearchLog>> {
        return imageLocalDataSource.selectAllSearchLog()
            .subscribeOn(Schedulers.io())
    }

    override fun requestImageList(imageSearchRequest: ImageSearchRequest): Single<ImageSearchResponse> {
        return imageRemoteDataSource.requestImageList(imageSearchRequest)
            .subscribeOn(Schedulers.io())
    }

    override fun deleteSearchLog(keyword: String): Completable {
        return imageLocalDataSource.deleteSearchLog(keyword)
            .subscribeOn(Schedulers.io())
    }

    override fun insertOrUpdateSearchLog(keyword: String): Single<SearchLog> {
        return imageLocalDataSource.insertOrUpdateSearchLog(keyword)
            .subscribeOn(Schedulers.io())
    }
}