package com.taqtile.tq1demo.home.presentation.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.taqtile.android.cleanbase.presentation.view.BaseFragment;

import com.taqtile.tq1demo.Constants;
import com.taqtile.tq1demo.R;
import com.taqtile.tq1demo.home.presentation.presenter.HomeContract;
import com.taqtile.tq1demo.navigation.NavigationManager;

import br.com.taqtile.android.textviews.CustomTextView;

import butterknife.BindView;

import butterknife.ButterKnife;
import taqtile.android.sdk.TQ;

/**
 * Created by taqtile on 7/6/16.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View {

    private HomeContract.Presenter mPresenter;

    private String deviceId = "";

    //region ViewComponents

    @BindView(R.id.app_key_text_view)
    CustomTextView mAppKey;
    @BindView(R.id.app_version_text_view)
    CustomTextView mAppVersion;
    @BindView(R.id.device_id_text_view)
    CustomTextView mDeviceId;
    @BindView(R.id.geo_tracking_id_text_view)
    CustomTextView mGeoTrackingId;
    @BindView(R.id.tq1_sdk_version_text_view)
    CustomTextView mTQ1Version;
    @BindView(R.id.tqg_sdk_version_text_view)
    CustomTextView mTQGVersion;


    //endregion

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    public HomeFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, root);
        setupView();
        return root;
    }

    private void setupView() {

        mAppKey.setText(Constants.TQ1_KEY);
        mGeoTrackingId.setText(Constants.TQG_ID);
        if (deviceId != null && deviceId.length() > 0) {
            mDeviceId.setText(deviceId);
        } else {
            mDeviceId.setText(getString(R.string.tq1_not_enabled_message));
        }
        mAppVersion.setText(com.taqtile.tq1demo.BuildConfig.VERSION_NAME);
        mTQ1Version.setText(taqtile.android.sdk.BuildConfig.VERSION_NAME);
        mTQGVersion.setText(com.taqtile.tqgeolocationsdk.BuildConfig.VERSION_NAME);
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public boolean refresh() {
        return false;
    }

    @Override
    public void willAppear() {
    }

    @Override
    public void willDisappear() {

    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setNavigationManager(NavigationManager navigationManager) {

    }
}
