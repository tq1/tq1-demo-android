package com.taqtile.tq1demo.data.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 5/30/16.
 */

@AutoValue
public abstract class FilterCategory implements Parcelable {

    public static FilterCategory create(String name, boolean isExclusive, ArrayList<Filter> filters) {
        return new AutoValue_FilterCategory(name, isExclusive, filters);
    }

    public abstract String getName();

    public abstract boolean isExclusive();

    public abstract ArrayList<Filter> getFilters();

}
