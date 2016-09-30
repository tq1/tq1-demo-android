package com.taqtile.tq1demo.customdata.presentation.renderer;

import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.pedrogomez.renderers.Renderer;
import com.taqtile.tq1demo.R;
import com.taqtile.tq1demo.customdata.presentation.presenter.CustomDataContract;
import com.taqtile.tq1demo.customdata.presentation.viewmodel.CustomDataListItemViewModel;

import br.com.taqtile.android.textviews.CustomTextView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by taqtile on 7/29/16.
 */
public class CustomDataRenderer extends Renderer<CustomDataListItemViewModel> implements View.OnClickListener, View.OnLongClickListener {

    CustomDataContract.Presenter mPresenter;

    @BindView(R.id.custom_data_key_text_view)
    CustomTextView mKeyTextView;

    @BindView(R.id.custom_data_value_text_view)
    CustomTextView mValueTextView;

    public CustomDataRenderer(CustomDataContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void setUpView(View rootView) {
        rootView.setOnClickListener(this);
        rootView.setOnLongClickListener(this);
    }

    @Override
    protected void hookListeners(View rootView) {
        /*
         * Empty implementation substituted with the usage of ButterKnife library by Jake Wharton.
         */
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        View inflatedView = inflater.inflate(R.layout.renderer_customdata, parent, false);
        ButterKnife.bind(this, inflatedView);
        return inflatedView;
    }

    @Override
    public void render() {
        CustomDataListItemViewModel model = getContent();
        renderData(model);
    }

    private void renderData(CustomDataListItemViewModel model) {
        mKeyTextView.setText(model.getKey());
        mValueTextView.setText(model.getValue());
    }

    @Override
    public void onClick(View v) {
        mPresenter.customDataCellClicked(mKeyTextView.getText().toString(), mValueTextView.getText().toString());
    }

    @Override
    public boolean onLongClick(View v) {
        v.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
        mPresenter.customDataCellLongPressed(mKeyTextView.getText().toString(), mValueTextView.getText().toString());
        return true;
    }
}
