package com.taqtile.tq1demo.data.source.product;

import android.support.annotation.Nullable;

import java.util.ArrayList;

import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.model.Product;

/**
 * Created by indigo on 5/18/16.
 */
public interface ProductDataSource {

    DataSourceResponse<ArrayList<Product>> getProductsByQueryAndPage(
            @Nullable String query, int pageNumber);

}
