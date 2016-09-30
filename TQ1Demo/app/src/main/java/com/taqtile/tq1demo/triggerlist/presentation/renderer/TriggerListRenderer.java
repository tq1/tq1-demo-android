package com.taqtile.tq1demo.triggerlist.presentation.renderer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedrogomez.renderers.Renderer;
import com.taqtile.tq1demo.R;
import com.taqtile.tq1demo.triggerlist.presentation.viewmodel.TriggerListItemViewModel;

import br.com.taqtile.android.textviews.CustomTextView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by taqtile on 7/26/16.
 */
public class TriggerListRenderer extends Renderer<TriggerListItemViewModel> {

    // region views
    @BindView(R.id.trigger_list_date)
    CustomTextView mTriggerDate;

    @BindView(R.id.trigger_list_type)
    CustomTextView mTriggerType;

    @BindView(R.id.trigger_list_description)
    CustomTextView mTriggerDescription;

    // end region
    @Override
    protected void setUpView(View rootView) {
        /*
         * Empty implementation substituted with the usage of ButterKnife library by Jake Wharton.
         */
    }

    @Override
    protected void hookListeners(View rootView) {
        /*
         * Empty implementation substituted with the usage of ButterKnife library by Jake Wharton.
         */
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        View inflatedView = inflater.inflate(R.layout.renderer_triggerlist, parent, false);
        ButterKnife.bind(this, inflatedView);
        return inflatedView;
    }

    @Override
    public void render() {
        TriggerListItemViewModel model = getContent();
        renderTriggerInfo(model);
    }

    private void renderTriggerInfo(TriggerListItemViewModel model) {
        mTriggerDate.setText(model.getDate());
        mTriggerType.setText(model.getType());
        mTriggerDescription.setText(mTriggerDescription.getContext().
                getString(R.string.trigger_list_description, model.getFenceId(), model.getFenceName()));
    }

}
