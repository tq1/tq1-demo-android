package com.taqtile.tq1demo.drawer.presentation.presenter;


import java.util.ArrayList;

import br.com.taqtile.android.cleanbase.presentation.activity.DrawerManager;
import br.com.taqtile.android.cleanbase.presentation.presenter.BasePresenter;
import br.com.taqtile.android.cleanbase.presentation.view.BaseView;
import com.taqtile.tq1demo.drawer.presentation.viewmodel.DrawerMenuItemViewModel;
import com.taqtile.tq1demo.navigation.NavigationManager;

/**
 * Created by taqtile on 5/25/16.
 */

public interface DrawerContract {

    interface View extends BaseView<Presenter, NavigationManager> {

        void displayMenuItems(ArrayList<DrawerMenuItemViewModel> items);

        void showUI(Class<?> activityClass);

        void setDrawerManager(DrawerManager manager);

        void updateUnreadNotificationsCounter(int count);
    }

    interface Presenter extends BasePresenter {

        void menuItemSelected(int index);

        void drawerClosed();
    }

}
