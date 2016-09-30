package com.taqtile.tq1demo.util;

import android.app.Activity;

import com.taqtile.tqgeolocationsdk.TQGeoTracker;

import com.taqtile.tq1demo.Constants;
import taqtile.android.sdk.TQGeoTriggerConfigureHandler;
import taqtile.android.sdk.TQGeoTriggerManager;

/**
 * Created by taqtile on 7/15/16.
 */
public class TQGManager implements TQGeoTriggerManager {

    private Activity mActivity;
    private TQGeoTriggerConfigureHandler mHandler;

    @Override
    public void configure(Activity activity, TQGeoTriggerConfigureHandler handler) {
        mActivity = activity;
        mHandler = handler;

        TQGeoTracker.sharedInstance().configure(activity, Constants.TQG_ID);

        mHandler.onFinish();
    }

    @Override
    public void start() {
        TQGeoTracker.sharedInstance().start(mActivity);
    }

    @Override
    public void stop() {
        TQGeoTracker.sharedInstance().stop(mActivity);
    }

    @Override
    public void pause() {
        stop();
    }

    @Override
    public void resume() {
        start();
    }

    @Override
    public String getDeviceId() {
        return TQGeoTracker.sharedInstance().getDeviceId(mActivity);
    }

}
