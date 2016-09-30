package com.taqtile.tq1demo.base.presentation.activity;

import android.view.View;

import com.taqtile.tq1demo.DrawerInjection;
import com.taqtile.tq1demo.Injection;
import com.taqtile.tq1demo.R;
import com.taqtile.tq1demo.drawer.presentation.presenter.DrawerContract;
import com.taqtile.tq1demo.drawer.presentation.presenter.DrawerPresenter;
import com.taqtile.tq1demo.drawer.presentation.view.DrawerView;
import com.taqtile.tq1demo.navigation.NavigationHelper;

import br.com.taqtile.android.cleanbase.presentation.activity.DrawerActivity;
import br.com.taqtile.android.cleanbase.presentation.presenter.BasePresenter;

/**
 * Created by taqtile on 6/6/16.
 */

public abstract class TemplateDrawerActivity
        extends DrawerActivity {

    private DrawerContract.View mDrawerView;

    @Override
    public int getDrawerContainerId() {
        return R.id.activity_base_drawer_drawerContainer;
    }

    @Override
    public int getDrawerMenuIcon() {
        return R.drawable.ic_menu;
    }

    @Override
    public int getActivityLayoutId() {
        return R.layout.activity_base_drawer;
    }

    @Override
    public int getFragmentContainerId() {
        return R.id.activity_base_drawer_container;
    }

    @Override
    public int getToolbarId() {
        return R.id.activity_base_drawer_toolbar;
    }

    @Override
    public int getDrawerLayoutId() {
        return R.id.activity_base_drawer_drawerLayout;
    }

    @Override
    public int getStatusBarBackgroundColor() {
        return R.color.color_primary;
    }

    @Override
    public int getFABId() {
        return R.id.activity_base_drawer_fab;
    }

    @Override
    public <T extends View & DrawerStateListener> T getDrawerView() {
        if (mDrawerView == null) {
            mDrawerView = new DrawerView(this);
            if (!(mDrawerView instanceof DrawerStateListener)) {
                throw new RuntimeException("DrawerView must implement DrawerStateListener");
            }
        }
        return (T) mDrawerView;
    }

    @Override
    public BasePresenter getDrawerPresenter() {
        return new DrawerPresenter(
                Injection.provideUseCaseHandler(),
                mDrawerView,
                DrawerInjection.provideGetDrawerMenuItemsUseCase(this),
                DrawerInjection.provideGetUnreadNotificationsCountUseCase(this),
                DrawerInjection.provideDrawerMenuItemToDrawerMenuItemViewModelMapper(this),
                new NavigationHelper(this),
                this
        );
    }
}
