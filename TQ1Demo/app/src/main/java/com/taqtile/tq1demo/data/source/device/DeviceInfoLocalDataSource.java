package com.taqtile.tq1demo.data.source.device;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.model.DeviceInfo;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 7/11/16.
 */

public class DeviceInfoLocalDataSource implements DeviceInfoDataSource {

    private static final String DEVICEINFO_PREFERENCE_FILE_KEY = "DEVICEINFO_FILE_KEY";
    private static final String DEVICEINFO_PREFERENCE_OBJ_KEY = "DEVICEINFO_OBJ_KEY";

    private static DeviceInfoLocalDataSource INSTANCE;

    @NonNull
    private final Context mContext;

    private DeviceInfoLocalDataSource(@NonNull Context context) {
        mContext = checkNotNull(context, "Context cannot be null");
    }

    public static DeviceInfoLocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new DeviceInfoLocalDataSource(context);
        }

        return INSTANCE;
    }

    @Override
    public DataSourceResponse<DeviceInfo> getDeviceInfo() {
        String serializedDeviceInfo = deviceInfoSharedPreferences().getString(DEVICEINFO_PREFERENCE_OBJ_KEY, null);
        DeviceInfo deviceInfo = DeviceInfo.createFromJSON(serializedDeviceInfo);

        return new DataSourceResponse<>(deviceInfo);
    }

    @Override
    public DataSourceResponse<Boolean> saveDeviceInfo(DeviceInfo deviceInfo) {

        DataSourceResponse response;

        boolean success = deviceInfoSharedPreferences().edit()
                .putString(DEVICEINFO_PREFERENCE_OBJ_KEY, deviceInfo.serialize())
                .commit();

        if (success) {
            response = new DataSourceResponse(true);
        } else {
            //TODO: Handle error
            response = new DataSourceResponse(false);
        }

        return response;
    }

    private SharedPreferences deviceInfoSharedPreferences() {
        String sharedPreferencesFileName = String.format("%s.%s",
                mContext.getApplicationContext().getPackageName(), DEVICEINFO_PREFERENCE_FILE_KEY);

        return mContext.getSharedPreferences(sharedPreferencesFileName,
                Context.MODE_PRIVATE);
    }
}
