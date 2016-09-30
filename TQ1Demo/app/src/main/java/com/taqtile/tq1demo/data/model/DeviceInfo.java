package com.taqtile.tq1demo.data.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.ryanharter.auto.value.gson.AutoValueGsonTypeAdapterFactory;

/**
 * Created by taqtile on 7/11/16.
 */

@AutoValue
public abstract class DeviceInfo {

    public static DeviceInfo create(String token) {
        return new AutoValue_DeviceInfo(token);
    }

    public static DeviceInfo createFromJSON(String serializedDeviceInfo) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new AutoValueGsonTypeAdapterFactory())
                .create();
        DeviceInfo deviceInfo = gson.fromJson(serializedDeviceInfo, DeviceInfo.class);
        return deviceInfo;
    }

    public String serialize() {
        return new Gson().toJson(this);
    }

    public abstract String getToken();

    public static TypeAdapter<DeviceInfo> typeAdapter(Gson gson) {
        return new AutoValue_DeviceInfo.GsonTypeAdapter(gson);
    }

}
