package com.taqtile.tq1demo.data.source.drawer;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.model.DrawerMenuItem;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 7/7/16.
 */

public class DrawerMenuItemsRepository implements DrawerMenuItemsDataSource {

    private static DrawerMenuItemsRepository INSTANCE = null;

    private final DrawerMenuItemsDataSource mDrawerMenuItemsLocalDataSource;

    ArrayList<DrawerMenuItem> mCachedMenuItems;

    boolean mCacheIsDirty = false;

    private DrawerMenuItemsRepository(@NonNull DrawerMenuItemsDataSource drawerMenuItemsDataSource) {
        mDrawerMenuItemsLocalDataSource = checkNotNull(drawerMenuItemsDataSource);
    }

    public static DrawerMenuItemsRepository getInstance(DrawerMenuItemsDataSource localMenuItemsDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new DrawerMenuItemsRepository(localMenuItemsDataSource);
        }

        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public DataSourceResponse<ArrayList<DrawerMenuItem>> getMenuItems() {
        DataSourceResponse<ArrayList<DrawerMenuItem>> response =
                mDrawerMenuItemsLocalDataSource.getMenuItems();

        if (response.isSuccess()) {
            return response;
        } else {
            //TODO: Handle error
            return null;
        }
    }
}
