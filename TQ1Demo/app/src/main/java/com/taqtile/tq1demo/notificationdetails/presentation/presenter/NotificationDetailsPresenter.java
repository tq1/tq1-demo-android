package com.taqtile.tq1demo.notificationdetails.presentation.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.taqtile.tq1demo.navigation.NavigationManager;
import com.taqtile.tq1demo.notificationdetails.domain.usecase.ChangeNotificationReadStatusUseCase;
import com.taqtile.tq1demo.notificationdetails.domain.usecase.RequestNotificationDetailsUseCase;
import com.taqtile.tq1demo.notificationdetails.domain.usecase.SendCustomStatusUseCase;

import br.com.taqtile.android.cleanbase.domain.BaseUseCase;
import br.com.taqtile.android.cleanbase.domain.BaseUseCaseHandler;

import taqtile.android.sdk.push.TQInbox;
import taqtile.android.sdk.push.TQInboxMessage;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 7/27/16.
 */
public class NotificationDetailsPresenter implements NotificationDetailsContract.Presenter {

    private final NotificationDetailsContract.View mView;

    private final RequestNotificationDetailsUseCase mRequestNotificationDetailsUseCase;

    private final ChangeNotificationReadStatusUseCase mChangeNotificationReadStatusUseCase;

    private final SendCustomStatusUseCase mSendCustomStatusUseCase;

    private final BaseUseCaseHandler mUseCaseHandler;

    private TQInboxMessage mNotification;

    private String mPushId;

    public NotificationDetailsPresenter(@NonNull BaseUseCaseHandler useCaseHandler,
                                        @NonNull NotificationDetailsContract.View notificationDetailsView,
                                        @NonNull RequestNotificationDetailsUseCase requestNotificationDetailsUseCase,
                                        @NonNull ChangeNotificationReadStatusUseCase changeNotificationReadStatusUseCase,
                                        @NonNull SendCustomStatusUseCase sendCustomStatusUseCase,
                                        @NonNull NavigationManager navigationManager,
                                        @NonNull String pushId) {
        mUseCaseHandler = checkNotNull(useCaseHandler, "Use case handler cannot be null");
        mRequestNotificationDetailsUseCase = checkNotNull(requestNotificationDetailsUseCase, "Request Notification Details use case cannot be null");
        mChangeNotificationReadStatusUseCase = checkNotNull(changeNotificationReadStatusUseCase, "Change Notification Status use case cannot be null");
        mSendCustomStatusUseCase = checkNotNull(sendCustomStatusUseCase, "Send Custom Status use case cannot be null");
        mView = checkNotNull(notificationDetailsView, "Notification details view cannot be null");
        mPushId = pushId;
        mView.setPresenter(this);
        mView.setNavigationManager(navigationManager);
    }

    @Override
    public void openNotificationContent() {
        mView.showContentView();
    }

    @Override
    public void markNotificationAsReadOrUnread() {
        mUseCaseHandler.execute(mChangeNotificationReadStatusUseCase,
                new ChangeNotificationReadStatusUseCase.RequestValues(mPushId, mNotification.getStatus()),
                new BaseUseCase.UseCaseCallback<ChangeNotificationReadStatusUseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(ChangeNotificationReadStatusUseCase.ResponseValue responseValue) {
                        mPushId = mNotification.getId();
                        requestNotificationDetails();
                    }

                    @Override
                    public void onError() {
                        //TODO: Check how to handle this error
                        if (mView.isActive()) {
                            mView.hideLoading();
                        }
                    }
                });
    }

    @Override
    public void customStatusButtonClicked() {
        mView.showSendCustomStatusDialog();
    }

    @Override
    public void sendCustomStatus(String status) {
        mUseCaseHandler.execute(mSendCustomStatusUseCase,
                new SendCustomStatusUseCase.RequestValues(mPushId, status),
                new BaseUseCase.UseCaseCallback<SendCustomStatusUseCase.ResponseValue>() {

                    @Override
                    public void onSuccess(SendCustomStatusUseCase.ResponseValue responseValue) {
                        requestNotificationDetails();
                    }

                    @Override
                    public void onError() {
                        //TODO: Check how to handle this error
                    }
                });
    }

    @Override
    public void requestNotificationDetails() {
        mUseCaseHandler.execute(mRequestNotificationDetailsUseCase,
                new RequestNotificationDetailsUseCase.RequestValues(mPushId),
                new BaseUseCase.UseCaseCallback<RequestNotificationDetailsUseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(RequestNotificationDetailsUseCase.ResponseValue responseValue) {
                        mNotification = responseValue.getNotification();
                        mView.showNotificationDetails(responseValue.getNotification());
                        if (mNotification.getStatus() == TQInbox.TQInboxMessageStatus.READ) {
                            mView.showMarkAsUnreadButtonText();
                        } else {
                            mView.showMarkAsReadButtonText();
                        }
                    }

                    @Override
                    public void onError() {
                        //TODO: Check how to handle this error
                        if (mView.isActive()) {
                            mView.hideLoading();
                        }
                    }
                });
    }

    @Override
    public void start() {

    }

    @Override
    public void resume() {
        requestNotificationDetails();
    }
}
