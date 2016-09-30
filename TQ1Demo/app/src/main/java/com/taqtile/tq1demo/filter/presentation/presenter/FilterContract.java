package com.taqtile.tq1demo.filter.presentation.presenter;

import java.util.ArrayList;

import br.com.taqtile.android.cleanbase.presentation.presenter.BasePresenter;
import br.com.taqtile.android.cleanbase.presentation.view.BaseView;
import com.taqtile.tq1demo.data.model.FilterCategory;
import com.taqtile.tq1demo.filter.presentation.viewmodel.FilterCategoryViewModel;
import com.taqtile.tq1demo.navigation.NavigationManager;

/**
 * Created by taqtile on 5/30/16.
 */

public interface FilterContract {

    interface View extends BaseView<Presenter, NavigationManager> {

        void showFilterList(ArrayList<FilterCategoryViewModel> filters);

        void showProductListUI(ArrayList<FilterCategory> activeFilters);

    }

    interface Presenter extends BasePresenter {

        void applyFilters(ArrayList<FilterCategoryViewModel> filters);

        void clearFilters();
        
    }

}
