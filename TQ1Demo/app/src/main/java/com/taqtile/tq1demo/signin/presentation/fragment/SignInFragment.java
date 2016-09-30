package com.taqtile.tq1demo.signin.presentation.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import br.com.taqtile.android.cleanbase.presentation.view.BaseFragment;
import br.com.taqtile.android.edittexts.EmailLabelEditTextCaption;
import br.com.taqtile.android.edittexts.PasswordLabelEditTextCaption;
import com.taqtile.tq1demo.R;
import com.taqtile.tq1demo.navigation.NavigationManager;
import com.taqtile.tq1demo.signin.presentation.presenter.SignInContract;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 5/23/16.
 */

public class SignInFragment extends BaseFragment
        implements SignInContract.View, View.OnClickListener {

    private SignInContract.Presenter mPresenter;

    private NavigationManager mNavigationManager;

    //region View Components

    @BindView(R.id.fragment_signin_email_cpf_edit_text)
    EmailLabelEditTextCaption mEmailEditText;

    @BindView(R.id.fragment_signin_password_edit_text)
    PasswordLabelEditTextCaption mPasswordEditText;

    @BindView(R.id.fragment_signin_facebook_button)
    Button mFacebookButton;

    @BindView(R.id.fragment_signin_signin_button)
    Button mSignInButton;

    @BindView(R.id.fragment_signin_forgot_button)
    Button mForgotPasswordButton;

    @BindView(R.id.fragment_signin_signup_link_button)
    Button mSignUpButton;

    //endregion

    public static SignInFragment newInstance() {
        return new SignInFragment();
    }

    public SignInFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_signin, container, false);
        ButterKnife.bind(this, root);
        setupView();
        return root;
    }

    private void setupView() {
        mFacebookButton.setOnClickListener(this);
        mSignInButton.setOnClickListener(this);
        mForgotPasswordButton.setOnClickListener(this);
        mSignUpButton.setOnClickListener(this);
    }

    @Override
    public void setPresenter(@NonNull SignInContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public boolean refresh() {
        return false;
    }

    @Override
    public void willAppear() {
    }

    @Override
    public void willDisappear() {
    }

    @Override
    public void setNavigationManager(NavigationManager navigationManager) {
        mNavigationManager = navigationManager;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.start();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.resume();
    }

    @Override
    public void showEmptyEmailError() {
        //TODO: Implement correct message
        Toast t = Toast.makeText(getContext(), "Preencha o email!", Toast.LENGTH_SHORT);
        t.show();
    }

    @Override
    public void showEmptyPasswordError() {
        //TODO: Implement correct message
        Toast t = Toast.makeText(getContext(), "Preencha a senha!", Toast.LENGTH_SHORT);
        t.show();
    }

    @Override
    public void showSignInError(String message) {
        //TODO: Implement correct message/format
        Toast t = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
        t.show();
    }

    @Override
    public void showForgotPasswordEmailError() {
        //TODO: Implement correct message/format
    }

    @Override
    public void showForgotPasswordSuccess() {
        //TODO: Handle Forgot password success
    }

    @Override
    public void showForgotPasswordError() {
        //TODO: Handle forgot password error
    }

    @Override
    public void showSignUpUI() {
        //TODO: Navigate to sign up
    }

    @Override
    public void showForgotPasswordUI() {
        //TODO: Show Forgot Dialog
    }

    @Override
    public void showProfileUI() {
        mNavigationManager.showProfileUI();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.fragment_signin_facebook_button:
                mPresenter.signInWithFacebook();
                break;
            case R.id.fragment_signin_forgot_button:
                //TODO: Handle forgot password
                break;
            case R.id.fragment_signin_signin_button:
                mPresenter.signIn(mEmailEditText.getText(), mPasswordEditText.getText());
                break;
            case R.id.fragment_signin_signup_link_button:
                mPresenter.signUp();
                break;
            default:
                break;
        }
    }
}
