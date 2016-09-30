package com.taqtile.tq1demo.profilenotlogged.presentation.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.taqtile.android.cleanbase.presentation.view.BaseFragment;
import com.taqtile.tq1demo.R;
import com.taqtile.tq1demo.buttons.TemplateButton;
import com.taqtile.tq1demo.navigation.NavigationManager;
import com.taqtile.tq1demo.profilenotlogged.presentation.presenter.ProfileNotLoggedContract;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 7/6/16.
 */

public class ProfileNotLoggedFragment extends BaseFragment
        implements ProfileNotLoggedContract.View, View.OnClickListener {

    private ProfileNotLoggedContract.Presenter mPresenter;

    private NavigationManager mNavigationManager;

    private static String DEBUG_TAG;

    //region ViewComponents

    @BindView(R.id.fragment_profilenotlogged_signin_button)
    TemplateButton mSignInButton;

    //endregion

    public static ProfileNotLoggedFragment newInstance() {
        return new ProfileNotLoggedFragment();
    }

    public ProfileNotLoggedFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profilenotlogged, container, false);
        ButterKnife.bind(this, root);
        setupView();
        return root;
    }

    private void setupView() {
        mSignInButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.fragment_profilenotlogged_signin_button:
                mPresenter.signIn();
                break;
        }
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
    public void showSignInUI() {
        mNavigationManager.showSignInUI();
    }

    @Override
    public void setPresenter(ProfileNotLoggedContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter, "Presenter cannot be null!");
    }

    @Override
    public void setNavigationManager(NavigationManager navigationManager) {
        mNavigationManager = checkNotNull(navigationManager, "NavigationManager cannot be null!");
    }
}
