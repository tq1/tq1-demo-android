package com.taqtile.tq1demo.productlist.presentation.presenter;

import java.util.ArrayList;

import br.com.taqtile.android.cleanbase.presentation.presenter.BasePresenter;
import br.com.taqtile.android.cleanbase.presentation.view.BaseView;
import com.taqtile.tq1demo.data.model.FilterCategory;
import com.taqtile.tq1demo.navigation.NavigationManager;
import com.taqtile.tq1demo.productlist.presentation.viewmodel.ProductListItemViewModel;

/**
 * Created by taqtile on 5/25/16.
 */

public interface ProductListContract {

    interface View extends BaseView<Presenter, NavigationManager> {

        void showProductList(ArrayList<ProductListItemViewModel> products);

        void showCartUI();

        void showSearchUI();

        void showFilterUI(ArrayList<FilterCategory> activeFilters);

        void showSortUI();

        void showProductDetailsUI(ProductListItemViewModel product);

        void showPlaceholder();

        void showProductListError(String error);

        void showNextPage(ArrayList<ProductListItemViewModel> products);

    }

    interface Presenter extends BasePresenter {

        void selectProductDetails(ProductListItemViewModel product);

        void selectCart();

        void searchProducts(String searchQuery);

        void selectSearch();

        void selectFilter();

        void selectSort();

        void filter(ArrayList<FilterCategory> selectedFilters);

        void sort();

        void reachedEndOfList();

    }

}
