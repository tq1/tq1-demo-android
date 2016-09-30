package com.taqtile.tq1demo.notificationdetails.presentation.activity;

import android.content.Context;
import android.content.Intent;

import com.taqtile.tq1demo.Injection;
import com.taqtile.tq1demo.base.presentation.activity.TemplateDrawerActivity;
import com.taqtile.tq1demo.navigation.NavigationHelper;
import com.taqtile.tq1demo.notificationdetails.presentation.fragment.NotificationDetailsFragment;
import com.taqtile.tq1demo.notificationdetails.presentation.presenter.NotificationDetailsPresenter;

import br.com.taqtile.android.cleanbase.presentation.presenter.BasePresenter;
import br.com.taqtile.android.cleanbase.presentation.view.LoadingHandler;

/**
 * Created by taqtile on 7/27/16.
 */
public class NotificationDetailsActivity extends TemplateDrawerActivity implements LoadingHandler {

    public static final String TAG_PUSH_ID = "pushId";

    private NotificationDetailsFragment mFragment;

    @Override
    public NotificationDetailsFragment getFragment() {
        NotificationDetailsFragment notificationDetailsFragment = (NotificationDetailsFragment) getSupportFragmentManager()
                .findFragmentById(getFragmentContainerId());
        if (notificationDetailsFragment == null) {
            notificationDetailsFragment = NotificationDetailsFragment.newInstance();
        }

        mFragment = notificationDetailsFragment;

        return mFragment;
    }

    @Override
    public BasePresenter getPresenter() {
        String pushId = "";
        if (getIntent().getStringExtra(TAG_PUSH_ID) != null) {
            pushId = getIntent().getStringExtra(TAG_PUSH_ID);
        }
        return new NotificationDetailsPresenter(
                Injection.provideUseCaseHandler(),
                mFragment,
                Injection.provideRequestNotificationDetailsUseCase(getApplicationContext()),
                Injection.provideChangeNotificationReadStatusUseCase(getApplicationContext()),
                Injection.provideSendCustomStatusUseCase(getApplicationContext()),
                new NavigationHelper(this),
                pushId);
    }

    public static void navigate(Context context) {
        Intent intent = new Intent(context, NotificationDetailsActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
