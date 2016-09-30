package com.taqtile.tq1demo.data.source.drawer;

import android.app.Activity;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.model.DrawerMenuItem;
import com.taqtile.tq1demo.home.presentation.activity.HomeActivity;
import com.taqtile.tq1demo.productlist.presentation.activity.ProductListActivity;
import com.taqtile.tq1demo.profilenotlogged.presentation.activity
        .ProfileNotLoggedActivity;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 7/7/16.
 */

public class DrawerMenuItemsLocalDataSource implements DrawerMenuItemsDataSource {

    private static DrawerMenuItemsLocalDataSource INSTANCE;

    private final Activity mActivityContext;

    private DrawerMenuItemsLocalDataSource(@NonNull Activity activityContext) {
        mActivityContext = checkNotNull(activityContext, "ActivityContext cannot be null!");
    }

    public static DrawerMenuItemsLocalDataSource getInstance(@NonNull Activity activityContext) {
        if (INSTANCE == null) {
            INSTANCE = new DrawerMenuItemsLocalDataSource(activityContext);
        }

        return INSTANCE;
    }

    private DataSourceResponse<ArrayList<DrawerMenuItem>> createMenuItemsData() {
        ArrayList<DrawerMenuItem> menuItems = new ArrayList<>();

        menuItems.add(DrawerMenuItem.create("Home", HomeActivity.class));
        menuItems.add(DrawerMenuItem.create("Products", ProductListActivity.class));
        menuItems.add(DrawerMenuItem.create("Profile", ProfileNotLoggedActivity.class));

        return new DataSourceResponse<>(menuItems);
    }

    @Override
    public DataSourceResponse<ArrayList<DrawerMenuItem>> getMenuItems() {
        return createMenuItemsData();
    }
}
