package com.taqtile.tq1demo.data.source.settings;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.taqtile.tq1demo.Constants;
import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.model.SettingsInfo;
import com.taqtile.tq1demo.util.SharedConstants;
import com.taqtile.tq1demo.util.TQGManager;
import com.taqtile.tq1demo.util.helper.SharedPreferencesHelper;
import com.taqtile.tqgeolocationsdk.TQGeoTracker;

import taqtile.android.sdk.TQ;
import taqtile.android.sdk.TQGeotrigger;
import taqtile.android.sdk.analytics.TQDeviceInfo;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 8/2/16.
 */
public class SettingsLocalDataSource implements SettingsDataSource {

    private static SettingsLocalDataSource INSTANCE;

    @NonNull
    private final Context mContext;

    // Prevent direct instantiation
    private SettingsLocalDataSource(@NonNull Context context) {
        mContext = checkNotNull(context, "context can't be null");
    }

    @Override
    public DataSourceResponse<SettingsInfo> getSettingsInfo() {

        SharedPreferencesHelper preferencesHelper = SharedPreferencesHelper.getInstance(mContext);
        SettingsInfo settingsInfo = (SettingsInfo) preferencesHelper.loadObj(SharedConstants.SHARED_PREFERENCES_SETTINGS_INFO, SettingsInfo.class);

        if (settingsInfo != null) {
            return new DataSourceResponse<>(settingsInfo);
        } else {
            // Default settings is TQG and TQ1 enabled
            return new DataSourceResponse<>(new SettingsInfo(true, true));
        }
    }

    @Override
    public DataSourceResponse<Boolean> saveSettingsInfo(SettingsInfo settingsInfo) {
        SharedPreferencesHelper preferencesHelper = SharedPreferencesHelper.getInstance(mContext);
        preferencesHelper.saveObj(SharedConstants.SHARED_PREFERENCES_SETTINGS_INFO, settingsInfo);
        return new DataSourceResponse<>(true);
    }

    @Override
    public DataSourceResponse<Boolean> setPushNotificationEnabled(final boolean isPushNotificationEnabled, final Activity activity) {
        TQDeviceInfo.setPushEnabled(mContext, isPushNotificationEnabled);
        return new DataSourceResponse<>(true);
    }

    @Override
    public DataSourceResponse<Boolean> setGeoLocationEnabled(final boolean isGeoLocationEnabled, final Activity activity) {
        TQDeviceInfo.setLocationEnabled(mContext, isGeoLocationEnabled);
        return new DataSourceResponse<>(true);
    }

    public static SettingsLocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SettingsLocalDataSource(context);
        }

        return INSTANCE;
    }

    private void askPermissions(Activity activity) {
        if (!(ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) || !TQ.shared().isLocationAuthorized(activity)) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }
    }

}
