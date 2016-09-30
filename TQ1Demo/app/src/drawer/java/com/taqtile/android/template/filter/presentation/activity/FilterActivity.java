package com.taqtile.tq1demo.filter.presentation.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import br.com.taqtile.android.cleanbase.presentation.presenter.BasePresenter;
import com.taqtile.tq1demo.base.presentation.activity.TemplateBackActivity;
import java.util.ArrayList;

import com.taqtile.tq1demo.data.model.FilterCategory;
import com.taqtile.tq1demo.filter.presentation.fragment.FilterFragment;
import com.taqtile.tq1demo.filter.presentation.presenter.FilterPresenter;
import com.taqtile.tq1demo.Injection;

/**
 * Created by taqtile on 5/31/16.
 */

public class FilterActivity extends TemplateBackActivity {

    public static final String ARGUMENT_ACTIVE_FILTER_LIST  = "ACTIVE_FILTER_LIST";
    public static final int FILTER_REQUEST_CODE = 2;

    private FilterFragment mFragment;

    @Override
    public Fragment getFragment() {
        FilterFragment filterFragment = (FilterFragment) getSupportFragmentManager()
                .findFragmentById(getFragmentContainerId());
        if (filterFragment == null) {
            filterFragment = FilterFragment.newInstance();
        }

        mFragment = filterFragment;
        return mFragment;
    }

    @Override
    public BasePresenter getPresenter() {
        ArrayList<FilterCategory> activeFilters = new ArrayList<>();

        if (getIntent().hasExtra(ARGUMENT_ACTIVE_FILTER_LIST)) {
            activeFilters = getIntent().getParcelableArrayListExtra(ARGUMENT_ACTIVE_FILTER_LIST);
        }

        return new FilterPresenter(
                Injection.provideUseCaseHandler(),
                mFragment,
                Injection.provideGetFiltersUseCase(getApplicationContext()),
                Injection.provideFilterCategoryToFilterCategoryViewModelMapper(),
                activeFilters);
    }

    public static void navigate(Context context, ArrayList<FilterCategory> activeFilters) {
        Intent intent = new Intent(context, FilterActivity.class);
        intent.putExtra(ARGUMENT_ACTIVE_FILTER_LIST, activeFilters);
        context.startActivity(intent);
    }
}
