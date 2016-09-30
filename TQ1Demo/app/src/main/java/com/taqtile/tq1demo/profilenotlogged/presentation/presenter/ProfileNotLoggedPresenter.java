package com.taqtile.tq1demo.profilenotlogged.presentation.presenter;

import android.support.annotation.NonNull;

import br.com.taqtile.android.cleanbase.domain.BaseUseCaseHandler;
import com.taqtile.tq1demo.navigation.NavigationManager;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 7/6/16.
 */

public class ProfileNotLoggedPresenter implements ProfileNotLoggedContract.Presenter {

    private final ProfileNotLoggedContract.View mView;

    private final BaseUseCaseHandler mUseCaseHandler;

    public ProfileNotLoggedPresenter(@NonNull BaseUseCaseHandler useCaseHandler,
                                     @NonNull ProfileNotLoggedContract.View profileNotLoggedView,
                                     @NonNull NavigationManager navigationManager) {
        mUseCaseHandler = checkNotNull(useCaseHandler, "UseCaseHandler cannot be null!");
        mView = checkNotNull(profileNotLoggedView, "ProfileNotLoggedView cannot be nulL!");

        mView.setPresenter(this);
        mView.setNavigationManager(checkNotNull(navigationManager, "NavigationManager cannot be null!"));
    }

    @Override
    public void signIn() {
        mView.showSignInUI();
    }

    @Override
    public void start() {

    }

    @Override
    public void resume() {

    }
}
