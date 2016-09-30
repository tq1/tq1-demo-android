package com.taqtile.tq1demo.notificationlist.presentation.presenter;

import com.taqtile.tq1demo.navigation.NavigationManager;
import com.taqtile.tq1demo.notificationlist.presentation.viewmodel.NotificationListItemViewModel;

import java.util.ArrayList;

import br.com.taqtile.android.cleanbase.presentation.presenter.BasePresenter;
import br.com.taqtile.android.cleanbase.presentation.view.BaseView;

/**
 * Created by taqtile on 5/25/16.
 */

public interface NotificationListContract {

    interface View extends BaseView<Presenter, NavigationManager> {

        void showNotificationList(ArrayList<NotificationListItemViewModel> notifications);

        void showNotificationDetailsUI(String pushId);

        void showNotificationListError(String error);

        void showPlaceholder();
    }

    interface Presenter extends BasePresenter {

        void selectNotificationDetails(String pushId);
        void requestNotifications();

    }

}
