package com.taqtile.tq1demo.notificationdetails.domain.usecase;

import android.support.annotation.NonNull;

import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.source.notifications.NotificationRepository;

import br.com.taqtile.android.cleanbase.domain.BaseUseCase;
import taqtile.android.sdk.push.TQInboxMessage;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 7/27/16.
 */
public class RequestNotificationDetailsUseCase extends BaseUseCase<RequestNotificationDetailsUseCase.RequestValues,
        RequestNotificationDetailsUseCase.ResponseValue> {

    private final NotificationRepository mNotificationRepository;

    public RequestNotificationDetailsUseCase(@NonNull NotificationRepository notificationRepository) {
        mNotificationRepository = checkNotNull(notificationRepository, "notification repository cant be null");
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        DataSourceResponse<TQInboxMessage> response = mNotificationRepository.getNotification(requestValues.getPushId());

        if (response.isSuccess()) {
            getUseCaseCallback().onSuccess(
                    new ResponseValue(response.getResponse()));
        } else {
            //TODO: Send the error through callback
            getUseCaseCallback().onError();
        }
    }
    public static final class RequestValues implements BaseUseCase.RequestValues {

        private final String mPushId;

        public RequestValues(String pushId) {
            mPushId = pushId;
        }

        public String getPushId() {
            return mPushId;
        }

    }

    public static final class ResponseValue implements BaseUseCase.ResponseValue {

        private final TQInboxMessage mNotification;

        public ResponseValue(@NonNull TQInboxMessage notification) {
            mNotification = checkNotNull(notification, "notification cant be null");
        }

        public TQInboxMessage getNotification() {
            return mNotification;
        }

    }
}

