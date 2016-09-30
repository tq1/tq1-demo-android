package com.taqtile.tq1demo.signin.presentation.presenter;

import android.support.annotation.NonNull;

import br.com.taqtile.android.cleanbase.domain.BaseUseCase;
import br.com.taqtile.android.cleanbase.domain.BaseUseCaseHandler;
import com.taqtile.tq1demo.navigation.NavigationManager;
import com.taqtile.tq1demo.signin.domain.usecase.ForgotUserPasswordUseCase;
import com.taqtile.tq1demo.signin.domain.usecase.SignInUseCase;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 5/23/16.
 */

public class SignInPresenter implements SignInContract.Presenter {

    private final SignInContract.View mView;

    private final SignInUseCase mSignInUseCase;

    private final ForgotUserPasswordUseCase mForgotUserPasswordUseCase;

    private final BaseUseCaseHandler mUseCaseHandler;

    public SignInPresenter(@NonNull BaseUseCaseHandler useCaseHandler,
                           @NonNull SignInContract.View signInView,
                           @NonNull SignInUseCase signIn,
                           @NonNull ForgotUserPasswordUseCase forgotPassword,
                           @NonNull NavigationManager navigationManager) {
        mUseCaseHandler = checkNotNull(useCaseHandler, "Use case handler cannot be null");
        mSignInUseCase = checkNotNull(signIn, "Sign In use case cannot be null");
        mForgotUserPasswordUseCase = checkNotNull(forgotPassword, "Forgot password use case cannot be null");
        mView = checkNotNull(signInView, "SignIn fragment cannot be null");

        mView.setPresenter(this);
        mView.setNavigationManager(checkNotNull(navigationManager, "NavigationManager cannot be null!"));
    }

    @Override
    public void signIn(String email, String password) {
        if (email == null || "".equals(email)) {
            if (mView.isActive()) {
                mView.showEmptyEmailError();
            }
        } else if (password == null || "".equals(password)) {
            if (mView.isActive()) {
                mView.showEmptyPasswordError();
            }
        } else {
            if (mView.isActive()) {
                mView.showLoading();
            }
            mUseCaseHandler.execute(mSignInUseCase,
                    new SignInUseCase.RequestValues(email, password, false),
                    new BaseUseCase.UseCaseCallback<SignInUseCase.ResponseValue>() {
                        @Override
                        public void onSuccess(SignInUseCase.ResponseValue response) {
                            //TODO: Handle Sign In Success
                            if (mView.isActive()) {
                                mView.showProfileUI();
                                mView.hideLoading();
                            }
                        }

                        @Override
                        public void onError() {
                            //TODO: Check if this is how we handle
                            if (mView.isActive()) {
                                mView.hideLoading();
                                mView.showSignInError(null);
                            }
                        }
                    });
        }
    }

    @Override
    public void signInWithFacebook() {
        if (mView.isActive()) {
            mView.showLoading();
        }
        mUseCaseHandler.execute(mSignInUseCase,
                new SignInUseCase.RequestValues(null, null, true),
                new BaseUseCase.UseCaseCallback<SignInUseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(SignInUseCase.ResponseValue response) {
                        //TODO: Handle Sign In Success
                        if (mView.isActive()) {
                            mView.hideLoading();
                        }
                    }

                    @Override
                    public void onError() {
                        //TODO: Check how to handle this error!
                        if (mView.isActive()) {
                            mView.hideLoading();
                            mView.showSignInError(null);
                        }
                    }
                });
    }

    @Override
    public void forgotPassword(String email) {
        if (email == null) {
            if (mView.isActive()) {
                mView.showForgotPasswordEmailError();
            }
        } else {
            if (mView.isActive()) {
                mView.showLoading();
            }
            mUseCaseHandler.execute(mForgotUserPasswordUseCase,
                    new ForgotUserPasswordUseCase.RequestValues(email),
                    new BaseUseCase.UseCaseCallback<ForgotUserPasswordUseCase.ResponseValue>() {
                        @Override
                        public void onSuccess(ForgotUserPasswordUseCase.ResponseValue response) {
                            if (mView.isActive()) {
                                mView.showForgotPasswordSuccess();
                                mView.hideLoading();
                            }
                        }

                        @Override
                        public void onError() {
                            if (mView.isActive()) {
                                mView.showForgotPasswordError();
                                mView.hideLoading();
                            }
                        }
                    });
        }
    }

    @Override
    public void signUp() {
        if (mView.isActive()) {
            mView.showSignUpUI();
        }
    }

    @Override
    public void forgotPassword() {
        if (mView.isActive()) {
            mView.showForgotPasswordUI();
        }
    }

    @Override
    public void start() {

    }

    @Override
    public void resume() {

    }

}
