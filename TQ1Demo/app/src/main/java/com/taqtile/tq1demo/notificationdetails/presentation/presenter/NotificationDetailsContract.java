package com.taqtile.tq1demo.notificationdetails.presentation.presenter;

import android.content.Context;

import com.taqtile.tq1demo.navigation.NavigationManager;

import br.com.taqtile.android.cleanbase.presentation.presenter.BasePresenter;
import br.com.taqtile.android.cleanbase.presentation.view.BaseView;
import taqtile.android.sdk.push.TQInboxMessage;

/**
 * Created by taqtile on 7/27/16.
 */
public interface NotificationDetailsContract {
    interface View extends BaseView<Presenter, NavigationManager> {

        // Here we're passing the whole object as parameter, because in fact we need it.
        void showNotificationDetails(TQInboxMessage notification);

        void showMarkAsReadButtonText();

        void showMarkAsUnreadButtonText();

        void showSendCustomStatusDialog();

        void showContentView();

        void showPlaceholder();

    }

    interface Presenter extends BasePresenter {

        void openNotificationContent();

        void markNotificationAsReadOrUnread();

        void customStatusButtonClicked();

        void sendCustomStatus(String status);

        void requestNotificationDetails();

    }

}

