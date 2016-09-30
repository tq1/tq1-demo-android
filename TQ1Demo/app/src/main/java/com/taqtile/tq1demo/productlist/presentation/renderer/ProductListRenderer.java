package com.taqtile.tq1demo.productlist.presentation.renderer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pedrogomez.renderers.Renderer;
import com.squareup.picasso.Picasso;

import com.taqtile.tq1demo.R;
import com.taqtile.tq1demo.productlist.presentation.viewmodel.ProductListItemViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by indigo on 5/25/16.
 */
public class ProductListRenderer extends Renderer<ProductListItemViewModel> {

    @BindView(R.id.product_list_item_image_view)
    ImageView mProductListItemImageView;

    @BindView(R.id.product_list_item_name_text_view)
    TextView mProductListItemNameTextView;

    @BindView(R.id.product_list_item_price_text_view)
    TextView mProductListItemPriceTextView;

    @Override
    protected void setUpView(View rootView) {
        /*
         * Empty implementation substituted with the usage of ButterKnife library by Jake Wharton.
         */
    }

    @Override
    protected void hookListeners(View rootView) {
        /*
         * Empty implementation substituted with the usage of ButterKnife library by Jake Wharton.
         */
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        View inflatedView = inflater.inflate(R.layout.renderer_productlist, parent, false);
        ButterKnife.bind(this, inflatedView);
        return inflatedView;
    }

    @Override
    public void render() {
        ProductListItemViewModel model = getContent();
        renderImage(model);
        renderTitle(model);
    }

    private void renderImage(ProductListItemViewModel model) {
        Picasso.with(getContext().getApplicationContext())
                .load(model.getThumbnailImageUrl())
                .fit()
                .centerCrop()
                .into(mProductListItemImageView);
    }

    private void renderTitle(ProductListItemViewModel model) {
        mProductListItemNameTextView.setText(model.getName());
        mProductListItemPriceTextView.setText(model.getCurrentPrice());
    }
}
