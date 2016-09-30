package com.taqtile.tq1demo.data.source.filter;

import java.util.ArrayList;

import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.model.FilterCategory;

/**
 * Created by taqtile on 5/30/16.
 */

public interface FilterDataSource {

    DataSourceResponse<ArrayList<FilterCategory>> getFilters();

    DataSourceResponse<Boolean> saveFilters(ArrayList<FilterCategory> filters);

}
