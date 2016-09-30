package com.taqtile.tq1demo.recyclerviews.filter;

import android.content.Context;
import android.view.View;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;

import java.util.List;

import br.com.taqtile.android.recyclerviews.filtersrecyclerview.adapter.FiltersRecyclerAdapter;
import br.com.taqtile.android.recyclerviews.filtersrecyclerview.model.FilterCategoryModel;
import com.taqtile.tq1demo.recyclerviews.R;

/**
 * Created by taqtile on 6/6/16.
 */

public class TemplateFilterRecyclerAdapter extends FiltersRecyclerAdapter {


    public TemplateFilterRecyclerAdapter(Context context, List<FilterCategoryModel> filters) {
        super(context, filters);
    }

    public TemplateFilterRecyclerAdapter(Context context, List<FilterCategoryModel> filters, boolean showHeader) {
        super(context, filters, showHeader);
    }

    @Override
    public int getFilterTextFormatStringId() {
        return R.string.filters_text_format;
    }

    @Override
    public int getParentViewId() {
        return R.layout.filter_parent_view;
    }

    @Override
    public int getChildViewId() {
        return R.layout.filter_child_view;
    }

    @Override
    public ParentViewHolder getParentViewHolder(View view) {
        return new TemplateFilterParentViewHolder(view);
    }

    @Override
    public ChildViewHolder getChildViewHolder(View view) {
        return new TemplateFilterChildViewHolder(view);
    }
}
