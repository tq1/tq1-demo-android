package com.taqtile.tq1demo.data.source.filter;

import java.util.ArrayList;

import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.model.Filter;
import com.taqtile.tq1demo.data.model.FilterCategory;

/**
 * Created by taqtile on 5/30/16.
 */

public class FilterFakeDataSource implements FilterDataSource {

    private static FilterFakeDataSource INSTANCE;

    private final DataSourceResponse<ArrayList<FilterCategory>> mFakeResponse;

    //Prevent direct instantiation
    private FilterFakeDataSource() {
        mFakeResponse = createFakeData();
    }

    public static FilterFakeDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FilterFakeDataSource();
        }

        return INSTANCE;
    }

    private DataSourceResponse<ArrayList<FilterCategory>> createFakeData() {
        ArrayList<FilterCategory> filters = new ArrayList<>();

        ArrayList<Filter> categoryFilters = new ArrayList<>();
        Filter f1 = Filter.create("Filter 1", 2);
        Filter f2 = Filter.create("Filter 2", 4);
        Filter f3 = Filter.create("Filter 3", 5);
        categoryFilters.add(f1);
        categoryFilters.add(f2);
        categoryFilters.add(f3);

        FilterCategory fc = FilterCategory.create("Filter Category", false, categoryFilters);
        FilterCategory fc2 = FilterCategory.create("Filter Category 2", true, categoryFilters);
        filters.add(fc);
        filters.add(fc2);

        return new DataSourceResponse<>(filters);
    }

    @Override
    public DataSourceResponse<ArrayList<FilterCategory>> getFilters() {
        return mFakeResponse;
    }

    @Override
    public DataSourceResponse<Boolean> saveFilters(ArrayList<FilterCategory> filters) {
        return new DataSourceResponse<>(true);
    }
}
