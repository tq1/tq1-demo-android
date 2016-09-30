package com.taqtile.tq1demo.data.source.device;

import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.model.DeviceInfo;

/**
 * Created by taqtile on 7/11/16.
 */

public interface DeviceInfoDataSource {

    DataSourceResponse<DeviceInfo> getDeviceInfo();

    DataSourceResponse<Boolean> saveDeviceInfo(DeviceInfo deviceInfo);

}
