package com.taqtile.tq1demo.notificationdetails.domain.usecase;

import android.support.annotation.NonNull;

import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.source.notifications.NotificationRepository;

import br.com.taqtile.android.cleanbase.domain.BaseUseCase;
import taqtile.android.sdk.push.TQInbox;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 7/29/16.
 */
public class ChangeNotificationReadStatusUseCase extends BaseUseCase<ChangeNotificationReadStatusUseCase.RequestValues,
        ChangeNotificationReadStatusUseCase.ResponseValue> {

    private final NotificationRepository mNotificationRepository;

    public ChangeNotificationReadStatusUseCase(@NonNull NotificationRepository notificationRepository) {
        mNotificationRepository = checkNotNull(notificationRepository, "notification repository cant be null");
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        DataSourceResponse<Boolean> response = mNotificationRepository.changeNotificationStatus(requestValues.getPushId(), requestValues.getCurrentStatus());

        if (response.isSuccess()) {
            getUseCaseCallback().onSuccess(new ResponseValue());
        } else {
            //TODO: Send the error through callback
            getUseCaseCallback().onError();
        }
    }
    public static final class RequestValues implements BaseUseCase.RequestValues {

        private final String mPushId;
        private final TQInbox.TQInboxMessageStatus mCurrentStatus;

        public RequestValues(String pushId, TQInbox.TQInboxMessageStatus currentStatus) {
            mPushId = pushId;
            mCurrentStatus = currentStatus;
        }

        public String getPushId() {
            return mPushId;
        }

        public TQInbox.TQInboxMessageStatus getCurrentStatus() {
            return mCurrentStatus;
        }

    }

    public static final class ResponseValue implements BaseUseCase.ResponseValue {

        public ResponseValue() {
        }

    }
}

