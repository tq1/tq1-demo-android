package com.taqtile.tq1demo.profilenotlogged.presentation.presenter;

import br.com.taqtile.android.cleanbase.presentation.presenter.BasePresenter;
import br.com.taqtile.android.cleanbase.presentation.view.BaseView;
import com.taqtile.tq1demo.navigation.NavigationManager;

/**
 * Created by taqtile on 7/6/16.
 */

public interface ProfileNotLoggedContract {

    interface View extends BaseView<Presenter, NavigationManager> {

        void showSignInUI();

    }

    interface Presenter extends BasePresenter {

        void signIn();

    }
}
