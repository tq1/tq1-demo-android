package com.taqtile.tq1demo.geofences.presentation.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.taqtile.tq1demo.R;
import com.taqtile.tq1demo.geofences.presentation.presenter.GeoFencesContract;
import com.taqtile.tq1demo.navigation.NavigationManager;
import com.taqtile.tqgeolocationsdk.fragments.DebugMapFragment;
import com.taqtile.tqgeolocationsdk.fragments.LogFragment;

import br.com.taqtile.android.cleanbase.presentation.view.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by taqtile on 7/29/16.
 */
public class GeoFencesFragment extends BaseFragment implements GeoFencesContract.View, View.OnClickListener {

    private GeoFencesContract.Presenter mPresenter;

    //region ViewComponents

    @BindView(R.id.geo_fences_map_cell_layout)
    RelativeLayout mMapCell;
    @BindView(R.id.geo_fences_log_cell_layout)
    RelativeLayout mLogCell;

    //endregion

    public static GeoFencesFragment newInstance() {
        return new GeoFencesFragment();
    }

    public GeoFencesFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_geo_fences, container, false);
        ButterKnife.bind(this, root);
        setupCells();
        return root;
    }

    private void setupCells() {
        mMapCell.setOnClickListener(this);
        mLogCell.setOnClickListener(this);
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
    public void setPresenter(GeoFencesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setNavigationManager(NavigationManager navigationManager) {

    }

    @Override
    public void onClick(View v) {
        if (v == mMapCell) {
            mPresenter.mapCellClicked();
        } else if (v == mLogCell) {
            mPresenter.logCellClicked();
        }
    }

    @Override
    public void showMapUI() {
        DebugMapFragment fragmentMap = new DebugMapFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(((ViewGroup)getView().getParent()).getId(), fragmentMap);
        fragmentTransaction.commit();
    }

    @Override
    public void showLogUI() {
        LogFragment fragmentLog = new LogFragment();
        FragmentManager fragmentLogManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentLogManager.beginTransaction();
        fragmentTransaction.replace(((ViewGroup)getView().getParent()).getId(), fragmentLog);
        fragmentTransaction.commit();
    }
}
