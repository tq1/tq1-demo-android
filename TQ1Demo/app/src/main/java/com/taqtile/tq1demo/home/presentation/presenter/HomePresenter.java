package com.taqtile.tq1demo.home.presentation.presenter;

import android.support.annotation.NonNull;

import br.com.taqtile.android.cleanbase.domain.BaseUseCaseHandler;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 7/6/16.
 */

public class HomePresenter implements HomeContract.Presenter {

    private final HomeContract.View mView;

    private final BaseUseCaseHandler mUseCaseHandler;

    public HomePresenter(@NonNull BaseUseCaseHandler useCaseHandler,
                         @NonNull HomeContract.View homeView) {
        mUseCaseHandler = checkNotNull(useCaseHandler, "UseCaseHandler cannot be null!");
        mView = checkNotNull(homeView, "HomeView cannot be null!");

        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void resume() {

    }
}
