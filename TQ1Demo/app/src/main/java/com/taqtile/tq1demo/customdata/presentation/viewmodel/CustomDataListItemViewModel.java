package com.taqtile.tq1demo.customdata.presentation.viewmodel;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 7/29/16.
 */
public class CustomDataListItemViewModel {

    private final String mKey;

    private final String mValue;

    public CustomDataListItemViewModel(String key, String value) {
        mKey = checkNotNull(key, "key cannot be null");
        mValue = checkNotNull(value, "value cannot be null");
    }

    public String getKey() {
        return mKey;
    }

    public String getValue() {
        return mValue;
    }
}
