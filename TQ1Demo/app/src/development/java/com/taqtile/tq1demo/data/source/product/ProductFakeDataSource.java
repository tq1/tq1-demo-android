package com.taqtile.tq1demo.data.source.product;

import android.support.annotation.Nullable;

import java.util.ArrayList;

import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.model.Product;
import com.taqtile.tq1demo.data.model.ProductImage;
import com.taqtile.tq1demo.data.model.ProductPrice;

/**
 * Created by indigo on 5/20/16.
 */
public class ProductFakeDataSource implements ProductDataSource {

    private static ProductFakeDataSource INSTANCE;

    private static final DataSourceResponse<ArrayList<Product>> PRODUCTS_FAKE_RESPONSE;

    static  {
        PRODUCTS_FAKE_RESPONSE = new DataSourceResponse<>(new ArrayList<Product>());

        addProduct("1", "Produto 1", 10.90, 24.90, 1, "Descrição do produto 1");
        addProduct("2", "Produto 2", 14.210, 27.30, 2, "Descrição do produto 2");
        addProduct("3", "Produto 3", 15.11290, 30.0, 3, "Descrição do produto 3");
        addProduct("4", "Produto 4", 1123.90, 122.10, 4, "Descrição do produto 4");
        addProduct("5", "Produto 5", 11.90, 33.30, 5, "Descrição do produto 5");
    }

    private static void addProduct(String id, String name, double formerPrice, double currentPrice,
                                   int rating, String description) {

        ProductPrice productPrice = ProductPrice.create(formerPrice, currentPrice);

        ArrayList<String> productImageUrls = new ArrayList<>();
        productImageUrls.add("https://placeholdit.imgix.net/~text?txtsize=66&txt=700%C3%97300&w=700&h=300");
        productImageUrls.add("https://placeholdit.imgix.net/~text?txtsize=66&txt=700%C3%97300&w=700&h=300");
        productImageUrls.add("https://placeholdit.imgix.net/~text?txtsize=66&txt=700%C3%97300&w=700&h=300");
        ProductImage productImage = ProductImage.create("https://placeholdit.imgix.net/~text?txtsize=66&txt=700%C3%97300&w=700&h=300",
                productImageUrls);

        Product product = Product.create(id, name, productPrice, rating, description, productImage);

        if (PRODUCTS_FAKE_RESPONSE.getResponse() != null) {
            PRODUCTS_FAKE_RESPONSE.getResponse().add(product);
        }

    }

    //Prevent direct instantiation
    private ProductFakeDataSource() {}

    public static ProductFakeDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProductFakeDataSource();
        }

        return INSTANCE;
    }

    @Override
    public DataSourceResponse<ArrayList<Product>> getProductsByQueryAndPage(@Nullable String query,
                                                                            int pageNumber) {
        return PRODUCTS_FAKE_RESPONSE;
    }
}
