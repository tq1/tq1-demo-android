package com.taqtile.tq1demo.data.source.triggers;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.common.collect.Lists;
import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tqgeolocationsdk.TQGeoTracker;
import com.taqtile.tqgeolocationsdk.models.Trigger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 7/26/16.
 */
public class TriggerLocalDataSource implements TriggerDataSource {

    private static TriggerLocalDataSource INSTANCE;

    @NonNull
    private final Context mContext;

    // Prevent direct instantiation
    private TriggerLocalDataSource(@NonNull Context context) {
        mContext = checkNotNull(context, "context can't be null");
    }

    @Override
    public DataSourceResponse<ArrayList<Trigger>> getTriggers() {

//      Once the SDK returns an ascending order list based on the timestamp, to get a descending list we just reverse it
        List<Trigger> triggerList = TQGeoTracker.sharedInstance().listTriggers(mContext);
        Collections.reverse(triggerList);
        return new DataSourceResponse<>(triggerList);
    }


    public static TriggerLocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new TriggerLocalDataSource(context);
        }

        return INSTANCE;
    }

}
