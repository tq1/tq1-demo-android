package com.taqtile.tq1demo.recyclerviews.filter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import br.com.taqtile.android.checkboxes.CustomCheckbox;
import br.com.taqtile.android.recyclerviews.filtersrecyclerview.viewholder.FiltersChildViewHolder;
import com.taqtile.tq1demo.recyclerviews.R;
import br.com.taqtile.android.textviews.CustomTextView;

/**
 * Created by taqtile on 6/6/16.
 */

public class TemplateFilterChildViewHolder extends FiltersChildViewHolder {

    private RadioButton mRadioView;

    private View mDividerView;

    private View mClickableView;

    private CustomTextView mLabelView;

    private CustomCheckbox mCheckboxView;

    public TemplateFilterChildViewHolder(View itemView) {
        super(itemView);
        bindViews(itemView);
    }

    @Override
    public void bindViews(View root) {
        mRadioView = (RadioButton) root.findViewById(R.id.filters_child_radio);
        mDividerView = (View) root.findViewById(R.id.filters_child_divider);
        mClickableView = (View) root.findViewById(R.id.filters_child_clickable_view);
        mLabelView = (CustomTextView) root.findViewById(R.id.filters_child_label);
        mCheckboxView = (CustomCheckbox) root.findViewById(R.id.filters_child_checkbox);
    }

    @Override
    public RadioButton getRadioButtonView() {
        return mRadioView;
    }

    @Override
    public View getDividerView() {
        return mDividerView;
    }

    @Override
    public View getClickableView() {
        return mClickableView;
    }

    @Override
    public TextView getLabelView() {
        return mLabelView;
    }

    @Override
    public CheckBox getCheckBoxView() {
        return mCheckboxView;
    }
}
