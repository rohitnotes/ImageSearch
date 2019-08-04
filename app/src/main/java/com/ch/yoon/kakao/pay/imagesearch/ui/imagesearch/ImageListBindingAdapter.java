package com.ch.yoon.kakao.pay.imagesearch.ui.imagesearch;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ch.yoon.kakao.pay.imagesearch.repository.remote.kakao.response.imagesearch.ImageInfo;
import com.ch.yoon.kakao.pay.imagesearch.ui.imagesearch.adapter.ImageListAdapter;
import com.ch.yoon.kakao.pay.imagesearch.utils.CollectionUtil;
import com.ch.yoon.kakao.pay.imagesearch.utils.GlideUtil;

import java.util.List;

/**
 * Creator : ch-yoon
 * Date : 2019-08-02.
 */
public class ImageListBindingAdapter {

    @BindingAdapter("countOfItemInLine")
    public static void setSpanCount(@NonNull RecyclerView recyclerView,
                                    @NonNull Integer countOfItemInLine) {
        final GridLayoutManager gridLayoutManager = (GridLayoutManager)recyclerView.getLayoutManager();
        if(gridLayoutManager != null) {
            gridLayoutManager.setSpanCount(countOfItemInLine);
        }
    }

    @BindingAdapter("searchImageInfoList")
    public static void setItems(@NonNull RecyclerView recyclerView,
                                @Nullable List<ImageInfo> imageInfoList) {
        final ImageListAdapter adapter = ((ImageListAdapter)recyclerView.getAdapter());
        if(adapter != null) {
            adapter.submitList(imageInfoList);

            if(CollectionUtil.isEmpty(imageInfoList)) {
                adapter.notifyDataSetChanged();
            }
        }
    }

    @BindingAdapter("imageSearchState")
    public static void setImageSearchState(@NonNull RecyclerView recyclerView,
                                           @NonNull ImageSearchState imageSearchState) {
        final ImageListAdapter adapter = ((ImageListAdapter)recyclerView.getAdapter());
        if(adapter != null) {
            switch (imageSearchState) {
                case NONE:
                case SUCCESS:
                    adapter.changeFooterViewVisibility(false);
                    break;
                case FAIL:
                    adapter.changeFooterViewVisibility(true);
                    break;
            }
        }
    }

    @BindingAdapter("loadImage")
    public static void loadImage(@NonNull ImageView imageView, @Nullable ImageInfo imageInfo) {
        if(imageInfo != null) {
            final String thumbnailUrl = imageInfo.getThumbnailUrl();
            GlideUtil.load(imageView, thumbnailUrl);
        }
    }

    @BindingAdapter("applySelectedState")
    public static void applySelectedState(@NonNull ImageView imageView,
                                          boolean isSelected) {
        imageView.setSelected(isSelected);
    }

}