package com.taqtile.tq1demo.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import br.com.taqtile.android.cleanbase.presentation.activity.BaseActivity;
import br.com.taqtile.android.cleanbase.presentation.activity.NavigationResultListener;
import com.taqtile.tq1demo.data.model.FilterCategory;
import com.taqtile.tq1demo.filter.presentation.activity.FilterActivity;
import com.taqtile.tq1demo.filter.presentation.fragment.FilterFragment;
import com.taqtile.tq1demo.notificationdetails.presentation.activity.NotificationDetailsActivity;
import com.taqtile.tq1demo.profilenotlogged.presentation.activity.ProfileNotLoggedActivity;
import com.taqtile.tq1demo.signin.presentation.activity.SignInActivity;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 7/6/16.
 */

public class NavigationHelper implements NavigationManager {

    private BaseActivity mContext;

    public NavigationHelper(@NonNull BaseActivity activityContext) {
        mContext = checkNotNull(activityContext, "ActivityContext cannot be null!");
    }


    @Override
    public void showFiltersUI(ArrayList<FilterCategory> activeFilters, NavigationResultListener listener) {
        mContext.setResultListener(listener, FilterActivity.FILTER_REQUEST_CODE);
        Intent intent = new Intent(mContext, FilterActivity.class);
        Bundle params = new Bundle();
        params.putParcelableArrayList(FilterFragment.bundleArrayKey, activeFilters);
        intent.putExtras(params);
        mContext.startActivityForResult(intent, FilterActivity.FILTER_REQUEST_CODE);
    }

    @Override
    public void showSignInUI() {
        SignInActivity.navigate(mContext);
    }

    @Override
    public void showProfileUI() {
        ProfileNotLoggedActivity.navigate(mContext);
    }

    @Override
    public void showNotificationDetailsUI(String pushId) {
        Intent intent = new Intent(mContext, NotificationDetailsActivity.class);
        intent.putExtra(NotificationDetailsActivity.TAG_PUSH_ID, pushId);
        mContext.startActivity(intent);
    }
}
