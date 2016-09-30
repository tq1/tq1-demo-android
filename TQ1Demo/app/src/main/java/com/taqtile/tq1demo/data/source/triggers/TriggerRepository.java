package com.taqtile.tq1demo.data.source.triggers;

import android.support.annotation.NonNull;

import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.model.DataSourceResponseError;
import com.taqtile.tqgeolocationsdk.models.Trigger;

import java.util.ArrayList;

import taqtile.android.sdk.push.TQInboxMessage;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 7/26/16.
 */
public class TriggerRepository implements TriggerDataSource {

    private static TriggerRepository INSTANCE = null;

    private final TriggerDataSource mTriggerDataSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    ArrayList<TQInboxMessage> mCachedProducts;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean mCacheIsDirty = false;

    /**
     * Prevents direct instantiation. This class is instantiated by the Injection class.
     */
    private TriggerRepository(@NonNull TriggerDataSource triggerDataSource) {
        mTriggerDataSource = checkNotNull(triggerDataSource);
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param triggerDataSource the backend data source
     * @return the {@link TriggerRepository} instance
     */
    public static TriggerRepository getInstance(TriggerDataSource triggerDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new TriggerRepository(triggerDataSource);
        }

        return INSTANCE;
    }

    /**
     * Used to force {@link #getInstance(TriggerDataSource)} to create a new instance
     * next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }


    /**
     * Gets the triggers list
     *
     * @return A {@link DataSourceResponse} containing either an {@link ArrayList<Trigger>} or
     * a {@link DataSourceResponseError} object.
     */
    @Override
    public DataSourceResponse<ArrayList<Trigger>> getTriggers() {
        DataSourceResponse<ArrayList<Trigger>> response =
                mTriggerDataSource.getTriggers();

        if (response.isSuccess()) {
            return response;
        } else {
            // TODO: handle error
            return null;
        }
    }

}
