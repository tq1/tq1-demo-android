package com.taqtile.tq1demo.data.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This class is responsible for wrapping responses from any data source. Since
 * we don't have a callback for listening for data source responses, we need a way
 * to tell repositories that a query wasn't successful.
 */
public class DataSourceResponse<T> {

    @NonNull
    private final Object mData;

    public DataSourceResponse(@NonNull Object data) {
        mData = checkNotNull(data, "data object can't be null");
    }

    /**
     *
     * @return boolean indicating whether the data source query has succeeded or not.
     */
    public boolean isSuccess() {
        return mData.getClass() != DataSourceResponseError.class;
    }

    @Nullable
    public T getResponse() {
        if (getError() == null) {
            return (T) mData;
        } else {
            return null;
        }
    }

    @Nullable
    public DataSourceResponseError getError() {
        if (mData.getClass() == DataSourceResponseError.class) {
            return (DataSourceResponseError) mData;
        } else {
            return null;
        }
    }
}
