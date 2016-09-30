package com.taqtile.tq1demo.notificationlist.presentation.fragment;

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
import com.taqtile.tq1demo.navigation.NavigationManager;
import com.taqtile.tq1demo.notificationlist.presentation.presenter.NotificationListContract;
import com.taqtile.tq1demo.notificationlist.presentation.renderer.NotificationListRenderer;
import com.taqtile.tq1demo.notificationlist.presentation.viewmodel.NotificationListItemViewModel;

import java.util.ArrayList;

import br.com.taqtile.android.cleanbase.presentation.view.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 7/26/16.
 */
public class NotificationListFragment extends BaseFragment implements NotificationListContract.View {

    private NotificationListContract.Presenter mPresenter;
    private RVRendererAdapter<NotificationListItemViewModel> mNotificationsListAdapter;
    private ListAdapteeCollection<NotificationListItemViewModel> mNotificationsList;

    private NavigationManager mNavigationManager;

    //region ViewComponents

    @BindView(R.id.notification_list_recycler_view)
    RecyclerView mRecyclerView;

    //endregion

    public static NotificationListFragment newInstance() {
        return new NotificationListFragment();
    }

    public NotificationListFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notification_list, container, false);
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
    public void showNotificationList(ArrayList<NotificationListItemViewModel> notifications) {
        mNotificationsList.addAll(notifications);
        mNotificationsListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showNotificationDetailsUI(String pushId) {
        mNavigationManager.showNotificationDetailsUI(pushId);
    }

    @Override
    public void showPlaceholder() {

    }

    @Override
    public void showNotificationListError(String error) {

    }

    @Override
    public void setPresenter(NotificationListContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setNavigationManager(NavigationManager navigationManager) {
        this.mNavigationManager = navigationManager;
    }

    private void setupRecyclerView() {
        mNotificationsList = new ListAdapteeCollection<>();
        RendererBuilder<NotificationListItemViewModel> rendererBuilder = new RendererBuilder<>
                (new NotificationListRenderer(mPresenter));
        mNotificationsListAdapter = new RVRendererAdapter<>(rendererBuilder, mNotificationsList);

        mRecyclerView.setAdapter(mNotificationsListAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


}
