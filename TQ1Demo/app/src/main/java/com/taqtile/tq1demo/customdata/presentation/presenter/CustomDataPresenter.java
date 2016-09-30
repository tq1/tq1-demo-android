package com.taqtile.tq1demo.customdata.presentation.presenter;

import android.support.annotation.NonNull;

import com.taqtile.tq1demo.customdata.domain.usecase.RetrieveCustomDataUseCase;
import com.taqtile.tq1demo.customdata.domain.usecase.SaveCustomDataUseCase;
import com.taqtile.tq1demo.customdata.domain.usecase.SendCustomDataUseCase;
import com.taqtile.tq1demo.customdata.presentation.viewmodel.CustomDataListItemViewModel;
import com.taqtile.tq1demo.customdata.presentation.viewmodel.mapper.CustomDataToCustomDataListItemViewModelMapper;
import com.taqtile.tq1demo.navigation.NavigationManager;

import java.util.ArrayList;
import java.util.Map;

import br.com.taqtile.android.cleanbase.domain.BaseUseCase;
import br.com.taqtile.android.cleanbase.domain.BaseUseCaseHandler;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 7/29/16.
 */
public class CustomDataPresenter implements CustomDataContract.Presenter {

    private final CustomDataContract.View mView;

    private final BaseUseCaseHandler mUseCaseHandler;

    private final CustomDataToCustomDataListItemViewModelMapper mMapper;

    private final SendCustomDataUseCase mSendCustomDataUseCase;
    private final RetrieveCustomDataUseCase mRetrieveCustomDataUseCase;
    private final SaveCustomDataUseCase mSaveCustomDataUseCase;

    Map<String, String> mCustomDataHash;

    public CustomDataPresenter(@NonNull BaseUseCaseHandler useCaseHandler,
                                @NonNull CustomDataContract.View productListView,
                                @NonNull SendCustomDataUseCase sendCustomDataUseCase,
                               @NonNull RetrieveCustomDataUseCase retrieveCustomDataUseCase,
                               @NonNull SaveCustomDataUseCase saveCustomDataUseCase,
                               @NonNull CustomDataToCustomDataListItemViewModelMapper mapper,
                                @NonNull NavigationManager navigationManager) {
        mUseCaseHandler = checkNotNull(useCaseHandler, "Use case handler cannot be null");
        mSendCustomDataUseCase = checkNotNull(sendCustomDataUseCase, "Send Custom Data use case cannot be null");
        mRetrieveCustomDataUseCase = checkNotNull(retrieveCustomDataUseCase, "Retrieve Custom Data use case cannot be null");
        mSaveCustomDataUseCase = checkNotNull(saveCustomDataUseCase, "Save Custom Data use case cannot be null");
        mView = checkNotNull(productListView, "Custom Data view cannot be null");
        mMapper = checkNotNull(mapper, "Mapper cannot be null");

        mView.setPresenter(this);
        mView.setNavigationManager(navigationManager);
    }

    @Override
    public void start() {
        retrieveCustomData();
    }

    @Override
    public void resume() {
    }

    @Override
    public void sendCustomData(ArrayList<CustomDataListItemViewModel> customDataListItemViewModelArrayList) {
        // This method will override the saved Custom Data from the Shared Preferences with the customDataListItemViewModelArrayList data
        if (mView.isActive()) {
            mView.showLoading();
        }

        mUseCaseHandler.execute(mSendCustomDataUseCase,
                new SendCustomDataUseCase.RequestValues(mCustomDataHash),
                new BaseUseCase.UseCaseCallback<SendCustomDataUseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(SendCustomDataUseCase.ResponseValue responseValue) {
                        if (mView.isActive()) {
                            mView.hideLoading();
                            mView.showSendDataSuccessMessage();
                            mView.setSendDataButtonEnabled();
                        }
                    }

                    @Override
                    public void onError() {
//                        Actually, there's no callback for this error in the SDK, so this is never called for now
//                        but it's already implemented for when the SDK implements a callback.
                        if (mView.isActive()) {
                            mView.hideLoading();
                            mView.showSendDataErrorMessage();
                            mView.setSendDataButtonEnabled();
                        }
                    }
                });

    }

    @Override
    public void addCustomData(final String key, String value) {

        if (key != null && key.length() > 0 && value != null && value.length() > 0) {
            mCustomDataHash.put(key, value);

            if (mView.isActive()) {
                mView.showLoading();
            }
            mUseCaseHandler.execute(mSaveCustomDataUseCase,
                    new SaveCustomDataUseCase.RequestValues(mCustomDataHash),
                    new BaseUseCase.UseCaseCallback<SaveCustomDataUseCase.ResponseValue>() {
                        @Override
                        public void onSuccess(SaveCustomDataUseCase.ResponseValue responseValue) {
                            if (mView.isActive()) {
                                mView.hideLoading();

                                mView.showCustomDataList(mMapper.mapCustomDataList(mCustomDataHash));
                            }
                        }

                        @Override
                        public void onError() {
                            mCustomDataHash.remove(key);
                            if (mView.isActive()) {
                                mView.hideLoading();
                                mView.showAddDataErrorMessage();
                            }
                        }
                    });
        } else {
            mView.showAddDataErrorMessage();
        }
    }

    @Override
    public void updateCustomData(String oldKey, final String newKey, String value) {
        if (oldKey.equals(newKey)) {
            // If the hash already contains a value for this key, the add method will just replace the existing custom data
            addCustomData(newKey, value);
        } else {
            // If user edit the key value, we remove the old custom data and add a new one
            mCustomDataHash.remove(oldKey);
            addCustomData(newKey, value);
        }
    }

    @Override
    public void deleteCustomData(final String key, final String value) {
        mCustomDataHash.remove(key);

        mUseCaseHandler.execute(mSaveCustomDataUseCase,
                new SaveCustomDataUseCase.RequestValues(mCustomDataHash),
                new BaseUseCase.UseCaseCallback<SaveCustomDataUseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(SaveCustomDataUseCase.ResponseValue responseValue) {
                        if (mView.isActive()) {
                            mView.hideLoading();
                            mView.showCustomDataList(mMapper.mapCustomDataList(mCustomDataHash));
                        }
                    }

                    @Override
                    public void onError() {
                        mCustomDataHash.put(key, value);
                        if (mView.isActive()) {
                            mView.hideLoading();
                            mView.showDeleteDataErrorMessage();
                        }
                    }
                });

    }

    @Override
    public void addCustomDataButtonClicked() {
        mView.showAddCustomDataDialog();
    }

    @Override
    public void customDataCellClicked(String key, String value) {
        mView.showEditCustomDataDialog(key, value);
    }

    @Override
    public void customDataCellLongPressed(String key, String value) {
        mView.showDeleteCustomDataDialog(key, value);
    }

    private void retrieveCustomData() {
        // This method will load the saved Custom Data from the Shared Preferences.
        if (mView.isActive()) {
            mView.showLoading();
        }

        mUseCaseHandler.execute(mRetrieveCustomDataUseCase,
                new RetrieveCustomDataUseCase.RequestValues(),
                new BaseUseCase.UseCaseCallback<RetrieveCustomDataUseCase.ResponseValue>() {

                    @Override
                    public void onSuccess(RetrieveCustomDataUseCase.ResponseValue responseValue) {
                        mCustomDataHash = responseValue.getCustomDataHash();
                        if (mView.isActive()) {
                            mView.hideLoading();
                            mView.showCustomDataList(mMapper.mapCustomDataList(responseValue.getCustomDataHash()));
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
