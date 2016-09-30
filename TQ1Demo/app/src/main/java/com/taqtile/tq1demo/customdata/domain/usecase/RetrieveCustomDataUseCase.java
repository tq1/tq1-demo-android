package com.taqtile.tq1demo.customdata.domain.usecase;

import android.support.annotation.NonNull;

import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.source.notifications.NotificationRepository;

import java.util.Map;

import br.com.taqtile.android.cleanbase.domain.BaseUseCase;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 8/1/16.
 */
public class RetrieveCustomDataUseCase extends BaseUseCase<RetrieveCustomDataUseCase.RequestValues,
        RetrieveCustomDataUseCase.ResponseValue> {

    private final NotificationRepository mNotificationRepository;

    public RetrieveCustomDataUseCase(@NonNull NotificationRepository notificationRepository) {
        mNotificationRepository = checkNotNull(notificationRepository, "notification repository cant be null");
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        DataSourceResponse<Map<String, String>> response = mNotificationRepository.retrieveCustomData();

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

        private final Map<String, String> mCustomDataHash;

        public ResponseValue(Map<String, String> customDataHash) {
            mCustomDataHash = customDataHash;
        }

        public Map<String, String> getCustomDataHash() {
            return mCustomDataHash;
        }
    }
}


