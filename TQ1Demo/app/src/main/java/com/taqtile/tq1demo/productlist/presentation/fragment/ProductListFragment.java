package com.taqtile.tq1demo.productlist.presentation.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.ArrayList;

import br.com.taqtile.android.cleanbase.presentation.activity.NavigationResultListener;
import br.com.taqtile.android.cleanbase.presentation.view.BaseFragment;
import com.taqtile.tq1demo.R;
import com.taqtile.tq1demo.base.presentation.renderer.EndlessRecyclerViewScrollListener;
import com.taqtile.tq1demo.base.presentation.renderer.EndlessScrollRendererBuilder;
import com.taqtile.tq1demo.base.presentation.renderer.EndlessScrollRendererCollection;
import com.taqtile.tq1demo.data.model.FilterCategory;
import com.taqtile.tq1demo.filter.presentation.fragment.FilterFragment;
import com.taqtile.tq1demo.navigation.NavigationManager;
import com.taqtile.tq1demo.productlist.presentation.presenter.ProductListContract;
import com.taqtile.tq1demo.productlist.presentation.viewmodel.ProductListItemViewModel;
import com.taqtile.tq1demo.productlist.presentation.renderer.ProductListRenderer;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 5/25/16.
 */

public class ProductListFragment extends BaseFragment
        implements ProductListContract.View, NavigationResultListener {

    private ProductListContract.Presenter mPresenter;
    private RVRendererAdapter<ProductListItemViewModel> mProductListAdapter;
    private EndlessScrollRendererCollection<ProductListItemViewModel> mProductList;
    private NavigationManager mNavigationManager;

    //region ViewComponents

    @BindView(R.id.product_list_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.fragment_productlist_filters_button)
    Button mFiltersButton;

    @BindView(R.id.fragment_productlist_appbar)
    AppBarLayout mAppBar;

    //endregion

    public static ProductListFragment newInstance() {
        return new ProductListFragment();
    }

    public ProductListFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_productlist, container, false);
        ButterKnife.bind(this, root);
        setupRecyclerView();
        setupButtons();
        return root;
    }

    @Override
    public void setPresenter(@NonNull ProductListContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public boolean refresh() {
        if (mRecyclerView.computeVerticalScrollOffset() > 0) {
            mAppBar.setExpanded(true);
            mRecyclerView.getLayoutManager().smoothScrollToPosition(mRecyclerView, null, 0);
            return true;
        }

        return false;
    }

    @Override
    public void willAppear() {
        getFABHelper().showFAB();
    }

    @Override
    public void willDisappear() {
        getFABHelper().hideFAB();
    }

    @Override
    public void setNavigationManager(NavigationManager navigationManager) {
        this.mNavigationManager = navigationManager;
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
    public void showProductList(ArrayList<ProductListItemViewModel> products) {
        mProductList.addAll(products);
        mProductListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCartUI() {

    }

    @Override
    public void showSearchUI() {

    }

    @Override
    public void showFilterUI(ArrayList<FilterCategory> activeFilters) {
        mNavigationManager.showFiltersUI(activeFilters, this);
    }

    @Override
    public void showSortUI() {

    }

    @Override
    public void showProductDetailsUI(ProductListItemViewModel product) {

    }

    @Override
    public void showPlaceholder() {

    }

    @Override
    public void showProductListError(String error) {

    }

    @Override
    public void showNextPage(ArrayList<ProductListItemViewModel> products) {
        mProductList.addAll(products);
        mProductList.setShowLoadMore(false);
        mProductListAdapter.notifyDataSetChanged();
    }

    private void setupRecyclerView() {
        mProductList = new EndlessScrollRendererCollection<>();
        RendererBuilder<ProductListItemViewModel> rendererBuilder = new EndlessScrollRendererBuilder<>
                (new ProductListRenderer());
        mProductListAdapter = new RVRendererAdapter<>(rendererBuilder, mProductList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setAdapter(mProductListAdapter);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setOnScrollListener(
                new EndlessRecyclerViewScrollListener(linearLayoutManager) {

                    @Override
                    public void onLoadMore(int page, int totalItemsCount) {
                        mProductList.setShowLoadMore(true);
                        mPresenter.reachedEndOfList();
                    }
                });
    }

    private void setupButtons() {
        mFiltersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.selectFilter();
            }
        });
    }

    @Override
    public void OnNavigationResult(Bundle bundle) {
        ArrayList<FilterCategory> filters =
                bundle.getParcelableArrayList(FilterFragment.bundleArrayKey);
        mPresenter.filter(filters);
    }

}
