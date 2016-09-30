package com.taqtile.tq1demo.triggers.controller;

import android.content.Context;

/**
 * Created by taqtile on 7/25/16.
 */
public interface TriggerContract {

    interface Service {

        void showNotificationUI(String message, Context context);

    }

    interface Controller {

        void triggerReceived(String fenceName, String event, Context context);

    }
}
