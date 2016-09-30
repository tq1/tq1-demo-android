package com.taqtile.tq1demo.triggerlist.presentation.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedrogomez.renderers.ListAdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.RendererBuilder;
import com.taqtile.tq1demo.R;
import com.taqtile.tq1demo.base.presentation.renderer.EndlessScrollRendererBuilder;
import com.taqtile.tq1demo.navigation.NavigationManager;
import com.taqtile.tq1demo.triggerlist.presentation.presenter.TriggerListContract;
import com.taqtile.tq1demo.triggerlist.presentation.renderer.TriggerListRenderer;
import com.taqtile.tq1demo.triggerlist.presentation.viewmodel.TriggerListItemViewModel;

import java.util.ArrayList;

import br.com.taqtile.android.cleanbase.presentation.view.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 7/26/16.
 */
public class TriggerListFragment extends BaseFragment implements TriggerListContract.View {

    private TriggerListContract.Presenter mPresenter;
    private RVRendererAdapter<TriggerListItemViewModel> mTriggersListAdapter;
    private ListAdapteeCollection<TriggerListItemViewModel> mTriggersList;

    //region ViewComponents

    @BindView(R.id.trigger_list_recycler_view)
    RecyclerView mRecyclerView;

    //endregion

    public static TriggerListFragment newInstance() {
        return new TriggerListFragment();
    }

    public TriggerListFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_trigger_list, container, false);
        ButterKnife.bind(this, root);
        setupRecyclerView();
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
    public boolean refresh() {
        if (mRecyclerView.computeVerticalScrollOffset() > 0) {
            mRecyclerView.getLayoutManager().smoothScrollToPosition(mRecyclerView, null, 0);
            return true;
        }

        return false;
    }

    @Override
    public void willAppear() {

    }

    @Override
    public void willDisappear() {

    }



    @Override
    public void showTriggersList(ArrayList<TriggerListItemViewModel> triggers) {
        mTriggersList.addAll(triggers);
        mTriggersListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showPlaceholder() {

    }

    @Override
    public void showTriggersListError(String error) {

    }

    @Override
    public void setPresenter(TriggerListContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setNavigationManager(NavigationManager navigationManager) {

    }

    private void setupRecyclerView() {
        mTriggersList = new ListAdapteeCollection<>();
        RendererBuilder<TriggerListItemViewModel> rendererBuilder = new EndlessScrollRendererBuilder<>
                (new TriggerListRenderer());
        mTriggersListAdapter = new RVRendererAdapter<>(rendererBuilder, mTriggersList);

        mRecyclerView.setAdapter(mTriggersListAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


}
