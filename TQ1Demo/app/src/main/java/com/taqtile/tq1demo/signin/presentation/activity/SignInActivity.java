package com.taqtile.tq1demo.signin.presentation.activity;

import android.content.Context;
import android.content.Intent;

import br.com.taqtile.android.cleanbase.presentation.presenter.BasePresenter;
import com.taqtile.tq1demo.base.presentation.activity.TemplateBackActivity;
import com.taqtile.tq1demo.Injection;
import com.taqtile.tq1demo.navigation.NavigationHelper;
import com.taqtile.tq1demo.signin.presentation.fragment.SignInFragment;
import com.taqtile.tq1demo.signin.presentation.presenter.SignInPresenter;

/**
 * Created by taqtile on 5/24/16.
 */

public class SignInActivity extends TemplateBackActivity {

    private SignInFragment mFragment;

    @Override
    public SignInFragment getFragment() {
        SignInFragment signInFragment = (SignInFragment) getSupportFragmentManager()
                .findFragmentById(getFragmentContainerId());
        if (signInFragment == null) {
            signInFragment = SignInFragment.newInstance();
        }

        mFragment = signInFragment;

        return mFragment;
    }

    @Override
    public BasePresenter getPresenter() {
        return new SignInPresenter(
                Injection.provideUseCaseHandler(),
                mFragment,
                Injection.provideSignInUseCase(getApplicationContext()),
                Injection.provideForgotPasswordUseCase(getApplicationContext()),
                new NavigationHelper(this));
    }

    public static void navigate(Context context) {
        Intent intent = new Intent(context, SignInActivity.class);
        context.startActivity(intent);
    }
}
