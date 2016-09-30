package com.taqtile.tq1demo.triggerlist.presentation.presenter;

import android.support.annotation.NonNull;

import com.taqtile.tq1demo.navigation.NavigationManager;
import com.taqtile.tq1demo.triggerlist.presentation.viewmodel.TriggerListItemViewModel;
import com.taqtile.tq1demo.triggerlist.presentation.viewmodel.mapper.TriggerToTriggerListItemViewModelMapper;
import com.taqtile.tq1demo.triggerlist.usecase.ListTriggersUseCase;
import com.taqtile.tqgeolocationsdk.models.Trigger;

import java.util.ArrayList;

import br.com.taqtile.android.cleanbase.domain.BaseUseCase;
import br.com.taqtile.android.cleanbase.domain.BaseUseCaseHandler;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 5/25/16.
 */

public class TriggerListPresenter implements TriggerListContract.Presenter {

    private final TriggerListContract.View mView;


    private final ListTriggersUseCase mListTriggersUseCase;

    private final BaseUseCaseHandler mUseCaseHandler;

    private final TriggerToTriggerListItemViewModelMapper mMapper;

    public TriggerListPresenter(@NonNull BaseUseCaseHandler useCaseHandler,
                                @NonNull TriggerListContract.View triggerListView,
                                @NonNull ListTriggersUseCase listTriggersUseCase,
                                @NonNull TriggerToTriggerListItemViewModelMapper mapper,
                                @NonNull NavigationManager navigationManager) {
        mUseCaseHandler = checkNotNull(useCaseHandler, "Use case handler cannot be null");
        mListTriggersUseCase = checkNotNull(listTriggersUseCase, "List triggers use case cannot be null");
        mView = checkNotNull(triggerListView, "Trigger list view cannot be null");
        mMapper = checkNotNull(mapper, "Mapper cannot be null");

        mView.setPresenter(this);
        mView.setNavigationManager(navigationManager);
    }

    @Override
    public void start() {
        requestTriggers();
    }

    @Override
    public void resume() {
    }


    @Override
    public void requestTriggers() {
        if (mView.isActive()) {
            mView.showLoading();
        }

        mUseCaseHandler.execute(mListTriggersUseCase,
                new ListTriggersUseCase.RequestValues(),
                new BaseUseCase.UseCaseCallback<ListTriggersUseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(ListTriggersUseCase.ResponseValue response) {
                        ArrayList<Trigger> triggersList = response.getTriggersList();

                        final ArrayList<TriggerListItemViewModel> viewModelTriggersList =
                                mMapper.mapTriggerList(triggersList);

                        if (mView.isActive()) {
                            mView.showTriggersList(viewModelTriggersList);
                            mView.hideLoading();
                        }
                    }

                    @Override
                    public void onError() {
                        //TODO: Check how to handle this error
                        if (mView.isActive()) {
                            mView.showTriggersListError(null);
                            mView.hideLoading();
                        }
                    }
                });
    }
}
