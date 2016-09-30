package com.taqtile.tq1demo.data.model;

import com.google.auto.value.AutoValue;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by indigo on 5/24/16.
 */

@AutoValue
public abstract class ProductImage {

    public static ProductImage create(String thumbnailUrl, ArrayList<String> imageUrls) {
        return new AutoValue_ProductImage(thumbnailUrl, imageUrls);
    }

    public abstract String getThumbnailUrl();

    public abstract ArrayList<String> getImageUrls();

}