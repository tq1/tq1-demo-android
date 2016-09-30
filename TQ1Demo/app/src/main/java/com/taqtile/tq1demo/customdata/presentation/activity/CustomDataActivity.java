package com.taqtile.tq1demo.customdata.presentation.activity;

import android.content.Context;
import android.content.Intent;

import com.taqtile.tq1demo.Injection;
import com.taqtile.tq1demo.base.presentation.activity.TemplateDrawerActivity;
import com.taqtile.tq1demo.customdata.presentation.fragment.CustomDataFragment;
import com.taqtile.tq1demo.customdata.presentation.presenter.CustomDataPresenter;
import com.taqtile.tq1demo.navigation.NavigationHelper;

import br.com.taqtile.android.cleanbase.presentation.presenter.BasePresenter;
import br.com.taqtile.android.cleanbase.presentation.view.LoadingHandler;

/**
 * Created by taqtile on 7/29/16.
 */
public class CustomDataActivity  extends TemplateDrawerActivity implements LoadingHandler {

    private CustomDataFragment mFragment;

    @Override
    public CustomDataFragment getFragment() {
        CustomDataFragment customDataFragment = (CustomDataFragment) getSupportFragmentManager()
                .findFragmentById(getFragmentContainerId());
        if (customDataFragment == null) {
            customDataFragment = CustomDataFragment.newInstance();
        }

        mFragment = customDataFragment;

        return mFragment;
    }

    @Override
    public BasePresenter getPresenter() {
        return new CustomDataPresenter(
                Injection.provideUseCaseHandler(),
                mFragment,
                Injection.provideSendCustomDataUseCase(getApplicationContext()),
                Injection.provideRetrieveCustomDataUseCase(getApplicationContext()),
                Injection.provideSaveCustomDataUseCase(getApplicationContext()),
                Injection.provideCustomDataToCustomDataListItemViewModelMapper(),
                new NavigationHelper(this));
    }

    public static void navigate(Context context) {
        Intent intent = new Intent(context, CustomDataActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
