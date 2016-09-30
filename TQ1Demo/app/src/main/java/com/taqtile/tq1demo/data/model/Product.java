package com.taqtile.tq1demo.data.model;

import com.google.auto.value.AutoValue;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by indigo on 5/24/16.
 */
@AutoValue
public abstract class Product {

    public static Product create(String id, String name, ProductPrice price,
                                 int rating, String description, ProductImage productImage) {
        return new AutoValue_Product(id, name, price, rating, description, productImage);
    }

    public abstract String getId();

    public abstract String getName();

    public abstract ProductPrice getPrice();

    public abstract int getRating();

    public abstract String getDescription();

    public abstract ProductImage getImages();

}