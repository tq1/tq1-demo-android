package com.taqtile.tq1demo.profilenotlogged.presentation.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;

import br.com.taqtile.android.cleanbase.presentation.presenter.BasePresenter;
import br.com.taqtile.android.cleanbase.presentation.view.LoadingHandler;
import com.taqtile.tq1demo.DrawerInjection;
import com.taqtile.tq1demo.Injection;
import com.taqtile.tq1demo.base.presentation.activity.TemplateDrawerActivity;
import com.taqtile.tq1demo.drawer.presentation.presenter.DrawerPresenter;
import com.taqtile.tq1demo.drawer.presentation.view.DrawerView;
import com.taqtile.tq1demo.navigation.NavigationHelper;
import com.taqtile.tq1demo.profilenotlogged.presentation.fragment
        .ProfileNotLoggedFragment;
import com.taqtile.tq1demo.profilenotlogged.presentation.presenter
        .ProfileNotLoggedPresenter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 7/7/16.
 */

public class ProfileNotLoggedActivity extends TemplateDrawerActivity implements LoadingHandler {

    private ProfileNotLoggedFragment mFragment;

    @Override
    public Fragment getFragment() {
        ProfileNotLoggedFragment profileNotLoggedFragment = (ProfileNotLoggedFragment)
                getSupportFragmentManager().findFragmentById(getFragmentContainerId());
        if (profileNotLoggedFragment == null) {
            profileNotLoggedFragment = ProfileNotLoggedFragment.newInstance();
        }

        mFragment = profileNotLoggedFragment;

        return mFragment;
    }

    @Override
    public BasePresenter getPresenter() {
        return new ProfileNotLoggedPresenter(
                Injection.provideUseCaseHandler(),
                mFragment,
                new NavigationHelper(this)
        );
    }

    public static void navigate(@NonNull Context context) {
        checkNotNull(context, "context can't be null!");
        Intent intent = new Intent(context, ProfileNotLoggedActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
