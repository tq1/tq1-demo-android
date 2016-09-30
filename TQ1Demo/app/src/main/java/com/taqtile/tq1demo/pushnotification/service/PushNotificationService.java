package com.taqtile.tq1demo.pushnotification.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.taqtile.tq1demo.R;
import com.taqtile.tq1demo.home.presentation.activity.HomeActivity;
import com.taqtile.tq1demo.pushnotification.NotificationStyles;
import com.taqtile.tq1demo.pushnotification.controller.PushNotificationContract;
import com.taqtile.tq1demo.pushnotification.controller.PushNotificationController;
import com.taqtile.tq1demo.util.SharedConstants;

import br.com.taqtile.android.cleanbase.push.notification.BaseGcmListenerService;
import taqtile.android.sdk.push.TQInbox;
import taqtile.android.sdk.push.TQInboxMessage;

/**
 * Created by taqtile on 7/11/16.
 */

public class PushNotificationService extends BaseGcmListenerService implements PushNotificationContract.Service {

    private PushNotificationContract.Controller mController;

    //There's no specif reason for this value "2"
    private static final int NOTIFICATION_REQUEST_CODE = 2;

    public PushNotificationService() {
        mController = new PushNotificationController(this);
    }

    public void processPushBundle(Bundle bundle) {
        // This method is called when GCM receives a new push

        // Retrieving the push information:
        String pushId = bundle.getString(SharedConstants.TQ1_PID_TAG);
        String alert = bundle.getString(SharedConstants.TQ1_TEXT_TAG);
        long timestamp = System.currentTimeMillis();

        // Requesting the push information by it's push id (pid)
        TQInbox inbox = new TQInbox(this);
        inbox.addAlert(pushId, alert, String.valueOf(timestamp), "");
        inbox.retrieveCustomContent(pushId, new TQInbox.TQInboxCustomContentCallback() {
            @Override
            public void onSuccess(TQInboxMessage message) {
                // The TQInbox retrieved the message content successfully

                // Call the controller method to handle the message
                mController.pushReceived(message);
            }

            @Override
            public void onError() {
                // The TQInbox failed retrieving the message content
            }
        });

    }

    @Override
    public void showSimplePushNotification(String text, NotificationStyles style) {
        Intent notificationIntent = new Intent(getApplicationContext(), HomeActivity.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        showNotification(notificationIntent, getString(R.string.notification_simple_push_title), text, style);

    }

    @Override
    public void showHtmlPushNotification(String message, String htmlText, NotificationStyles style) {
        // TODO: Handle message and html text
        showSimplePushNotification(htmlText, style);
    }

    @Override
    public void showLinkPushNotification(String message, String link, NotificationStyles style) {
        Intent notificationIntent = new Intent(getApplicationContext(), HomeActivity.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        notificationIntent.putExtra(SharedConstants.HOME_EXTERNAL_LINK_TAG, link);

        showNotification(notificationIntent, getString(R.string.notification_link_push_title), message, style);

    }

    @Override
    public void showTagPushNotification(String tag, NotificationStyles style) {
        showSimplePushNotification(tag, style);
    }

    private void showNotification(Intent intent, String notificationTitle, String notificationMessage, NotificationStyles style) {

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext());

        switch (style) {
            case BigText: {
                NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle(notificationBuilder);
                bigTextStyle.bigText(notificationMessage);
                break;
            }
            case BigPicture: {
                // TODO: Handle images
                NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle(notificationBuilder);
                bigPictureStyle.bigPicture(null);
                break;
            }
            default: {
                break;
            }
        }

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), NOTIFICATION_REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = notificationBuilder
                .setContentTitle(notificationTitle)
                .setContentText(notificationMessage)
                .setSmallIcon(R.drawable.ic_account_circle)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        NotificationManager mNotificationManager =
                (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, notification);
    }
}
