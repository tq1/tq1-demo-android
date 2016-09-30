package com.taqtile.tq1demo.pushnotification.controller;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ryanharter.auto.value.gson.AutoValueGsonTypeAdapterFactory;

import com.taqtile.tq1demo.data.model.NotificationContent;
import com.taqtile.tq1demo.pushnotification.NotificationStyles;
import com.taqtile.tq1demo.util.SharedConstants;
import taqtile.android.sdk.push.TQInbox;
import taqtile.android.sdk.push.TQInboxMessage;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 7/11/16.
 */

public class PushNotificationController implements PushNotificationContract.Controller {

    private PushNotificationContract.Service mService;

    public PushNotificationController(@NonNull PushNotificationContract.Service service) {
        mService = checkNotNull(service, "PushNotificationService cannot be null!");
    }


    @Override
    public void pushReceived(TQInboxMessage message) {
        NotificationStyles style = NotificationStyles.Default;

        switch (message.getType()) {
            case PUSH: {
                mService.showSimplePushNotification(message.getAlert(), style);
                break;
            }
            case HTML: {
                style = NotificationStyles.BigText;
                mService.showHtmlPushNotification(message.getAlert(), message.getContent(), style);
                break;
            }
            case LINK: {
                mService.showLinkPushNotification(message.getAlert(), message.getContent(), style);
                break;
            }
            case TAG: {
                if (message.getContent() != null) {

                    Gson gson = new Gson();
                    NotificationContent notificationContent = gson.fromJson(message.getContent(), NotificationContent.class);

                    if (notificationContent.getBigText() != null && notificationContent.getBigText().length() > 0) {
                        style = NotificationStyles.BigText;
                    }
                    if (notificationContent.getBigPicture() != null && notificationContent.getBigPicture().length() > 0) {
                        style = NotificationStyles.BigPicture;
                    }
                }
                mService.showTagPushNotification(message.getAlert(), style);
                break;
            }
            default: {
                break;
            }
        }
    }
}
