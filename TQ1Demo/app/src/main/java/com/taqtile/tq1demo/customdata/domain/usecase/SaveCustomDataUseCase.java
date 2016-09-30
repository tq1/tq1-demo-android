package com.taqtile.tq1demo.customdata.domain.usecase;

import android.support.annotation.NonNull;

import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.source.notifications.NotificationRepository;

import java.util.Map;

import br.com.taqtile.android.cleanbase.domain.BaseUseCase;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 8/2/16.
 */
public class SaveCustomDataUseCase  extends BaseUseCase<SaveCustomDataUseCase.RequestValues,
        SaveCustomDataUseCase.ResponseValue> {

    private final NotificationRepository mNotificationRepository;

    public SaveCustomDataUseCase(@NonNull NotificationRepository notificationRepository) {
        mNotificationRepository = checkNotNull(notificationRepository, "notification repository cant be null");
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        DataSourceResponse<Boolean> response = mNotificationRepository.saveCustomData(requestValues.getCustomDataHash());

        if (response.isSuccess()) {
            getUseCaseCallback().onSuccess(
                    new ResponseValue());
        } else {
            //TODO: Send the error through callback
            getUseCaseCallback().onError();
        }
    }

    public static final class RequestValues implements BaseUseCase.RequestValues {

        private final Map<String, String> mCustomDataHash;

        public RequestValues(Map<String, String> customDataHash) {
            mCustomDataHash = customDataHash;
        }

        public Map<String, String> getCustomDataHash() {
            return mCustomDataHash;
        }

    }

    public static final class ResponseValue implements BaseUseCase.ResponseValue {

        public ResponseValue() {
        }
    }
}


