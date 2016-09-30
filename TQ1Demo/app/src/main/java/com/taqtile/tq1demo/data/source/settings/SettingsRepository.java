package com.taqtile.tq1demo.data.source.settings;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.model.DataSourceResponseError;
import com.taqtile.tq1demo.data.model.SettingsInfo;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 8/2/16.
 */
public class SettingsRepository implements SettingsDataSource {

    private static SettingsRepository INSTANCE = null;

    private final SettingsDataSource mSettingsDataSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    SettingsInfo mCachedSettingsInfo;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean mCacheIsDirty = false;

    /**
     * Prevents direct instantiation. This class is instantiated by the Injection class.
     */
    private SettingsRepository(@NonNull SettingsDataSource settingsDataSource) {
        mSettingsDataSource = checkNotNull(settingsDataSource);
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param settingsDataSource the backend data source
     * @return the {@link SettingsRepository} instance
     */
    public static SettingsRepository getInstance(SettingsDataSource settingsDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new SettingsRepository(settingsDataSource);
        }

        return INSTANCE;
    }

    /**
     * Used to force {@link #getInstance(SettingsDataSource)} to create a new instance
     * next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }


    /**
     * Gets the settings info
     *
     * @return A {@link DataSourceResponse} containing either an {@link SettingsInfo} or
     * a {@link DataSourceResponseError} object.
     */
    @Override
    public DataSourceResponse<SettingsInfo> getSettingsInfo() {
        DataSourceResponse<SettingsInfo> response =
                mSettingsDataSource.getSettingsInfo();

        if (response.isSuccess()) {
            return response;
        } else {
            // TODO: handle error
            return null;
        }
    }

    @Override
    public DataSourceResponse<Boolean> saveSettingsInfo(SettingsInfo settingsInfo) {
        DataSourceResponse<Boolean> response =
                mSettingsDataSource.saveSettingsInfo(settingsInfo);

        if (response.isSuccess()) {
            return response;
        } else {
            // TODO: handle error
            return null;
        }
    }

    @Override
    public DataSourceResponse<Boolean> setPushNotificationEnabled(boolean isPushNotificationEnabled, Activity activity) {
        DataSourceResponse<Boolean> response =
                mSettingsDataSource.setPushNotificationEnabled(isPushNotificationEnabled, activity);

        if (response.isSuccess()) {
            return response;
        } else {
            // TODO: handle error
            return null;
        }
    }

    @Override
    public DataSourceResponse<Boolean> setGeoLocationEnabled(boolean isGeoLocationEnabled, Activity activity) {
        DataSourceResponse<Boolean> response =
                mSettingsDataSource.setGeoLocationEnabled(isGeoLocationEnabled, activity);

        if (response.isSuccess()) {
            return response;
        } else {
            // TODO: handle error
            return null;
        }
    }
}
