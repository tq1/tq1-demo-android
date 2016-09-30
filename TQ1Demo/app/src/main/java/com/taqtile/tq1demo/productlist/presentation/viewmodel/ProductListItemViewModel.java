package com.taqtile.tq1demo.productlist.presentation.viewmodel;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 5/25/16.
 */

public class ProductListItemViewModel {

    private final String mId;

    private final String mName;

    private final String mFormerPrice;

    private final String mCurrentPrice;

    private final String mThumbnailImageUrl;

    public ProductListItemViewModel(String id, String name, String formerPrice,
                                    String currentPrice, String thumbnailImageUrl) {
        mId = checkNotNull(id, "product id cannot be null");
        mName = checkNotNull(name, "product name cannot be null");
        mCurrentPrice = checkNotNull(currentPrice, "product current price cannot be null");
        mFormerPrice = formerPrice;
        mThumbnailImageUrl = thumbnailImageUrl;
    }

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getFormerPrice() {
        return mFormerPrice;
    }

    public String getCurrentPrice() {
        return mCurrentPrice;
    }

    public String getThumbnailImageUrl() {
        return mThumbnailImageUrl;
    }
}
