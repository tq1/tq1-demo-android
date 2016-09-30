package com.taqtile.tq1demo.recyclerviews.filter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.taqtile.android.recyclerviews.filtersrecyclerview.viewholder.FiltersParentViewHolder;
import com.taqtile.tq1demo.recyclerviews.R;
import br.com.taqtile.android.textviews.CustomTextView;

/**
 * Created by taqtile on 6/6/16.
 */

public class TemplateFilterParentViewHolder extends FiltersParentViewHolder {

    private View mHeaderView;

    private CustomTextView mLabelView;

    private ImageView mArrowView;


    public TemplateFilterParentViewHolder(View itemView) {
        super(itemView);
        bindViews(itemView);
    }

    @Override
    public void bindViews(View root) {
        mHeaderView = (View) root.findViewById(R.id.filters_parent_header);
        mLabelView = (CustomTextView) root.findViewById(R.id.filters_parent_label);
        mArrowView = (ImageView) root.findViewById(R.id.filters_parent_arrow);
    }

    @Override
    public View getHeaderView() {
        return mHeaderView;
    }

    @Override
    public TextView getLabelView() {
        return mLabelView;
    }

    @Override
    public ImageView getArrowView() {
        return mArrowView;
    }

    @Override
    public int getArrowColorId() {
        return R.color.color_gray_darker;
    }
}
