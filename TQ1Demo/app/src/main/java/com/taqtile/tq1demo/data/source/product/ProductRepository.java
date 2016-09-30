package com.taqtile.tq1demo.data.source.product;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.model.DataSourceResponseError;
import com.taqtile.tq1demo.data.model.Product;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Concrete implementation to load products from data source into a cache.
 * It doesn't make sense to have a product local data source. We should never have
 * any local copies of products, because of their prices.
 */
public class ProductRepository implements ProductDataSource {

    private static ProductRepository INSTANCE = null;

    private final ProductDataSource mProductRemoteDataSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    ArrayList<Product> mCachedProducts;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean mCacheIsDirty = false;

    /**
     * Prevents direct instantiation. This class is instantiated by the Injection class.
     */
    private ProductRepository(@NonNull ProductDataSource productDataSource) {
        mProductRemoteDataSource = checkNotNull(productDataSource);
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param productRemoteDataSource the backend data source
     * @return the {@link ProductRepository} instance
     */
    public static ProductRepository getInstance(ProductDataSource productRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new ProductRepository(productRemoteDataSource);
        }

        return INSTANCE;
    }

    /**
     * Used to force {@link #getInstance(ProductDataSource)} to create a new instance
     * next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }


    /**
     * Gets products by a specific page number using a query defined by the user.
     *
     * @param query
     * @param pageNumber - starts on 0.
     * @return A {@link DataSourceResponse} containing either an {@link ArrayList<Product>} or
     * a {@link DataSourceResponseError} object.
     */
    @Override
    public DataSourceResponse<ArrayList<Product>> getProductsByQueryAndPage(@Nullable String query,
                                                                            int pageNumber) {

        DataSourceResponse<ArrayList<Product>> response =
                mProductRemoteDataSource.getProductsByQueryAndPage(query, pageNumber);

        if (response.isSuccess()) {
            return response;
        } else {
            // TODO: handle error
            return null;
        }

    }
}
