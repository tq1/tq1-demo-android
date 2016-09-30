package com.taqtile.tq1demo.geofences.presentation.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.taqtile.tq1demo.Injection;
import com.taqtile.tq1demo.base.presentation.activity.TemplateDrawerActivity;
import com.taqtile.tq1demo.geofences.presentation.fragment.GeoFencesFragment;
import com.taqtile.tq1demo.geofences.presentation.presenter.GeoFencesPresenter;

import br.com.taqtile.android.cleanbase.presentation.presenter.BasePresenter;
import br.com.taqtile.android.cleanbase.presentation.view.LoadingHandler;

/**
 * Created by taqtile on 7/29/16.
 */
public class GeoFencesActivity extends TemplateDrawerActivity implements LoadingHandler {

    private GeoFencesFragment mFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Fragment getFragment() {
        GeoFencesFragment geoFencesFragment = (GeoFencesFragment) getSupportFragmentManager()
                .findFragmentById(getFragmentContainerId());
        if(geoFencesFragment == null) {
            geoFencesFragment = GeoFencesFragment.newInstance();
        }

        mFragment = geoFencesFragment;

        return mFragment;
    }

    @Override
    public BasePresenter getPresenter() {
        return new GeoFencesPresenter(
                Injection.provideUseCaseHandler(),
                mFragment
        );
    }

    public static void navigate(Context context) {
        Intent intent = new Intent(context, GeoFencesActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onBackPressed() {
        if (!mFragment.isVisible()) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(getFragmentContainerId(), mFragment);
            fragmentTransaction.commit();
        } else {
            super.onBackPressed();
        }
    }

}
