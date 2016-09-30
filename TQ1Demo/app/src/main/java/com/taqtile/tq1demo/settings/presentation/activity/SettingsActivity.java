package com.taqtile.tq1demo.settings.presentation.activity;

import android.content.Context;
import android.content.Intent;

import com.taqtile.tq1demo.Injection;
import com.taqtile.tq1demo.base.presentation.activity.TemplateDrawerActivity;
import com.taqtile.tq1demo.navigation.NavigationHelper;
import com.taqtile.tq1demo.settings.presentation.fragment.SettingsFragment;
import com.taqtile.tq1demo.settings.presentation.presenter.SettingsPresenter;

import br.com.taqtile.android.cleanbase.presentation.presenter.BasePresenter;
import br.com.taqtile.android.cleanbase.presentation.view.LoadingHandler;

/**
 * Created by taqtile on 8/2/16.
 */
public class SettingsActivity extends TemplateDrawerActivity implements LoadingHandler {

    private SettingsFragment mFragment;

    @Override
    public SettingsFragment getFragment() {
        SettingsFragment settingsFragment = (SettingsFragment) getSupportFragmentManager()
                .findFragmentById(getFragmentContainerId());
        if (settingsFragment == null) {
            settingsFragment = SettingsFragment.newInstance();
        }

        mFragment = settingsFragment;

        return mFragment;
    }

    @Override
    public BasePresenter getPresenter() {
        return new SettingsPresenter(
                Injection.provideUseCaseHandler(),
                mFragment,
                Injection.provideLoadSettingsUseCase(getApplicationContext()),
                Injection.provideSaveSettingsUseCase(getApplicationContext()),
                Injection.provideSetTQGStateUseCase(getApplicationContext()),
                Injection.provideSetTQ1StateUseCase(getApplicationContext()),
                new NavigationHelper(this));
    }

    public static void navigate(Context context) {
        Intent intent = new Intent(context, SettingsActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
