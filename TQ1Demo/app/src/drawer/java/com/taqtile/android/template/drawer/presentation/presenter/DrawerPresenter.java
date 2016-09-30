package com.taqtile.tq1demo.drawer.presentation.presenter;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import br.com.taqtile.android.cleanbase.domain.BaseUseCase;
import br.com.taqtile.android.cleanbase.domain.BaseUseCaseHandler;
import br.com.taqtile.android.cleanbase.presentation.activity.DrawerManager;
import com.taqtile.tq1demo.R;
import com.taqtile.tq1demo.data.model.DrawerMenuItem;
import com.taqtile.tq1demo.drawer.domain.usecase.GetDrawerItemsUseCase;
import com.taqtile.tq1demo.drawer.presentation.viewmodel.DrawerMenuItemViewModel;
import com.taqtile.tq1demo.drawer.presentation.viewmodel.mapper.DrawerMenuItemToDrawerMenuItemViewModelMapper;
import com.taqtile.tq1demo.navigation.NavigationManager;
import com.taqtile.tq1demo.signin.presentation.activity.SignInActivity;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 5/25/16.
 */

public class DrawerPresenter implements DrawerContract.Presenter {

    private final DrawerContract.View mView;

    private final BaseUseCaseHandler mUseCaseHandler;

    private final GetDrawerItemsUseCase mGetDrawerItemsUseCase;

    private final DrawerMenuItemToDrawerMenuItemViewModelMapper mMapper;

    private ArrayList<DrawerMenuItem> mMenuItems;

    private int mSelectedMenuItem;

    private boolean mShouldNavigate = false;

    public DrawerPresenter(@NonNull BaseUseCaseHandler useCaseHandler,
                           @NonNull DrawerContract.View drawerView,
                           @NonNull GetDrawerItemsUseCase getDrawerItemsUseCase,
                           @NonNull DrawerMenuItemToDrawerMenuItemViewModelMapper
                                   drawerMenuItemMapper,
                           @NonNull NavigationManager navigationManager,
                           @NonNull DrawerManager drawerManager) {
        mUseCaseHandler = checkNotNull(useCaseHandler, "Use case handler cannot be null");
        mView = checkNotNull(drawerView, "Drawer view cannot be null");
        mGetDrawerItemsUseCase = checkNotNull(getDrawerItemsUseCase,
                "GetDrawerMenuItems use case cannot be null!");
        mMapper = checkNotNull(drawerMenuItemMapper, "Mapper cannot be null!");

        mView.setPresenter(this);
        mView.setNavigationManager(checkNotNull(navigationManager,
                "NavigationManager cannot be null!"));
        mView.setDrawerManager(checkNotNull(drawerManager, "DrawerManager cannot be null!"));
    }

    @Override
    public void menuItemSelected(int index) {
        mSelectedMenuItem = index;
        mShouldNavigate = true;
    }

    @Override
    public void drawerClosed() {
        if (mShouldNavigate) {
            mView.showUI(mMenuItems.get(mSelectedMenuItem).getActivityClass());
            mShouldNavigate = false;
        }
    }

    public void start() {
        createMenuItemsList();
    }

    @Override
    public void resume() {
        refreshMenuItemList();
    }

    private void createMenuItemsList() {
        mUseCaseHandler.execute(mGetDrawerItemsUseCase,
                new GetDrawerItemsUseCase.RequestValues(),
                new BaseUseCase.UseCaseCallback<GetDrawerItemsUseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(GetDrawerItemsUseCase.ResponseValue responseValue) {
                        mMenuItems = responseValue.getMenuItems();
                        ArrayList<DrawerMenuItemViewModel> mappedItems =
                                mMapper.mapDrawerMenuItemListToDrawerMenuItemViewModelList(
                                        mMenuItems);
                        mView.displayMenuItems(mappedItems);
                    }

                    @Override
                    public void onError() {
                        //TODO: Handle error
                    }
                });
    }

    private void refreshMenuItemList() {
        if (mMenuItems != null && mView != null) {
            ArrayList<DrawerMenuItemViewModel> mappedItems =
                    mMapper.mapDrawerMenuItemListToDrawerMenuItemViewModelList(mMenuItems);
            mView.displayMenuItems(mappedItems);
        }
    }
}
