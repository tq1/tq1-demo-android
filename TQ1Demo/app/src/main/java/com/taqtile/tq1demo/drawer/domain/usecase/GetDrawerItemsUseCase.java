package com.taqtile.tq1demo.drawer.domain.usecase;

import java.util.ArrayList;

import br.com.taqtile.android.cleanbase.domain.BaseUseCase;
import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.model.DrawerMenuItem;
import com.taqtile.tq1demo.data.source.drawer.DrawerMenuItemsRepository;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 7/7/16.
 */

public class GetDrawerItemsUseCase extends BaseUseCase<GetDrawerItemsUseCase.RequestValues,
        GetDrawerItemsUseCase.ResponseValue> {

    private final DrawerMenuItemsRepository mMenuItemsRepository;

    public GetDrawerItemsUseCase(DrawerMenuItemsRepository drawerMenuItemsRepository) {
        mMenuItemsRepository = checkNotNull(drawerMenuItemsRepository, "MenuItemsRepository cannot be null!");
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        DataSourceResponse<ArrayList<DrawerMenuItem>> response = mMenuItemsRepository.getMenuItems();

        if (response.isSuccess()) {
            getUseCaseCallback().onSuccess(
                    new GetDrawerItemsUseCase.ResponseValue(response.getResponse()));
        } else {
            //TODO: send the error back through the callback
            getUseCaseCallback().onError();
        }
    }

    public static final class RequestValues implements BaseUseCase.RequestValues {

    }

    public static final class ResponseValue implements BaseUseCase.ResponseValue {

        private final ArrayList<DrawerMenuItem> mItems;

        public ResponseValue(ArrayList<DrawerMenuItem> menuItems) {
            mItems = checkNotNull(menuItems, "MenuItems cannot be null!");
        }

        public ArrayList<DrawerMenuItem> getMenuItems() {
            return mItems;
        }

    }

}
