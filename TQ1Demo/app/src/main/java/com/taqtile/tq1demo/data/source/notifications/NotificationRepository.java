package com.taqtile.tq1demo.data.source.notifications;

import android.support.annotation.NonNull;

import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.model.DataSourceResponseError;

import java.util.ArrayList;
import java.util.Map;

import taqtile.android.sdk.push.TQInbox;
import taqtile.android.sdk.push.TQInboxMessage;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 7/26/16.
 */
public class NotificationRepository implements NotificationDataSource {

    private static NotificationRepository INSTANCE = null;

    private final NotificationDataSource mNotificationDataSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    ArrayList<TQInboxMessage> mCachedProducts;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean mCacheIsDirty = false;

    /**
     * Prevents direct instantiation. This class is instantiated by the Injection class.
     */
    private NotificationRepository(@NonNull NotificationDataSource notificationDataSource) {
        mNotificationDataSource = checkNotNull(notificationDataSource);
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param notificationDataSource the backend data source
     * @return the {@link NotificationRepository} instance
     */
    public static NotificationRepository getInstance(NotificationDataSource notificationDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new NotificationRepository(notificationDataSource);
        }

        return INSTANCE;
    }

    /**
     * Used to force {@link #getInstance(NotificationDataSource)} to create a new instance
     * next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }


    /**
     * Gets the notifications list
     *
     * @return A {@link DataSourceResponse} containing either an {@link ArrayList<TQInboxMessage>} or
     * a {@link DataSourceResponseError} object.
     */
    @Override
    public DataSourceResponse<ArrayList<TQInboxMessage>> getNotifications() {
        DataSourceResponse<ArrayList<TQInboxMessage>> response =
                mNotificationDataSource.getNotifications();

        if (response.isSuccess()) {
            return response;
        } else {
            // TODO: handle error
            return null;
        }
    }

    @Override
    public DataSourceResponse<Integer> getUnreadNotificationsCount() {
        DataSourceResponse<Integer> response =
                mNotificationDataSource.getUnreadNotificationsCount();

        if (response.isSuccess()) {
            return response;
        } else {
            // TODO: handle error
            return null;
        }
    }

    @Override
    public DataSourceResponse<TQInboxMessage> getNotification(String pushId) {
        DataSourceResponse<TQInboxMessage> response =
                mNotificationDataSource.getNotification(pushId);

        if (response.isSuccess()) {
            return response;
        } else {
            // TODO: handle error
            return null;
        }
    }

    @Override
    public DataSourceResponse<Boolean> changeNotificationStatus(String pushId, TQInbox.TQInboxMessageStatus currentStatus) {
        DataSourceResponse<Boolean> response =
                mNotificationDataSource.changeNotificationStatus(pushId, currentStatus);

        if (response.isSuccess()) {
            return response;
        } else {
            // TODO: handle error
            return null;
        }
    }

    @Override
    public DataSourceResponse<Boolean> sendCustomStatus(String pushId, String status) {
        DataSourceResponse<Boolean> response =
                mNotificationDataSource.sendCustomStatus(pushId, status);

        if (response.isSuccess()) {
            return response;
        } else {
            // TODO: handle error
            return null;
        }
    }

    @Override
    public DataSourceResponse<Boolean> sendCustomData(Map<String, String> customDataHash) {
        DataSourceResponse<Boolean> response =
                mNotificationDataSource.sendCustomData(customDataHash);

        if (response.isSuccess()) {
            return response;
        } else {
            // TODO: handle error
            return null;
        }
    }

    @Override
    public DataSourceResponse<Map<String, String>> retrieveCustomData() {
        DataSourceResponse<Map<String, String>> response =
                mNotificationDataSource.retrieveCustomData();

        if (response.isSuccess()) {
            return response;
        } else {
            // TODO: handle error
            return null;
        }
    }

    @Override
    public DataSourceResponse<Boolean> saveCustomData(Map<String, String> customDataHash) {
        DataSourceResponse<Boolean> response =
                mNotificationDataSource.saveCustomData(customDataHash);

        if (response.isSuccess()) {
            return response;
        } else {
            // TODO: handle error
            return null;
        }
    }

}
