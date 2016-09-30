package com.taqtile.tq1demo.drawer.presentation.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.taqtile.android.cleanbase.presentation.activity.DrawerActivity;
import br.com.taqtile.android.cleanbase.presentation.activity.DrawerManager;

import com.taqtile.tq1demo.R;
import com.taqtile.tq1demo.base.presentation.activity.TemplateDrawerActivity;
import com.taqtile.tq1demo.drawer.presentation.presenter.DrawerContract;
import com.taqtile.tq1demo.drawer.presentation.viewmodel.DrawerMenuItemViewModel;
import com.taqtile.tq1demo.navigation.NavigationManager;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 5/25/16.
 */


public class DrawerView extends FrameLayout implements DrawerContract.View,
        NavigationView.OnNavigationItemSelectedListener, DrawerActivity.DrawerStateListener {

    private DrawerContract.Presenter mPresenter;

    private Activity mContext;

    private NavigationManager mNavigationManager;

    private ArrayList<DrawerMenuItemViewModel> mMenuItems;

    private DrawerManager mDrawerManager;

    private int notificationCellPosition;

    //region ViewComponents

    private NavigationView mNavigationView;

    //endregion

    public DrawerView(Activity context) {
        this(context, null);
    }

    public DrawerView(Activity context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawerView(Activity context, AttributeSet attrs, int defaultStyle) {
        super(context, attrs, defaultStyle);
        mContext = context;
        setupView();
    }

    private void setupView() {
        LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View root = inflater.inflate(R.layout.view_drawer, this, true);
        mNavigationView = (NavigationView) findViewById(R.id.view_drawer_navigationview);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void setPresenter(@NonNull DrawerContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter, "Drawer presenter cannot be null");
        mPresenter.start();
    }

    @Override
    public void setDrawerManager(DrawerManager manager) {
        mDrawerManager = checkNotNull(manager, "DrawerManager cannot be null!");
    }

    @Override
    public void updateUnreadNotificationsCounter(int count) {
        MenuItem menuItem = mNavigationView.getMenu().getItem(notificationCellPosition);

        if (menuItem != null) {
            TextView counterTextView = (TextView) menuItem.getActionView();
            if (count > 0) {
                counterTextView.setVisibility(VISIBLE);
                counterTextView.setText(String.valueOf(count));
            } else {
                counterTextView.setVisibility(INVISIBLE);
            }
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onDrawerClosed() {
        mPresenter.drawerClosed();
    }

    @Override
    public void onResume() {
        mPresenter.resume();
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void setNavigationManager(NavigationManager navigationManager) {
        mNavigationManager = checkNotNull(navigationManager, "NavigationManager cannot be null!");
    }

    @Override
    public void displayMenuItems(ArrayList<DrawerMenuItemViewModel> items) {
        mMenuItems = items;
        Menu menu = mNavigationView.getMenu();
        menu.clear();

        for (int i = 0; i < items.size(); i++) {
            DrawerMenuItemViewModel menuItem = items.get(i);
            Drawable icon = ContextCompat.getDrawable(mContext, menuItem.getDrawable());

            if (menuItem.getTitle().equals(mContext.getString(R.string.menu_notifications_title))) {
                notificationCellPosition = i;
                menu.add(Menu.NONE, menuItem.getId(), menuItem.getIndex(), menuItem.getTitle())
                        .setIcon(icon)
                        .setCheckable(true)
                        .setActionView(R.layout.menu_counter)
                        .setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS)
                        .setChecked(menuItem.isSelected());
            } else {
                menu.add(Menu.NONE, menuItem.getId(), menuItem.getIndex(), menuItem.getTitle())
                        .setIcon(icon)
                        .setCheckable(true)
                        .setChecked(menuItem.isSelected());
            }
        }

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if (!item.isChecked()) {
            mPresenter.menuItemSelected(item.getOrder());
        }
        mDrawerManager.closeDrawer();
        return true;
    }

    @Override
    public void showUI(Class<?> activityClass) {
        Intent intent = new Intent(mContext, activityClass);
        if (activityClass.getGenericSuperclass() == TemplateDrawerActivity.class) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        }

        mContext.startActivity(intent);
        mContext.overridePendingTransition(0, 0);
    }
}
