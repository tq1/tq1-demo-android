package com.taqtile.tq1demo.base.presentation.activity;

import com.taqtile.tq1demo.R;

/**
 * Created by taqtile on 6/6/16.
 */

public abstract class TemplateBackActivity
        extends br.com.taqtile.android.cleanbase.presentation.activity.BackActivity {

    @Override
    public int getActivityLayoutId() {
        return R.layout.activity_base_back;
    }

    @Override
    public int getFragmentContainerId() {
        return R.id.activity_base_back_contentFrame;
    }

    @Override
    public int getToolbarId() {
        return R.id.activity_base_back_toolbar;
    }

    @Override
    public int getLoadingViewContainerId() {
        return R.id.loading_view_root;
    }

    @Override
    public int getFABId() {
        return 0;
    }
}
