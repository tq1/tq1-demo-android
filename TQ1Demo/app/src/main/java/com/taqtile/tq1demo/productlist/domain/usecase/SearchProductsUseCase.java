package com.taqtile.tq1demo.productlist.domain.usecase;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import br.com.taqtile.android.cleanbase.domain.BaseUseCase;
import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.model.Product;
import com.taqtile.tq1demo.data.source.product.ProductRepository;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 5/25/16.
 */

public class SearchProductsUseCase extends BaseUseCase<SearchProductsUseCase.RequestValues,
        SearchProductsUseCase.ResponseValue> {

    private final ProductRepository mProductRepository;

    public SearchProductsUseCase(@NonNull ProductRepository productRepository) {
        mProductRepository = checkNotNull(productRepository, "product repository cant be null");
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        DataSourceResponse<ArrayList<Product>> response = mProductRepository.
                getProductsByQueryAndPage(requestValues.getSearchQuery(), requestValues.getPage());

        if (response.isSuccess()) {
            getUseCaseCallback().onSuccess(
                    new ResponseValue(response.getResponse()));
        } else {
            //TODO: Send the error through callback
            getUseCaseCallback().onError();
        }
    }

    public static final class RequestValues implements BaseUseCase.RequestValues {

        private final String mSearchQuery;

        private final int mPage;

        public RequestValues(String searchQuery, int page) {
            mSearchQuery = searchQuery;
            mPage = page;
        }

        public String getSearchQuery() {
            return mSearchQuery;
        }

        public int getPage() {
            return mPage;
        }

    }

    public static final class ResponseValue implements BaseUseCase.ResponseValue {

        private final ArrayList<Product> mProducts;

        public ResponseValue(@NonNull ArrayList<Product> products) {
            mProducts = checkNotNull(products, "products cant be null");
        }

        public ArrayList<Product> getProducts() {
            return mProducts;
        }

    }
}


