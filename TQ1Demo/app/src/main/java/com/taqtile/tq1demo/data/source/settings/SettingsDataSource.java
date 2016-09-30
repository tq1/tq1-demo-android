package com.taqtile.tq1demo.data.source.settings;

import android.app.Activity;

import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.model.SettingsInfo;

/**
 * Created by taqtile on 8/2/16.
 */
public interface SettingsDataSource {

    DataSourceResponse<SettingsInfo> getSettingsInfo();
    DataSourceResponse<Boolean> saveSettingsInfo(SettingsInfo settingsInfo);

    DataSourceResponse<Boolean> setPushNotificationEnabled(boolean isPushNotificationEnabled, Activity activity);
    DataSourceResponse<Boolean> setGeoLocationEnabled(boolean isGeoLocationEnabled, Activity activity);
}
