package com.taqtile.tq1demo.filter.presentation.presenter;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import br.com.taqtile.android.cleanbase.domain.BaseUseCase;
import br.com.taqtile.android.cleanbase.domain.BaseUseCaseHandler;
import com.taqtile.tq1demo.data.model.Filter;
import com.taqtile.tq1demo.data.model.FilterCategory;
import com.taqtile.tq1demo.filter.domain.usecase.GetFiltersUseCase;
import com.taqtile.tq1demo.filter.presentation.viewmodel.FilterCategoryViewModel;
import com.taqtile.tq1demo.filter.presentation.viewmodel.mapper.FilterCategoryToFilterCategoryViewModelMapper;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 5/31/16.
 */

public class FilterPresenter implements FilterContract.Presenter {

    private final FilterContract.View mView;

    private final GetFiltersUseCase mGetFiltersUseCase;

    private final BaseUseCaseHandler mUseCaseHandler;

    private final FilterCategoryToFilterCategoryViewModelMapper mMapper;

    private ArrayList<FilterCategory> mActiveFilters;

    public FilterPresenter(@NonNull BaseUseCaseHandler useCaseHandler,
                           @NonNull FilterContract.View filterView,
                           @NonNull GetFiltersUseCase getFiltersUseCase,
                           @NonNull FilterCategoryToFilterCategoryViewModelMapper mapper,
                           @NonNull ArrayList<FilterCategory> activeFilters) {
        mUseCaseHandler = checkNotNull(useCaseHandler, "Use case handler cannot be null!");
        mView = checkNotNull(filterView, "Filter view cannot be null!");
        mGetFiltersUseCase = checkNotNull(getFiltersUseCase, "GetFilters cannot be null!");
        mMapper = checkNotNull(mapper, "Mapper cannot be null!");
        mActiveFilters = checkNotNull(activeFilters, "Active filters cannot be null");

        mView.setPresenter(this);
    }

    @Override
    public void applyFilters(ArrayList<FilterCategoryViewModel> filters) {
        if (mView.isActive()) {
            //TODO: Send valid result
            ArrayList<FilterCategory> f = new ArrayList<>();
            f.add(FilterCategory.create("asdf", false, new ArrayList<Filter>()));
            mView.showProductListUI(f);
        }
    }

    @Override
    public void clearFilters() {
        if (mView.isActive()) {
            //TODO: Send result
            mView.showProductListUI(new ArrayList<FilterCategory>());
        }
    }

    private void getFilterList() {
        if (mView.isActive()) {
            mView.showLoading();
        }

        mUseCaseHandler.execute(mGetFiltersUseCase,
                new GetFiltersUseCase.RequestValues(),
                new BaseUseCase.UseCaseCallback<GetFiltersUseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(GetFiltersUseCase.ResponseValue response) {
                        ArrayList<FilterCategory> filters = response.getFilters();
                        ArrayList<FilterCategoryViewModel> filterList = mMapper.mapFilterList(filters);

                        if (mView.isActive()) {
                            mView.showFilterList(filterList);
                            mView.hideLoading();
                        }

                    }

                    @Override
                    public void onError() {
                        if (mView.isActive()) {
                            //TODO: Check how to handle this error
                            mView.hideLoading();
                        }
                    }
                });
    }

    @Override
    public void start() {
        getFilterList();
    }

    @Override
    public void resume() {

    }
}
