package com.taqtile.tq1demo.notificationlist.presentation.activity;

import android.content.Context;
import android.content.Intent;

import com.taqtile.tq1demo.Injection;
import com.taqtile.tq1demo.base.presentation.activity.TemplateDrawerActivity;
import com.taqtile.tq1demo.navigation.NavigationHelper;
import com.taqtile.tq1demo.notificationlist.presentation.fragment.NotificationListFragment;
import com.taqtile.tq1demo.notificationlist.presentation.presenter.NotificationListPresenter;

import br.com.taqtile.android.cleanbase.presentation.presenter.BasePresenter;
import br.com.taqtile.android.cleanbase.presentation.view.LoadingHandler;

/**
 * Created by taqtile on 7/26/16.
 */
public class NotificationListActivity extends TemplateDrawerActivity implements LoadingHandler {

    private NotificationListFragment mFragment;

    @Override
    public NotificationListFragment getFragment() {
        NotificationListFragment notificationListFragment = (NotificationListFragment) getSupportFragmentManager()
                .findFragmentById(getFragmentContainerId());
        if (notificationListFragment == null) {
            notificationListFragment = NotificationListFragment.newInstance();
        }

        mFragment = notificationListFragment;

        return mFragment;
    }

    @Override
    public BasePresenter getPresenter() {
        return new NotificationListPresenter(
                Injection.provideUseCaseHandler(),
                mFragment,
                Injection.provideListNotificationUseCase(getApplicationContext()),
                Injection.provideNotificationToNotificationListItemViewModelMapper(),
                new NavigationHelper(this));
    }

    public static void navigate(Context context) {
        Intent intent = new Intent(context, NotificationListActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
