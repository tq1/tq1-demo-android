package com.taqtile.tq1demo.geofences.presentation.presenter;

import com.taqtile.tq1demo.navigation.NavigationManager;

import br.com.taqtile.android.cleanbase.presentation.presenter.BasePresenter;
import br.com.taqtile.android.cleanbase.presentation.view.BaseView;

/**
 * Created by taqtile on 7/29/16.
 */
public interface GeoFencesContract {

    interface View extends BaseView<Presenter, NavigationManager> {

        void showMapUI();
        void showLogUI();

    }

    interface Presenter extends BasePresenter {

        void mapCellClicked();
        void logCellClicked();

    }
}
