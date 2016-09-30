package com.taqtile.tq1demo.home.presentation.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import com.crashlytics.android.Crashlytics;
import com.taqtile.tq1demo.Constants;
import com.taqtile.tq1demo.Injection;
import com.taqtile.tq1demo.base.presentation.activity.TemplateDrawerActivity;
import com.taqtile.tq1demo.data.model.SettingsInfo;
import com.taqtile.tq1demo.home.presentation.fragment.HomeFragment;
import com.taqtile.tq1demo.home.presentation.presenter.HomePresenter;
import com.taqtile.tq1demo.util.SharedConstants;
import com.taqtile.tq1demo.util.TQGManager;
import com.taqtile.tq1demo.util.helper.SharedPreferencesHelper;

import br.com.taqtile.android.cleanbase.presentation.presenter.BasePresenter;
import br.com.taqtile.android.cleanbase.presentation.view.LoadingHandler;
import io.fabric.sdk.android.Fabric;
import taqtile.android.sdk.TQ;
import taqtile.android.sdk.TQGeotrigger;
import taqtile.android.sdk.analytics.TQDeviceInfo;

/**
 * Created by taqtile on 7/7/16.
 */

public class HomeActivity extends TemplateDrawerActivity implements LoadingHandler {

    private HomeFragment mFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setupTQ1();
    }

    private void setupTQ1() {

        askPermissionsIfNeeds();

        TQGManager tqgManager = new TQGManager();
        TQGeotrigger.shared().setManager(this, tqgManager);
        TQ.shared().init(Constants.TQ1_KEY, Constants.GCM_ID, getApplicationContext());
        TQ.shared().start();

        mFragment.setDeviceId(tqgManager.getDeviceId());

        SettingsInfo settingsInfo = getSettingsInfo();
        TQDeviceInfo.setPushEnabled(this, settingsInfo.isPushNotificationActivated());
        TQDeviceInfo.setLocationEnabled(this, settingsInfo.isGeoLocationActivated());
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

    private SettingsInfo getSettingsInfo() {
        SharedPreferencesHelper sharedPreferencesHelper = SharedPreferencesHelper.getInstance(this);
        SettingsInfo settingsInfo = (SettingsInfo) sharedPreferencesHelper.loadObj(SharedConstants.SHARED_PREFERENCES_SETTINGS_INFO, SettingsInfo.class);

        if (settingsInfo != null) {
            return settingsInfo;
        } else {
            // Default settings is TQG and TQ1 enabled
            return new SettingsInfo(true, true);
        }
    }

    private void askPermissionsIfNeeds() {
        if (!(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) || !TQ.shared().isLocationAuthorized(this)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }
    }
}
