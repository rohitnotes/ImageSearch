package com.ch.yoon.kakao.pay.imagesearch.presentation.imagesearch.searchbox

import androidx.databinding.BindingAdapter
import com.ch.yoon.suggetionsearchview.SuggestionSearchView
import com.ch.yoon.suggetionsearchview.adapter.DefaultSuggestionAdapter
import com.ch.yoon.suggetionsearchview.model.SearchLog
import java.util.ArrayList

/**
 * Creator : ch-yoon
 * Date : 2019-10-27.
 */
@BindingAdapter("searchLogList")
fun setItems(suggestionSearchView: SuggestionSearchView, searchLogList: List<SearchLog>?) {
    val adapter = suggestionSearchView.getAdapter() as DefaultSuggestionAdapter?
    adapter?.submitList(if (searchLogList == null) null else ArrayList(searchLogList))
}

@BindingAdapter("close")
fun close(suggestionSearchView: SuggestionSearchView, closeEvent: Unit?) {
    suggestionSearchView.hide()
}