package com.taqtile.tq1demo.notificationlist.presentation.presenter;

import android.support.annotation.NonNull;

import com.taqtile.tq1demo.navigation.NavigationManager;
import com.taqtile.tq1demo.notificationlist.domain.usecase.ListNotificationsUseCase;
import com.taqtile.tq1demo.notificationlist.presentation.viewmodel.NotificationListItemViewModel;
import com.taqtile.tq1demo.notificationlist.presentation.viewmodel.mapper.NotificationToNotificationListItemViewModelMapper;

import java.util.ArrayList;

import br.com.taqtile.android.cleanbase.domain.BaseUseCase;
import br.com.taqtile.android.cleanbase.domain.BaseUseCaseHandler;
import taqtile.android.sdk.push.TQInboxMessage;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 5/25/16.
 */

public class NotificationListPresenter implements NotificationListContract.Presenter {

    private final NotificationListContract.View mView;


    private final ListNotificationsUseCase mListNotificationsUseCase;

    private final BaseUseCaseHandler mUseCaseHandler;

    private final NotificationToNotificationListItemViewModelMapper mMapper;

    public NotificationListPresenter(@NonNull BaseUseCaseHandler useCaseHandler,
                                     @NonNull NotificationListContract.View notificationListView,
                                     @NonNull ListNotificationsUseCase listNotificationsUseCase,
                                     @NonNull NotificationToNotificationListItemViewModelMapper mapper,
                                     @NonNull NavigationManager navigationManager) {
        mUseCaseHandler = checkNotNull(useCaseHandler, "Use case handler cannot be null");
        mListNotificationsUseCase = checkNotNull(listNotificationsUseCase, "List Notifications use case cannot be null");
        mView = checkNotNull(notificationListView, "Notification list view cannot be null");
        mMapper = checkNotNull(mapper, "Mapper cannot be null");

        mView.setPresenter(this);
        mView.setNavigationManager(navigationManager);
    }

    @Override
    public void start() {
    }

    @Override
    public void resume() {
        requestNotifications();
    }

    @Override
    public void selectNotificationDetails(String pushId) {
        if (mView.isActive()) {
            mView.showNotificationDetailsUI(pushId);
        }
    }

    @Override
    public void requestNotifications() {
        if (mView.isActive()) {
            mView.showLoading();
        }

        mUseCaseHandler.execute(mListNotificationsUseCase,
                new ListNotificationsUseCase.RequestValues(),
                new BaseUseCase.UseCaseCallback<ListNotificationsUseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(ListNotificationsUseCase.ResponseValue response) {
                        ArrayList<TQInboxMessage> notificationsList = response.getNotifications();

                        final ArrayList<NotificationListItemViewModel> viewModelNotifications =
                                mMapper.mapNotificationList(notificationsList);

                        if (mView.isActive()) {
                            mView.showNotificationList(viewModelNotifications);
                            mView.hideLoading();
                        }
                    }

                    @Override
                    public void onError() {
                        //TODO: Check how to handle this error
                        if (mView.isActive()) {
                            mView.showNotificationListError(null);
                            mView.hideLoading();
                        }
                    }
                });
    }
}
