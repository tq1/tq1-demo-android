package com.taqtile.tq1demo.settings.domain.usecase;

import android.support.annotation.NonNull;

import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.model.SettingsInfo;
import com.taqtile.tq1demo.data.source.settings.SettingsRepository;

import br.com.taqtile.android.cleanbase.domain.BaseUseCase;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 7/26/16.
 */
public class SaveSettingsUseCase extends BaseUseCase<SaveSettingsUseCase.RequestValues,
        SaveSettingsUseCase.ResponseValue> {

    private final SettingsRepository mSettingsRepository;

    public SaveSettingsUseCase(@NonNull SettingsRepository settingsRepository) {
        mSettingsRepository = checkNotNull(settingsRepository, "settings repository cant be null");
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        DataSourceResponse<Boolean> response = mSettingsRepository.saveSettingsInfo(requestValues.getSettingsInfo());

        if (response.isSuccess()) {
            getUseCaseCallback().onSuccess(
                    new ResponseValue());
        } else {
            //TODO: Send the error through callback
            getUseCaseCallback().onError();
        }
    }
    public static final class RequestValues implements BaseUseCase.RequestValues {

        private SettingsInfo mSettingsInfo;

        public RequestValues(SettingsInfo settingsInfo) {
            mSettingsInfo = settingsInfo;
        }

        public SettingsInfo getSettingsInfo() {
            return mSettingsInfo;
        }
    }

    public static final class ResponseValue implements BaseUseCase.ResponseValue {

        public ResponseValue() {
        }
    }
}

