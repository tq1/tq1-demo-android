package com.taqtile.tq1demo.data.source.device;


import android.support.annotation.NonNull;

import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.model.DeviceInfo;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 7/11/16.
 */

public class DeviceInfoRepository implements DeviceInfoDataSource {

    private static DeviceInfoRepository INSTANCE = null;

    private final DeviceInfoDataSource mDeviceInfoLocalDataSource;

    DeviceInfo mCachedDeviceInfo;

    boolean mCacheIsDirty = false;

    private DeviceInfoRepository(@NonNull DeviceInfoDataSource deviceInfoDataSource) {
        mDeviceInfoLocalDataSource = checkNotNull(deviceInfoDataSource);
    }

    public static DeviceInfoRepository getInstance(DeviceInfoDataSource localDeviceInfoDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new DeviceInfoRepository(localDeviceInfoDataSource);
        }

        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public DataSourceResponse<DeviceInfo> getDeviceInfo() {

        DataSourceResponse<DeviceInfo> response =
                mDeviceInfoLocalDataSource.getDeviceInfo();

        if (response.isSuccess()) {
            return response;
        } else {
            //TODO: Handle error
            return null;
        }
    }

    @Override
    public DataSourceResponse<Boolean> saveDeviceInfo(DeviceInfo deviceInfo) {

        DataSourceResponse<Boolean> response =
                mDeviceInfoLocalDataSource.saveDeviceInfo(deviceInfo);

        if (response.isSuccess()) {
            return response;
        } else {
            //TODO: Handle error
            return null;
        }
    }
}
