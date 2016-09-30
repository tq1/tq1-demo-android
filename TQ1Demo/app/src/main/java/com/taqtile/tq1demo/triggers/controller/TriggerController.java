package com.taqtile.tq1demo.triggers.controller;


import android.content.Context;
import android.support.annotation.NonNull;

import com.taqtile.tq1demo.util.SharedConstants;

import static com.google.common.base.Preconditions.checkNotNull;
/**
 * Created by taqtile on 7/25/16.
 */
public class TriggerController implements TriggerContract.Controller {

    private TriggerContract.Service mService;

    public TriggerController(@NonNull TriggerContract.Service service) {
        mService = checkNotNull(service, "TriggerService cannot be null!");
    }

    @Override
    public void triggerReceived(String fenceName, String event, Context context) {
        mService.showNotificationUI("LOCAL: " + fenceName + " - " + event, context);
    }
}