package com.taqtile.tq1demo.home.presentation.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import br.com.taqtile.android.cleanbase.presentation.presenter.BasePresenter;
import br.com.taqtile.android.cleanbase.presentation.util.ActivityUtils;
import br.com.taqtile.android.cleanbase.presentation.view.LoadingHandler;
import com.taqtile.tq1demo.DrawerInjection;
import com.taqtile.tq1demo.Injection;
import com.taqtile.tq1demo.base.presentation.activity.TemplateDrawerActivity;
import com.taqtile.tq1demo.deviceregistration.service.DeviceRegistrationIntentService;
import com.taqtile.tq1demo.drawer.presentation.presenter.DrawerPresenter;
import com.taqtile.tq1demo.drawer.presentation.view.DrawerView;
import com.taqtile.tq1demo.home.presentation.fragment.HomeFragment;
import com.taqtile.tq1demo.home.presentation.presenter.HomePresenter;
import com.taqtile.tq1demo.navigation.NavigationHelper;

/**
 * Created by taqtile on 7/7/16.
 */

public class HomeActivity extends TemplateDrawerActivity implements LoadingHandler {

    private HomeFragment mFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupPushNotifications();
    }

    private void setupPushNotifications() {
        if (ActivityUtils.checkPlayServices(this)) {
            Intent intent = new Intent(this, DeviceRegistrationIntentService.class);
            startService(intent);
        }
    }

    @Override
    public Fragment getFragment() {
        HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager()
                .findFragmentById(getFragmentContainerId());
        if(homeFragment == null) {
            homeFragment = HomeFragment.newInstance();
        }

        mFragment = homeFragment;

        return mFragment;
    }

    @Override
    public BasePresenter getPresenter() {
        return new HomePresenter(
                Injection.provideUseCaseHandler(),
                mFragment
        );
    }

    public static void navigate(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
