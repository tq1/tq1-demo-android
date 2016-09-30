package com.taqtile.tq1demo.notificationlist.domain.usecase;

import android.support.annotation.NonNull;

import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.source.notifications.NotificationRepository;

import java.util.ArrayList;

import br.com.taqtile.android.cleanbase.domain.BaseUseCase;
import taqtile.android.sdk.push.TQInboxMessage;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 7/26/16.
 */
public class ListNotificationsUseCase extends BaseUseCase<ListNotificationsUseCase.RequestValues,
        ListNotificationsUseCase.ResponseValue> {

    private final NotificationRepository mNotificationRepository;

    public ListNotificationsUseCase(@NonNull NotificationRepository notificationRepository) {
        mNotificationRepository = checkNotNull(notificationRepository, "notification repository cant be null");
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        DataSourceResponse<ArrayList<TQInboxMessage>> response = mNotificationRepository.getNotifications();

        if (response.isSuccess()) {
            getUseCaseCallback().onSuccess(
                    new ResponseValue(response.getResponse()));
        } else {
            //TODO: Send the error through callback
            getUseCaseCallback().onError();
        }
    }
    public static final class RequestValues implements BaseUseCase.RequestValues {
        public RequestValues() {
        }

    }

    public static final class ResponseValue implements BaseUseCase.ResponseValue {

        private final ArrayList<TQInboxMessage> mNotifications;

        public ResponseValue(@NonNull ArrayList<TQInboxMessage> notifications) {
            mNotifications = checkNotNull(notifications, "notifications cant be null");
        }

        public ArrayList<TQInboxMessage> getNotifications() {
            return mNotifications;
        }

    }
}

