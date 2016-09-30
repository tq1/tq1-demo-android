package com.taqtile.tq1demo.data.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 5/30/16.
 */


@AutoValue
public abstract class Filter implements Parcelable {

    public static Filter create(String name, int productsCount) {
        return new AutoValue_Filter(name, productsCount);
    }

    public abstract String getName();

    public abstract int getProductsCount();
}
