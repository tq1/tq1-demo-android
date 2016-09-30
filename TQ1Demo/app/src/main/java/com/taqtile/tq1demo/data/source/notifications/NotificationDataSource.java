package com.taqtile.tq1demo.data.source.notifications;

import com.taqtile.tq1demo.data.model.DataSourceResponse;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Map;

import taqtile.android.sdk.push.TQInbox;
import taqtile.android.sdk.push.TQInboxMessage;

/**
 * Created by taqtile on 7/26/16.
 */
public interface NotificationDataSource {

    DataSourceResponse<ArrayList<TQInboxMessage>> getNotifications();

    DataSourceResponse<Integer> getUnreadNotificationsCount();

    DataSourceResponse<TQInboxMessage> getNotification(String pushId);

    DataSourceResponse<Boolean> changeNotificationStatus(String pushId, TQInbox.TQInboxMessageStatus currentStatus);

    DataSourceResponse<Boolean> sendCustomStatus(String pushId, String status);

    DataSourceResponse<Boolean> sendCustomData(Map<String, String> customDataHash);

    DataSourceResponse<Map<String, String>> retrieveCustomData();

    DataSourceResponse<Boolean> saveCustomData(Map<String, String> customDataHash);

}
