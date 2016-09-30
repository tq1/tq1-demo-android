package com.taqtile.tq1demo.triggerlist.presentation.presenter;

import com.taqtile.tq1demo.navigation.NavigationManager;
import com.taqtile.tq1demo.triggerlist.presentation.viewmodel.TriggerListItemViewModel;

import java.util.ArrayList;

import br.com.taqtile.android.cleanbase.presentation.presenter.BasePresenter;
import br.com.taqtile.android.cleanbase.presentation.view.BaseView;

/**
 * Created by taqtile on 5/25/16.
 */

public interface TriggerListContract {

    interface View extends BaseView<Presenter, NavigationManager> {

        void showTriggersList(ArrayList<TriggerListItemViewModel> triggers);

        void showPlaceholder();

        void showTriggersListError(String error);

    }

    interface Presenter extends BasePresenter {

        void requestTriggers();

    }

}
