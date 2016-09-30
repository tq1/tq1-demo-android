package com.taqtile.tq1demo.navigation;

import java.util.ArrayList;

import br.com.taqtile.android.cleanbase.presentation.activity.NavigationResultListener;
import br.com.taqtile.android.cleanbase.presentation.view.BaseNavigationManager;
import com.taqtile.tq1demo.data.model.FilterCategory;

/**
 * Created by taqtile on 7/5/16.
 */

public interface NavigationManager extends BaseNavigationManager{

    void showFiltersUI(ArrayList<FilterCategory> activeFilters, NavigationResultListener listener);

    void showSignInUI();

    void showProfileUI();

    void showNotificationDetailsUI(String pushId);

}
