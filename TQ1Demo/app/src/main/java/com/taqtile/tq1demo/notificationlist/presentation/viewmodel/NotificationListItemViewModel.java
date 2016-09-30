package com.taqtile.tq1demo.notificationlist.presentation.viewmodel;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 7/26/16.
 */
public class NotificationListItemViewModel {

    private final String mPushId;

    private final String mTitle;

    private final String mReceivedDate;

    private final String mMessage;

    private boolean isPendingRead;

    public NotificationListItemViewModel(String pushId, String title, String receivedDate, String message,
                                         Boolean isPendingRead) {
        mPushId = checkNotNull(pushId, "push id cannot be null");
        mTitle = checkNotNull(title, "notification title cannot be null");
        mReceivedDate = checkNotNull(receivedDate, "received date cannot be null");
        mMessage = checkNotNull(message, "notification message cannot be null");
        this.isPendingRead = isPendingRead;
    }


    public String getPushId() {
        return mPushId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getReceivedDate() {
        return mReceivedDate;
    }

    public String getMessage() {
        return mMessage;
    }

    public boolean isPendingRead() {
        return isPendingRead;
    }
}
