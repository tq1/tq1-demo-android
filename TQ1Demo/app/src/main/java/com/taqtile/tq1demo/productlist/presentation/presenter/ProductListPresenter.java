package com.taqtile.tq1demo.productlist.presentation.presenter;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import br.com.taqtile.android.cleanbase.domain.BaseUseCase;
import br.com.taqtile.android.cleanbase.domain.BaseUseCaseHandler;
import com.taqtile.tq1demo.data.model.FilterCategory;
import com.taqtile.tq1demo.data.model.Product;
import com.taqtile.tq1demo.navigation.NavigationManager;
import com.taqtile.tq1demo.productlist.domain.usecase.SearchProductsUseCase;
import com.taqtile.tq1demo.productlist.presentation.viewmodel.ProductListItemViewModel;
import com.taqtile.tq1demo.productlist.presentation.viewmodel.mapper.ProductToProductListItemViewModelMapper;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 5/25/16.
 */

public class ProductListPresenter implements ProductListContract.Presenter {

    private final ProductListContract.View mView;

    private final SearchProductsUseCase mSearchProductsUseCase;

    private final BaseUseCaseHandler mUseCaseHandler;

    private final ProductToProductListItemViewModelMapper mMapper;

    private int mNextPage;

    private String mCurrentQuery;

    private ArrayList<FilterCategory> mActiveFilters = new ArrayList<>();

    public ProductListPresenter(@NonNull BaseUseCaseHandler useCaseHandler,
                                @NonNull ProductListContract.View productListView,
                                @NonNull SearchProductsUseCase searchProductsUseCase,
                                @NonNull ProductToProductListItemViewModelMapper mapper,
                                @NonNull NavigationManager navigationManager) {
        mUseCaseHandler = checkNotNull(useCaseHandler, "Use case handler cannot be null");
        mSearchProductsUseCase = checkNotNull(searchProductsUseCase, "Search Products use case cannot be null");
        mView = checkNotNull(productListView, "Product list view cannot be null");
        mMapper = checkNotNull(mapper, "Mapper cannot be null");
        mNextPage = 0;

        mView.setPresenter(this);
        mView.setNavigationManager(navigationManager);
    }

    public void selectProductDetails(ProductListItemViewModel product) {
        if (mView.isActive()) {
            mView.showProductDetailsUI(product);
        }
    }

    @Override
    public void selectCart() {
        if (mView.isActive()) {
            mView.showCartUI();
        }
    }

    @Override
    public void searchProducts(String searchQuery) {
        if (mView.isActive()) {
            mView.showLoading();
        }

        mCurrentQuery = searchQuery;

        mUseCaseHandler.execute(mSearchProductsUseCase,
                new SearchProductsUseCase.RequestValues(searchQuery, mNextPage),
                new BaseUseCase.UseCaseCallback<SearchProductsUseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(SearchProductsUseCase.ResponseValue response) {
                        ArrayList<Product> productList = response.getProducts();

                        final ArrayList<ProductListItemViewModel> viewModelProducts =
                                mMapper.mapProductList(productList);

                        if (mView.isActive()) {
                            if (mNextPage == 0) {
                                mView.showProductList(viewModelProducts);
                            } else {
                                mView.showNextPage(viewModelProducts);
                            }
                            mView.hideLoading();
                        }
                        mNextPage++;
                    }

                    @Override
                    public void onError() {
                        //TODO: Check how to handle this error
                        if (mView.isActive()) {
                            mView.showProductListError(null);
                            mView.hideLoading();
                        }
                    }
                });
    }

    @Override
    public void selectSearch() {
        if (mView.isActive()) {
            mView.showSearchUI();
        }
    }

    @Override
    public void selectFilter() {
        if (mView.isActive()) {
            mView.showFilterUI(mActiveFilters);
        }
    }

    @Override
    public void selectSort() {
        if (mView.isActive()) {
            mView.showSortUI();
        }
    }

    @Override
    public void filter(ArrayList<FilterCategory> selectedFilters) {
        //TODO: Implement when filter use case is done
    }

    @Override
    public void sort() {
        //TODO: Implement when sort use case is done
    }

    @Override
    public void reachedEndOfList() {
        searchProducts(mCurrentQuery);
    }

    @Override
    public void start() {
        searchProducts("");
    }

    @Override
    public void resume() {
        searchProducts("");
    }

}
