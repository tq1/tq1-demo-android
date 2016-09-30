package com.taqtile.tq1demo.signin.presentation.presenter;

import br.com.taqtile.android.cleanbase.presentation.presenter.BasePresenter;
import br.com.taqtile.android.cleanbase.presentation.view.BaseView;
import com.taqtile.tq1demo.navigation.NavigationManager;

/**
 * Created by taqtile on 5/23/16.
 */

public interface SignInContract {

    interface View extends BaseView<Presenter, NavigationManager> {

        void showEmptyEmailError();

        void showEmptyPasswordError();

        void showSignInError(String message);

        void showForgotPasswordEmailError();

        void showForgotPasswordSuccess();

        void showForgotPasswordError();

        void showSignUpUI();

        void showForgotPasswordUI();

        void showProfileUI();

    }

    interface Presenter extends BasePresenter {

        void signIn(String email, String password);

        void signInWithFacebook();

        void forgotPassword(String email);

        void signUp();

        void forgotPassword();
    }


}
