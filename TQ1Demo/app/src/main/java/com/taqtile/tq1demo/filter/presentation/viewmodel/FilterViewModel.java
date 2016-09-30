package com.taqtile.tq1demo.filter.presentation.viewmodel;

import br.com.taqtile.android.recyclerviews.filtersrecyclerview.model.FilterModel;

/**
 * Created by taqtile on 6/7/16.
 */
public class FilterViewModel implements FilterModel {

    private boolean mIsSelected;

    private String mDescription;

    private boolean mIsLastFilter;

    private int mItemsCount;

    public FilterViewModel(boolean mIsSelected, String mDescription, boolean mIsLastFilter, int mItemsCount) {
        this.mIsSelected = mIsSelected;
        this.mDescription = mDescription;
        this.mIsLastFilter = mIsLastFilter;
        this.mItemsCount = mItemsCount;
    }

    @Override
    public boolean isSelected() {
        return mIsSelected;
    }

    @Override
    public void setIsSelected(boolean b) {
        mIsSelected = b;
    }

    @Override
    public String getDescription() {
        return mDescription;
    }

    @Override
    public int getItemsCount() {
        return mItemsCount;
    }

    @Override
    public boolean getIsLastFilter() {
        return mIsLastFilter;
    }
}
