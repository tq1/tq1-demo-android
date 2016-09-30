package com.taqtile.tq1demo.drawer.presentation.viewmodel;

import android.support.annotation.DrawableRes;

import com.google.auto.value.AutoValue;

/**
 * Created by taqtile on 7/7/16.
 */

@AutoValue
public abstract class DrawerMenuItemViewModel {

    public static DrawerMenuItemViewModel create(String title, int id, @DrawableRes int drawable, int index, boolean isSelected) {
        return new AutoValue_DrawerMenuItemViewModel(title, id, drawable, index, isSelected);
    }

    public abstract String getTitle();

    public abstract int getId();

    public abstract @DrawableRes int getDrawable();

    public abstract int getIndex();

    public abstract boolean isSelected();

}
