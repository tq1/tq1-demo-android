package com.taqtile.tq1demo.settings.domain.usecase;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.source.settings.SettingsRepository;

import br.com.taqtile.android.cleanbase.domain.BaseUseCase;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 8/3/16.
 */
public class SetTQ1StateUseCase extends BaseUseCase<SetTQ1StateUseCase.RequestValues,
        SetTQ1StateUseCase.ResponseValue> {

    private final SettingsRepository mSettingsRepository;

    public SetTQ1StateUseCase(@NonNull SettingsRepository settingsRepository) {
        mSettingsRepository = checkNotNull(settingsRepository, "settings repository cant be null");
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        DataSourceResponse<Boolean> response = mSettingsRepository.setPushNotificationEnabled(requestValues.getTQ1State(), requestValues.getActivity());

        if (response.isSuccess()) {
            getUseCaseCallback().onSuccess(
                    new ResponseValue());
        } else {
            //TODO: Send the error through callback
            getUseCaseCallback().onError();
        }
    }
    public static final class RequestValues implements BaseUseCase.RequestValues {

        private boolean mTQ1State;
        private Activity mActivity;

        public RequestValues(boolean tq1State, Activity activity) {
            mTQ1State = tq1State;
            mActivity = activity;
        }

        public boolean getTQ1State() {
            return mTQ1State;
        }

        public Activity getActivity() {
            return mActivity;
        }
    }

    public static final class ResponseValue implements BaseUseCase.ResponseValue {

        public ResponseValue() {
        }
    }
}

