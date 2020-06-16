package com.fivegearszerochill.noted.util.repository;

import androidx.paging.PagedList;

public class PagingHelper {
    private static final int PRE_FETCH_DISTANCE_MULTIPLIER = 3;

    public static final int PAGE_SIZE = 12;
    public static final int PRE_FETCH_DISTANCE = PAGE_SIZE * PRE_FETCH_DISTANCE_MULTIPLIER;


    public static PagedList.Config getPagingConfig() {
        return new PagedList.Config.Builder()
                .setPageSize(PAGE_SIZE)
                .setPrefetchDistance(PRE_FETCH_DISTANCE)
                .setEnablePlaceholders(true)
                .build();
    }
}
