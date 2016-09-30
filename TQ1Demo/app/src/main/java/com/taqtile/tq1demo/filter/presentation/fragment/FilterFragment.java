package com.taqtile.tq1demo.filter.presentation.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import br.com.taqtile.android.cleanbase.presentation.view.BaseFragmentForResult;
import br.com.taqtile.android.recyclerviews.filtersrecyclerview.model.FilterCategoryModel;
import com.taqtile.tq1demo.R;
import com.taqtile.tq1demo.data.model.FilterCategory;
import com.taqtile.tq1demo.filter.presentation.presenter.FilterContract;
import com.taqtile.tq1demo.filter.presentation.viewmodel.FilterCategoryViewModel;
import com.taqtile.tq1demo.navigation.NavigationManager;
import com.taqtile.tq1demo.recyclerviews.filter.TemplateFilterRecyclerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 5/30/16.
 */

public class FilterFragment extends BaseFragmentForResult implements FilterContract.View {

    public static final String bundleArrayKey = "FILTER_ARRAY_BUNDLE_KEY";

    private FilterContract.Presenter mPresenter;
    private TemplateFilterRecyclerAdapter mRecycleAdapter;
    private ArrayList<FilterCategoryViewModel> mFilters;

    //region ViewComponents

    @BindView(R.id.fragment_filter_apply_button)
    Button mApplyFiltersButton;

    @BindView(R.id.fragment_filter_clear_button)
    Button mClearFiltersButton;

    @BindView(R.id.fragment_filter_recycler_view)
    RecyclerView mRecyclerView;

    //endregion

    public static FilterFragment newInstance() {
        return new FilterFragment();
    }

    public FilterFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_filter, container, false);
        ButterKnife.bind(this, root);
        setupButtons();
        return root;
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
    public void setPresenter(@NonNull FilterContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showFilterList(ArrayList<FilterCategoryViewModel> filters) {
        mFilters = filters;
        setupRecyclerView();
    }

    @Override
    public void showProductListUI(ArrayList<FilterCategory> activeFilters) {
        Bundle resultData = new Bundle();
        resultData.putParcelableArrayList(bundleArrayKey, activeFilters);
        finishWithResult(resultData);
    }

    private void setupButtons() {
        mApplyFiltersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Fix return value
                mPresenter.applyFilters(new ArrayList<FilterCategoryViewModel>());
            }
        });
        mClearFiltersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.clearFilters();
            }
        });
    }

    private void setupRecyclerView() {
        ArrayList<FilterCategoryModel> filters = new ArrayList<>();
        filters.addAll(mFilters);
        mRecycleAdapter = new TemplateFilterRecyclerAdapter(getContext(), filters);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mRecycleAdapter);
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

    }
}
