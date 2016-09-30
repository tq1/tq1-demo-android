package com.taqtile.tq1demo.drawer.presentation.viewmodel.mapper;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;


import com.taqtile.tq1demo.R;
import com.taqtile.tq1demo.data.model.DrawerMenuItem;
import com.taqtile.tq1demo.drawer.presentation.viewmodel.DrawerMenuItemViewModel;


import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 7/7/16.
 */

public class DrawerMenuItemToDrawerMenuItemViewModelMapper {

    private final Context mContext;

    public DrawerMenuItemToDrawerMenuItemViewModelMapper(@NonNull Context context) {
        mContext = checkNotNull(context, "Context cannot be null!");
    }

    public DrawerMenuItemViewModel mapDrawerMenuItemToDrawerMenuItemViewModel(
            DrawerMenuItem menuItem) {
        switch(menuItem.getTitle()) {
            case MenuItemsTitles.MENU_HOME_TITLE:
                return DrawerMenuItemViewModel.create(menuItem.getTitle(), 1,
                        R.drawable.ic_home, 0, isMenuItemSelected(menuItem));

            case MenuItemsTitles.MENU_NOTIFICATIONS_TITLE:
                return DrawerMenuItemViewModel.create(menuItem.getTitle(), 2,
                        android.R.drawable.ic_dialog_email, 1, isMenuItemSelected(menuItem));

            case MenuItemsTitles.MENU_SETTINGS_TITLE:
                return DrawerMenuItemViewModel.create(menuItem.getTitle(), 3,
                        R.drawable.ic_settings, 2, isMenuItemSelected(menuItem));

            case MenuItemsTitles.MENU_CUSTOM_DATA_TITLE:
                return DrawerMenuItemViewModel.create(menuItem.getTitle(), 4,
                        R.drawable.ic_custom_data, 3, isMenuItemSelected(menuItem));

            case MenuItemsTitles.MENU_GEO_FENCES_TITLE:
                return DrawerMenuItemViewModel.create(menuItem.getTitle(), 5,
                        R.drawable.ic_place, 4, isMenuItemSelected(menuItem));

            default:
                return DrawerMenuItemViewModel.create(menuItem.getTitle(), 99,
                        R.drawable.ic_check, 99, isMenuItemSelected(menuItem));
        }
    }

    public ArrayList<DrawerMenuItemViewModel> mapDrawerMenuItemListToDrawerMenuItemViewModelList(
            ArrayList<DrawerMenuItem> menuItems) {
        ArrayList<DrawerMenuItemViewModel> mMappedMenuItems = new ArrayList<>();

        for (DrawerMenuItem menuItem: menuItems) {
            mMappedMenuItems.add(mapDrawerMenuItemToDrawerMenuItemViewModel(menuItem));
        }

        return mMappedMenuItems;
    }

    private boolean isMenuItemSelected(DrawerMenuItem item) {
        if (item.getActivityClass() == mContext.getClass()) {
            return true;
        } else {
            return false;
        }
    }

}
