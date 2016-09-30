package com.taqtile.tq1demo;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.taqtile.tq1demo.data.source.drawer.DrawerMenuItemsLocalDataSource;
import com.taqtile.tq1demo.data.source.drawer.DrawerMenuItemsRepository;
import com.taqtile.tq1demo.drawer.domain.usecase.GetDrawerItemsUseCase;
import com.taqtile.tq1demo.drawer.presentation.viewmodel.mapper.DrawerMenuItemToDrawerMenuItemViewModelMapper;

/**
 * Created by taqtile on 7/12/16.
 */

public class DrawerInjection {

    public static DrawerMenuItemToDrawerMenuItemViewModelMapper provideDrawerMenuItemToDrawerMenuItemViewModelMapper(@NonNull Context context) {
        return new DrawerMenuItemToDrawerMenuItemViewModelMapper(context);
    }

    public static GetDrawerItemsUseCase provideGetDrawerMenuItemsUseCase(@NonNull Activity activityContext) {
        return new GetDrawerItemsUseCase(provideDrawerMenuItemsRepository(activityContext));
    }

    public static DrawerMenuItemsRepository provideDrawerMenuItemsRepository(@NonNull Activity activityContext) {
        return DrawerMenuItemsRepository.getInstance(DrawerMenuItemsLocalDataSource.getInstance(activityContext));
    }

}
