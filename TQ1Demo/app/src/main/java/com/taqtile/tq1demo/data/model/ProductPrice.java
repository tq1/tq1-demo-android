package com.taqtile.tq1demo.data.model;

import com.google.auto.value.AutoValue;


/**
 * Created by indigo on 5/24/16.
 */

@AutoValue
public abstract class ProductPrice {

    public static ProductPrice create(double formerPrice, double currentPrice) {
        return new AutoValue_ProductPrice(formerPrice, currentPrice);
    }

    public abstract double getFormerPrice();

    public abstract double getCurrentPrice();

}

