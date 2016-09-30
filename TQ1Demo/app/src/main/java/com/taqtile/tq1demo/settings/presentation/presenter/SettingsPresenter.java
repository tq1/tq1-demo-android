package com.taqtile.tq1demo.settings.presentation.presenter;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.taqtile.tq1demo.data.model.SettingsInfo;
import com.taqtile.tq1demo.navigation.NavigationManager;
import com.taqtile.tq1demo.settings.domain.usecase.LoadSettingsUseCase;
import com.taqtile.tq1demo.settings.domain.usecase.SaveSettingsUseCase;
import com.taqtile.tq1demo.settings.domain.usecase.SetTQ1StateUseCase;
import com.taqtile.tq1demo.settings.domain.usecase.SetTQGStateUseCase;

import br.com.taqtile.android.cleanbase.domain.BaseUseCase;
import br.com.taqtile.android.cleanbase.domain.BaseUseCaseHandler;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 8/2/16.
 */
public class SettingsPresenter implements SettingsContract.Presenter {

    private final SettingsContract.View mView;

    private final LoadSettingsUseCase mLoadSettingsUseCase;
    private final SaveSettingsUseCase mSaveSettingsUseCase;
    private final SetTQGStateUseCase mSetTQGStateUseCase;
    private final SetTQ1StateUseCase mSetTQ1StateUseCase;

    private final BaseUseCaseHandler mUseCaseHandler;

    public SettingsPresenter(@NonNull BaseUseCaseHandler useCaseHandler,
                             @NonNull SettingsContract.View settingsView,
                             @NonNull LoadSettingsUseCase loadSettingsUseCase,
                             @NonNull SaveSettingsUseCase saveSettingsUseCase,
                             @NonNull SetTQGStateUseCase setTQGStateUseCase,
                             @NonNull SetTQ1StateUseCase setTQ1StateUseCase,
                             @NonNull NavigationManager navigationManager) {
        mUseCaseHandler = checkNotNull(useCaseHandler, "Use case handler cannot be null");
        mLoadSettingsUseCase = checkNotNull(loadSettingsUseCase, "Load Settings use case cannot be null");
        mSaveSettingsUseCase = checkNotNull(saveSettingsUseCase, "Save Settings use case cannot be null");
        mSetTQGStateUseCase = checkNotNull(setTQGStateUseCase, "Set TQG State use case cannot be null");
        mSetTQ1StateUseCase = checkNotNull(setTQ1StateUseCase, "Set TQ1 State use case cannot be null");
        mView = checkNotNull(settingsView, "Settings view cannot be null");

        mView.setPresenter(this);
        mView.setNavigationManager(navigationManager);
    }

    @Override
    public void start() {
        requestSettings();
    }

    @Override
    public void resume() {
    }

    @Override
    public void requestSettings() {
        if (mView.isActive()) {
            mView.showLoading();
        }

        mUseCaseHandler.execute(mLoadSettingsUseCase,
                new LoadSettingsUseCase.RequestValues(),
                new BaseUseCase.UseCaseCallback<LoadSettingsUseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(LoadSettingsUseCase.ResponseValue responseValue) {
                        if (mView.isActive()) {
                            mView.showSettings(responseValue.getSettingsInfo());
                            mView.hideLoading();
                        }
                    }

                    @Override
                    public void onError() {
                        //TODO: Check how to handle this error
                        if (mView.isActive()) {
                            mView.showError();
                            mView.hideLoading();
                        }
                    }
                });
    }

    @Override
    public void geoLocationSwitchStateChanged(final boolean isGeoLocationEnabled, final boolean isPushNotificationEnabled, Activity activity) {

        mUseCaseHandler.execute(mSetTQGStateUseCase,
                new SetTQGStateUseCase.RequestValues(isGeoLocationEnabled, activity),
                new BaseUseCase.UseCaseCallback<SetTQGStateUseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(SetTQGStateUseCase.ResponseValue responseValue) {
                        if (mView.isActive()) {
                            mView.hideLoading();
                            saveSettings(isGeoLocationEnabled, isPushNotificationEnabled);
                        }
                    }

                    @Override
                    public void onError() {
                        //TODO: Check how to handle this error
                        if (mView.isActive()) {
                            mView.showError();
                            mView.hideLoading();
                        }
                    }
                });
    }

    @Override
    public void pushNotificationSwitchStateChanged(final boolean isGeoLocationEnabled, final boolean isPushNotificationEnabled, Activity activity) {

        mUseCaseHandler.execute(mSetTQ1StateUseCase,
                new SetTQ1StateUseCase.RequestValues(isPushNotificationEnabled, activity),
                new BaseUseCase.UseCaseCallback<SetTQ1StateUseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(SetTQ1StateUseCase.ResponseValue responseValue) {
                        if (mView.isActive()) {
                            mView.hideLoading();
                            saveSettings(isGeoLocationEnabled, isPushNotificationEnabled);
                        }
                    }

                    @Override
                    public void onError() {
                        //TODO: Check how to handle this error
                        if (mView.isActive()) {
                            mView.showError();
                            mView.hideLoading();
                        }
                    }
                });
    }

    private void saveSettings(boolean isGeoLocationEnabled, boolean isPushNotificationEnabled) {
        SettingsInfo settingsInfo = new SettingsInfo(isGeoLocationEnabled, isPushNotificationEnabled);
        mUseCaseHandler.execute(mSaveSettingsUseCase,
                new SaveSettingsUseCase.RequestValues(settingsInfo),
                new BaseUseCase.UseCaseCallback<SaveSettingsUseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(SaveSettingsUseCase.ResponseValue responseValue) {
                        if (mView.isActive()) {
                            mView.hideLoading();
                        }
                    }

                    @Override
                    public void onError() {
                        //TODO: Check how to handle this error
                        if (mView.isActive()) {
                            mView.showError();
                            mView.hideLoading();
                        }
                    }
                });
    }

}
