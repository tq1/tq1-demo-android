package com.taqtile.tq1demo.triggerlist.presentation.activity;

import android.content.Context;
import android.content.Intent;

import com.taqtile.tq1demo.Injection;
import com.taqtile.tq1demo.base.presentation.activity.TemplateDrawerActivity;
import com.taqtile.tq1demo.navigation.NavigationHelper;
import com.taqtile.tq1demo.triggerlist.presentation.fragment.TriggerListFragment;
import com.taqtile.tq1demo.triggerlist.presentation.presenter.TriggerListPresenter;

import br.com.taqtile.android.cleanbase.presentation.presenter.BasePresenter;
import br.com.taqtile.android.cleanbase.presentation.view.LoadingHandler;

/**
 * Created by taqtile on 7/26/16.
 */
public class TriggerListActivity extends TemplateDrawerActivity implements LoadingHandler {

    private TriggerListFragment mFragment;

    @Override
    public TriggerListFragment getFragment() {
        TriggerListFragment triggerListFragment = (TriggerListFragment) getSupportFragmentManager()
                .findFragmentById(getFragmentContainerId());
        if (triggerListFragment == null) {
            triggerListFragment = TriggerListFragment.newInstance();
        }

        mFragment = triggerListFragment;

        return mFragment;
    }

    @Override
    public BasePresenter getPresenter() {
        return new TriggerListPresenter(
                Injection.provideUseCaseHandler(),
                mFragment,
                Injection.provideListTriggersUseCase(getApplicationContext()),
                Injection.provideTriggerToTriggerListItemViewModelMapper(),
                new NavigationHelper(this));
    }

    public static void navigate(Context context) {
        Intent intent = new Intent(context, TriggerListActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
