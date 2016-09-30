package com.taqtile.tq1demo.productlist.presentation.viewmodel.mapper;

import java.util.ArrayList;

import com.taqtile.tq1demo.data.model.Product;
import com.taqtile.tq1demo.productlist.presentation.viewmodel.ProductListItemViewModel;
import br.com.taqtile.android.utils.presentation.CurrencyUtils;

/**
 * Created by taqtile on 5/25/16.
 */

public class ProductToProductListItemViewModelMapper {

    public ProductListItemViewModel mapProductToProductListItemViewModel(Product product) {

        String formattedFormerPrice = getFormattedFormerPrice(product.getPrice().getFormerPrice(),
                product.getPrice().getCurrentPrice());
        String formattedCurrentPrice = getFormattedCurrentPrice(product.getPrice()
                .getCurrentPrice());

        ProductListItemViewModel listProductViewModel = new ProductListItemViewModel(product.getId(),
                product.getName(), formattedFormerPrice, formattedCurrentPrice,
                product.getImages().getThumbnailUrl());

        return listProductViewModel;
    }

    /**
     * Adds a currency symbol (chosen according to current device locale)
     * to the former price and truncate its value to 2 decimal cases. Also performs some
     * validations on the former price before returning the formatted value.
     *
     * @return the formatted former price if conditions are met, otherwise returns null.
     */
    public String getFormattedFormerPrice(double formerPrice, double currentPrice) {
        String formattedFormerPrice;

        if (formerPrice <= currentPrice || formerPrice <= 0) {
            formattedFormerPrice = null;
        } else {
            formattedFormerPrice = CurrencyUtils.format(formerPrice);
        }

        return formattedFormerPrice;
    }

    /**
     * Adds a currency symbol (chosen according to current device locale)
     * to the current price and truncate its value to 2 decimal cases.
     *
     * @return the formatted current price.
     */
    private String getFormattedCurrentPrice(double currentPrice) {
        String formattedCurrentPrice;

        if (currentPrice >= 0) {
            formattedCurrentPrice = CurrencyUtils.format(currentPrice);
        } else {
            formattedCurrentPrice = null;
        }

        return formattedCurrentPrice;
    }

    public ArrayList<ProductListItemViewModel> mapProductList(ArrayList<Product> products) {
        ArrayList<ProductListItemViewModel> viewModelProducts = new ArrayList<>();

        for(Product p: products) {
            ProductListItemViewModel pViewModel = mapProductToProductListItemViewModel(p);
            viewModelProducts.add(pViewModel);
        }

        return viewModelProducts;
    }
}
