package com.taqtile.tq1demo.filter.presentation.viewmodel;

import java.util.ArrayList;
import java.util.List;

import br.com.taqtile.android.recyclerviews.filtersrecyclerview.model.FilterCategoryModel;

/**
 * Created by taqtile on 5/30/16.
 */

public class FilterCategoryViewModel implements FilterCategoryModel {

    private String mDescription;

    private boolean mIsExclusive;

    private boolean mIsInitiallyExpanded;

    private ArrayList<FilterViewModel> mFilters;

    public FilterCategoryViewModel(String mDescription, boolean mIsExclusive, boolean mIsInitiallyExpanded, ArrayList<FilterViewModel> mFilters) {
        this.mDescription = mDescription;
        this.mIsExclusive = mIsExclusive;
        this.mIsInitiallyExpanded = mIsInitiallyExpanded;
        this.mFilters = mFilters;
    }

    @Override
    public ArrayList<?> getFiltersList() {
        return mFilters;
    }

    @Override
    public String getDescription() {
        return mDescription;
    }

    @Override
    public boolean isExclusive() {
        return mIsExclusive;
    }

    @Override
    public List<?> getChildItemList() {
        return mFilters;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return mIsInitiallyExpanded;
    }
}
