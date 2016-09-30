package com.taqtile.tq1demo.data.model;

/**
 * Created by taqtile on 8/2/16.
 */
public class SettingsInfo {

    private boolean isGeoLocationActivated;
    private boolean isPushNotificationActivated;

    public SettingsInfo(boolean tqgEnabled, boolean tq1Enabled) {
        isGeoLocationActivated = tqgEnabled;
        isPushNotificationActivated = tq1Enabled;
    }

    public boolean isPushNotificationActivated() {
        return isPushNotificationActivated;
    }

    public boolean isGeoLocationActivated() {
        return isGeoLocationActivated;
    }

}
