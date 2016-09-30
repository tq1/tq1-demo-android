package com.taqtile.tq1demo.data.source.drawer;

import java.util.ArrayList;

import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.model.DrawerMenuItem;

/**
 * Created by taqtile on 7/7/16.
 */

public interface DrawerMenuItemsDataSource {

    DataSourceResponse<ArrayList<DrawerMenuItem>> getMenuItems();

}
