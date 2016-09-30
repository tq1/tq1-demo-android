package com.taqtile.tq1demo.triggers.receiver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.taqtile.tq1demo.R;
import com.taqtile.tq1demo.home.presentation.activity.HomeActivity;
import com.taqtile.tq1demo.triggers.controller.TriggerContract;
import com.taqtile.tq1demo.triggers.controller.TriggerController;
import com.taqtile.tq1demo.util.SharedConstants;

/**
 * Created by taqtile on 7/15/16.
 */
public class TriggerReceiver extends BroadcastReceiver implements TriggerContract.Service{
    private TriggerContract.Controller mController;

    //There's no specif reason for this value "2"
    private static final int NOTIFICATION_REQUEST_CODE = 2;

    public TriggerReceiver() {
        mController = new TriggerController(this);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String fenceName = intent.getStringExtra(SharedConstants.TQG_TAG_FENCE_NAME);
        String event = intent.getStringExtra(SharedConstants.TQG_TAG_EVENT_TYPE);

        mController.triggerReceived(fenceName, event, context);
    }

    @Override
    public void showNotificationUI(String message, Context context) {

        Intent notificationIntent = new Intent(context, HomeActivity.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, NOTIFICATION_REQUEST_CODE,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_account_circle)
                .setContentTitle(context.getString(R.string.tqg_local_push_title))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setContentText(message);

        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle(notificationBuilder);
        bigTextStyle.bigText(message);

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, notificationBuilder.build());
    }
}