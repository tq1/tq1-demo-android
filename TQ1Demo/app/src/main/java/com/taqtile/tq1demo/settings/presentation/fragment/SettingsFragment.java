package com.taqtile.tq1demo.settings.presentation.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.taqtile.tq1demo.R;
import com.taqtile.tq1demo.data.model.SettingsInfo;
import com.taqtile.tq1demo.navigation.NavigationManager;
import com.taqtile.tq1demo.settings.presentation.presenter.SettingsContract;

import br.com.taqtile.android.cleanbase.presentation.view.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 8/2/16.
 */
public class SettingsFragment extends BaseFragment implements SettingsContract.View, CompoundButton.OnCheckedChangeListener {

    private SettingsContract.Presenter mPresenter;

    //region ViewComponents

    @BindView(R.id.tqg_switch_button)
    Switch mTQGSwitch;

    @BindView(R.id.tq1_switch_button)
    Switch mTQ1Switch;

    //endregion

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    public SettingsFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, root);
        setupSwitches();
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.start();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.resume();
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
    public void setPresenter(SettingsContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setNavigationManager(NavigationManager navigationManager) {

    }

    private void setupSwitches() {
        mTQGSwitch.setOnCheckedChangeListener(this);
        mTQ1Switch.setOnCheckedChangeListener(this);

    }


    @Override
    public void showSettings(SettingsInfo settingsInfo) {
            mTQGSwitch.setChecked(settingsInfo.isGeoLocationActivated());
            mTQ1Switch.setChecked(settingsInfo.isPushNotificationActivated());
    }

    @Override
    public void showError() {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView == mTQGSwitch) {
            mPresenter.geoLocationSwitchStateChanged(mTQGSwitch.isChecked(), mTQ1Switch.isChecked(), getActivity());
        } else if (buttonView == mTQ1Switch) {
            mPresenter.pushNotificationSwitchStateChanged(mTQGSwitch.isChecked(), mTQ1Switch.isChecked(), getActivity());
        }
    }
}
