package com.taqtile.tq1demo.pushnotification.controller;

import br.com.taqtile.android.cleanbase.push.notification.BaseGcmListenerContract;
import com.taqtile.tq1demo.pushnotification.NotificationStyles;
import taqtile.android.sdk.push.TQInboxMessage;

/**
 * Created by taqtile on 7/11/16.
 */

public interface PushNotificationContract extends BaseGcmListenerContract {

    interface Service {

        void showSimplePushNotification(String text, NotificationStyles style);
        void showHtmlPushNotification(String message, String htmlText, NotificationStyles style);
        void showLinkPushNotification(String message, String link, NotificationStyles style);
        void showTagPushNotification(String tag, NotificationStyles style);

    }

    interface Controller {

        void pushReceived(TQInboxMessage message);

    }
}
