package com.taqtile.tq1demo.data.model;

import com.google.auto.value.AutoValue;

/**
 * Created by taqtile on 7/7/16.
 */

@AutoValue
public abstract class DrawerMenuItem {

    public static DrawerMenuItem create(String title, Class<?> activityClass) {
        return new AutoValue_DrawerMenuItem(title, activityClass);
    }

    public abstract String getTitle();

    public abstract Class<?> getActivityClass();
}
