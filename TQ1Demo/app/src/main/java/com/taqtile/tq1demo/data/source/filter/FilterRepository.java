package com.taqtile.tq1demo.data.source.filter;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.model.DataSourceResponseError;
import com.taqtile.tq1demo.data.model.FilterCategory;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 5/30/16.
 */

public class FilterRepository implements FilterDataSource {

    private static FilterRepository INSTANCE = null;

    private final FilterDataSource mFilterLocalDataSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    ArrayList<FilterCategory> mCachedFilters;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean mCacheIsDirty = false;

    /**
     * Prevents direct instantiation. This class is instantiated by the Injection class.
     */
    private FilterRepository(@NonNull FilterDataSource filterDataSource) {
        mFilterLocalDataSource = checkNotNull(filterDataSource);
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param localFilterDataSource the backend data source
     * @return the {@link FilterRepository} instance
     */
    public static FilterRepository getInstance(FilterDataSource localFilterDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new FilterRepository(localFilterDataSource);
        }

        return INSTANCE;
    }

    /**
     * Used to force {@link #getInstance(FilterDataSource)} to create a new instance
     * next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }

    /**
     * Gets the list of available filters
     *
     * @return A {@link DataSourceResponse} containing either an {@link ArrayList<FilterCategory>} or
     * a {@link DataSourceResponseError} object.
     */
    @Override
    public DataSourceResponse<ArrayList<FilterCategory>> getFilters() {

        DataSourceResponse<ArrayList<FilterCategory>> response =
                mFilterLocalDataSource.getFilters();

        if (response.isSuccess()) {
            return response;
        } else {
            //TODO: handle error
            return null;
        }

    }

    /**
     * Saves the list of available filters
     *
     * @param filters
     * @return A {@link DataSourceResponse} containing either an {@link ArrayList<FilterCategory>} or
     * a {@link DataSourceResponseError} object.
     */
    @Override
    public DataSourceResponse<Boolean> saveFilters(ArrayList<FilterCategory> filters) {

        DataSourceResponse<Boolean> response =
                mFilterLocalDataSource.saveFilters(filters);

        if (response.isSuccess()) {
            return response;
        } else {
            //TODO: handle error
            return null;
        }

    }
}
