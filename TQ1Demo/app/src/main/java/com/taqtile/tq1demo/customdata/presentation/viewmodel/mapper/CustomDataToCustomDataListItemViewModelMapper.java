package com.taqtile.tq1demo.customdata.presentation.viewmodel.mapper;

import com.taqtile.tq1demo.customdata.presentation.viewmodel.CustomDataListItemViewModel;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by taqtile on 8/1/16.
 */
public class CustomDataToCustomDataListItemViewModelMapper {

    public ArrayList<CustomDataListItemViewModel> mapCustomDataList(Map<String, String> customDataHash) {
        ArrayList<CustomDataListItemViewModel> viewModelCustomDataList = new ArrayList<>();

        Object[] keysArray = customDataHash.keySet().toArray();
        for (Object keyObject : keysArray) {
            String key = keyObject.toString();
            CustomDataListItemViewModel customDataListItemViewModel = new CustomDataListItemViewModel(key, customDataHash.get(key));
            viewModelCustomDataList.add(customDataListItemViewModel);
        }

        return viewModelCustomDataList;
    }
}