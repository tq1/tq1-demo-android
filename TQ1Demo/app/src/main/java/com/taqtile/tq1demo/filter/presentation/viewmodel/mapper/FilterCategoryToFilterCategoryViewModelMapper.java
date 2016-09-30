package com.taqtile.tq1demo.filter.presentation.viewmodel.mapper;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import com.taqtile.tq1demo.data.model.FilterCategory;
import com.taqtile.tq1demo.filter.presentation.viewmodel.FilterCategoryViewModel;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 5/31/16.
 */

public class FilterCategoryToFilterCategoryViewModelMapper {

    private FilterToFilterViewModelMapper mFilterMapper;

    public FilterCategoryToFilterCategoryViewModelMapper(@NonNull FilterToFilterViewModelMapper filterMapper) {
        mFilterMapper = checkNotNull(filterMapper, "Filter mapper cannot be null!");
    }

    public FilterCategoryViewModel mapFilterCategoryToFilterCategoryViewModel(FilterCategory filterCategory) {
        return new FilterCategoryViewModel(
                filterCategory.getName(),
                filterCategory.isExclusive(),
                false,
                mFilterMapper.mapFilterList(filterCategory.getFilters()));
    }

    public ArrayList<FilterCategoryViewModel> mapFilterList(ArrayList<FilterCategory> filters) {
        ArrayList<FilterCategoryViewModel> viewModelFilters = new ArrayList<>();

        for (FilterCategory f: filters) {
            FilterCategoryViewModel fViewModel = mapFilterCategoryToFilterCategoryViewModel(f);
            viewModelFilters.add(fViewModel);
        }

        return viewModelFilters;
    }
}
