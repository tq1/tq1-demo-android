package com.taqtile.tq1demo.notificationlist.presentation.viewmodel.mapper;

import com.taqtile.tq1demo.notificationlist.presentation.viewmodel.NotificationListItemViewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import taqtile.android.sdk.push.TQInbox;
import taqtile.android.sdk.push.TQInboxMessage;

/**
 * Created by taqtile on 7/26/16.
 */
public class NotificationToNotificationListItemViewModelMapper {

    public NotificationListItemViewModel mapNotificationToNotificationListItemViewModel(TQInboxMessage notification) {

        String formattedReceivedDate = getFormattedReceivedDate(notification.getTimestamp());
        boolean isUnread = isStatusUnread(notification.getStatus());

        //TODO: Decide what to show in each field
        NotificationListItemViewModel listNotificationViewModel = new NotificationListItemViewModel(notification.getId(), notification.getAlert(),
                formattedReceivedDate, notification.getAlert(), isUnread);

        return listNotificationViewModel;
    }

    /**
     * Converts the timestamp to the local date format String.
     *
     * @return the formatted received date.
     */
    public String getFormattedReceivedDate(String timestamp) {

        long time = Long.parseLong(timestamp);

        DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.getDefault());
        String date = formatter.format(new Date(time));

        return date;
    }

    /**
     * Converts the TQInboxMessageStatus to a boolean indicating if the message is still unread.
     *
     * @return true if the message is unread or false otherwise.
     */
    public boolean isStatusUnread(TQInbox.TQInboxMessageStatus status) {
        if (status == TQInbox.TQInboxMessageStatus.UNREAD) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<NotificationListItemViewModel> mapNotificationList(ArrayList<TQInboxMessage> notifications) {
        ArrayList<NotificationListItemViewModel> viewModelNotifications = new ArrayList<>();

        for(TQInboxMessage notification: notifications) {
            NotificationListItemViewModel notificationViewModel = mapNotificationToNotificationListItemViewModel(notification);
            viewModelNotifications.add(notificationViewModel);
        }

        return viewModelNotifications;
    }


}
