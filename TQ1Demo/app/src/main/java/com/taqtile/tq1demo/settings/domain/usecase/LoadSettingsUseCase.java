package com.taqtile.tq1demo.settings.domain.usecase;

import android.support.annotation.NonNull;

import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.model.SettingsInfo;
import com.taqtile.tq1demo.data.source.settings.SettingsRepository;

import br.com.taqtile.android.cleanbase.domain.BaseUseCase;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 8/2/16.
 */
public class LoadSettingsUseCase extends BaseUseCase<LoadSettingsUseCase.RequestValues,
        LoadSettingsUseCase.ResponseValue> {

    private final SettingsRepository mNotificationRepository;

    public LoadSettingsUseCase(@NonNull SettingsRepository notificationRepository) {
        mNotificationRepository = checkNotNull(notificationRepository, "settings repository cant be null");
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        DataSourceResponse<SettingsInfo> settingsInfo = mNotificationRepository.getSettingsInfo();
        DataSourceResponse<SettingsInfo> response = settingsInfo;

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

        private final SettingsInfo mSettingsInfo;

        public ResponseValue(@NonNull SettingsInfo settingsInfo) {
            mSettingsInfo = checkNotNull(settingsInfo, "settings info cant be null");
        }

        public SettingsInfo getSettingsInfo() {
            return mSettingsInfo;
        }

    }
}

