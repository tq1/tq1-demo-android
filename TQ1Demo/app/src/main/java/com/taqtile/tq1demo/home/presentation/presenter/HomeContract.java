package com.taqtile.tq1demo.home.presentation.presenter;

import br.com.taqtile.android.cleanbase.presentation.presenter.BasePresenter;
import br.com.taqtile.android.cleanbase.presentation.view.BaseView;
import com.taqtile.tq1demo.navigation.NavigationManager;

/**
 * Created by taqtile on 7/6/16.
 */

public interface HomeContract {

    interface View extends BaseView<Presenter, NavigationManager> {
        
    }

    interface Presenter extends BasePresenter {

    }

}
