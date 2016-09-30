package com.taqtile.tq1demo.productlist.presentation.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import br.com.taqtile.android.cleanbase.presentation.presenter.BasePresenter;
import br.com.taqtile.android.cleanbase.presentation.view.LoadingHandler;
import com.taqtile.tq1demo.DrawerInjection;
import com.taqtile.tq1demo.base.presentation.activity.TemplateDrawerActivity;
import com.taqtile.tq1demo.drawer.presentation.presenter.DrawerPresenter;
import com.taqtile.tq1demo.drawer.presentation.view.DrawerView;
import com.taqtile.tq1demo.Injection;
import com.taqtile.tq1demo.navigation.NavigationHelper;
import com.taqtile.tq1demo.productlist.presentation.fragment.ProductListFragment;
import com.taqtile.tq1demo.productlist.presentation.presenter.ProductListPresenter;

/**
 * Created by taqtile on 5/25/16.
 */

public class ProductListActivity extends TemplateDrawerActivity implements LoadingHandler {

    private ProductListFragment mFragment;

    @Override
    public ProductListFragment getFragment() {
        ProductListFragment productListFragment = (ProductListFragment) getSupportFragmentManager()
                .findFragmentById(getFragmentContainerId());
        if (productListFragment == null) {
            productListFragment = ProductListFragment.newInstance();
        }

        mFragment = productListFragment;

        return mFragment;
    }

    @Override
    public BasePresenter getPresenter() {
        return new ProductListPresenter(
                Injection.provideUseCaseHandler(),
                mFragment,
                Injection.provideSearchProductsUseCase(getApplicationContext()),
                Injection.provideProductToProductListItemViewModelMapper(),
                new NavigationHelper(this));
    }

    public static void navigate(Context context) {
        Intent intent = new Intent(context, ProductListActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
