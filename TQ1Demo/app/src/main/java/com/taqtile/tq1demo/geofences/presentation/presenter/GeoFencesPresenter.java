package com.taqtile.tq1demo.geofences.presentation.presenter;

import android.support.annotation.NonNull;

import br.com.taqtile.android.cleanbase.domain.BaseUseCaseHandler;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 7/29/16.
 */
public class GeoFencesPresenter implements GeoFencesContract.Presenter {

    private final GeoFencesContract.View mView;

    private final BaseUseCaseHandler mUseCaseHandler;

    public GeoFencesPresenter(@NonNull BaseUseCaseHandler useCaseHandler,
                         @NonNull GeoFencesContract.View geoFencesView) {
        mUseCaseHandler = checkNotNull(useCaseHandler, "UseCaseHandler cannot be null!");
        mView = checkNotNull(geoFencesView, "Geo Fences View cannot be null!");

        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void mapCellClicked() {
        mView.showMapUI();
    }

    @Override
    public void logCellClicked() {
        mView.showLogUI();
    }
}
