package com.taqtile.tq1demo.filter.presentation.viewmodel.mapper;

import java.util.ArrayList;

import com.taqtile.tq1demo.data.model.Filter;
import com.taqtile.tq1demo.filter.presentation.viewmodel.FilterViewModel;

/**
 * Created by taqtile on 6/7/16.
 */
public class FilterToFilterViewModelMapper {

    public FilterViewModel mapFilterToFilterViewModel(Filter filter, boolean isChecked, boolean isLast) {
        return new FilterViewModel(
                isChecked,
                filter.getName(),
                isLast,
                filter.getProductsCount()
        );
    }

    public ArrayList<FilterViewModel> mapFilterList(ArrayList<Filter> filters) {
        ArrayList<FilterViewModel> viewModelFilters = new ArrayList<>();

        for (int i = 0; i < filters.size(); i++) {
            //TODO: Check how to handle checked filters
            boolean isLast = i == filters.size() - 1;
            FilterViewModel fVM = mapFilterToFilterViewModel(filters.get(i), false, isLast);
            viewModelFilters.add(fVM);
        }

        return viewModelFilters;
    }

}
