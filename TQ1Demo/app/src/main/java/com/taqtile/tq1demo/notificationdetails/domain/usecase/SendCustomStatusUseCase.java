package com.taqtile.tq1demo.notificationdetails.domain.usecase;

import android.support.annotation.NonNull;

import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.source.notifications.NotificationRepository;

import br.com.taqtile.android.cleanbase.domain.BaseUseCase;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 7/28/16.
 */
public class SendCustomStatusUseCase extends BaseUseCase<SendCustomStatusUseCase.RequestValues,
        SendCustomStatusUseCase.ResponseValue> {

    private final NotificationRepository mNotificationRepository;

    public SendCustomStatusUseCase(@NonNull NotificationRepository notificationRepository) {
        mNotificationRepository = checkNotNull(notificationRepository, "notification repository cant be null");
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        DataSourceResponse<Boolean> response = mNotificationRepository.sendCustomStatus(requestValues.getPushId(), requestValues.getStatus());

        if (response.isSuccess()) {
            getUseCaseCallback().onSuccess(
                    new ResponseValue());
        } else {
            //TODO: Send the error through callback
            getUseCaseCallback().onError();
        }
    }
    public static final class RequestValues implements BaseUseCase.RequestValues {

        private final String mPushId;
        private final String mStatus;

        public RequestValues(String pushId, String status) {
            mPushId = pushId;
            mStatus = status;
        }

        public String getPushId() {
            return mPushId;
        }

        public String getStatus() {
            return mStatus;
        }
    }

    public static final class ResponseValue implements BaseUseCase.ResponseValue {

        public ResponseValue() {
        }

    }
}

