package com.taqtile.tq1demo.settings.presentation.presenter;

import android.app.Activity;

import com.taqtile.tq1demo.data.model.SettingsInfo;
import com.taqtile.tq1demo.navigation.NavigationManager;

import br.com.taqtile.android.cleanbase.presentation.presenter.BasePresenter;
import br.com.taqtile.android.cleanbase.presentation.view.BaseView;

/**
 * Created by taqtile on 8/2/16.
 */
public interface SettingsContract {

    interface View extends BaseView<Presenter, NavigationManager> {

        void showSettings(SettingsInfo settingsInfo);

        void showError();

    }

    interface Presenter extends BasePresenter {

        void requestSettings();

        void geoLocationSwitchStateChanged(boolean isGeoLocationEnabled, boolean isPushNotificationEnabled, Activity activity);

        void pushNotificationSwitchStateChanged(boolean isGeoLocationEnabled, boolean isPushNotificationEnabled, Activity activity);

    }


}
