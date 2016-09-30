package com.taqtile.tq1demo.data.source.notifications;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.reflect.TypeToken;
import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.util.SharedConstants;
import com.taqtile.tq1demo.util.helper.SharedPreferencesHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import taqtile.android.sdk.TQAnalytics;
import taqtile.android.sdk.push.TQInbox;
import taqtile.android.sdk.push.TQInboxMessage;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 7/26/16.
 */
public class NotificationLocalDataSource implements NotificationDataSource {

    private static NotificationLocalDataSource INSTANCE;

    @NonNull
    private final Context mContext;

    // Prevent direct instantiation
    private NotificationLocalDataSource(@NonNull Context context) {
        mContext = checkNotNull(context, "context can't be null");
    }

    @Override
    public DataSourceResponse<ArrayList<TQInboxMessage>> getNotifications() {

        TQInbox inbox = new TQInbox(mContext);
        return new DataSourceResponse<>(inbox.getInboxMessageList());
    }

    @Override
    public DataSourceResponse<Integer> getUnreadNotificationsCount() {
        TQInbox inbox = new TQInbox(mContext);
        return new DataSourceResponse<>(inbox.getInboxMessageCount(TQInbox.TQInboxMessageStatus.UNREAD));
    }

    @Override
    public DataSourceResponse<TQInboxMessage> getNotification(String pushId) {
        TQInbox inbox = new TQInbox(mContext);
        return new DataSourceResponse<>(inbox.getInboxMessage(pushId));
    }

    @Override
    public DataSourceResponse<Boolean> changeNotificationStatus(String pushId, TQInbox.TQInboxMessageStatus currentStatus) {
        TQInbox inbox = new TQInbox(mContext);
        if (currentStatus == TQInbox.TQInboxMessageStatus.READ) {
            inbox.markAsUnread(pushId);
        } else {
            inbox.markAsUnread(pushId);
        }
        return new DataSourceResponse<>(true);
    }

    @Override
    public DataSourceResponse<Boolean> sendCustomStatus(String pushId, String status) {
        TQInbox inbox = new TQInbox(mContext);
        inbox.addCustomStatus(pushId, status);
        return new DataSourceResponse<>(true);
    }

    @Override
    public DataSourceResponse<Boolean> sendCustomData(Map<String, String> customDataHash) {
        TQAnalytics.shared().addCustomData(customDataHash);
        return new DataSourceResponse<>(true);
    }

    @Override
    public DataSourceResponse<Map<String, String>> retrieveCustomData() {
        SharedPreferencesHelper sharedPreferences = SharedPreferencesHelper.getInstance(mContext);
        Map<String, String> customDataHash = (Map<String, String>) sharedPreferences.loadObj(SharedConstants.SHARED_PREFERENCES_CUSTOM_DATA, new TypeToken<Map<String, String>>() {
        }.getType());
        if (customDataHash != null) {
            return new DataSourceResponse<>(customDataHash);
        } else {
            return new DataSourceResponse<>(new HashMap<>());
        }
    }

    @Override
    public DataSourceResponse<Boolean> saveCustomData(Map<String, String> customDataHash) {
        SharedPreferencesHelper sharedPreferences = SharedPreferencesHelper.getInstance(mContext);
        sharedPreferences.saveObj(SharedConstants.SHARED_PREFERENCES_CUSTOM_DATA, customDataHash);
        return new DataSourceResponse<>(true);
    }

    public static NotificationLocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new NotificationLocalDataSource(context);
        }

        return INSTANCE;
    }

}
