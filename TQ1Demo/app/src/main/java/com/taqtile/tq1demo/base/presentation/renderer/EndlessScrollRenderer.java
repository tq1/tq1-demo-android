package com.taqtile.tq1demo.base.presentation.renderer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedrogomez.renderers.Renderer;

import com.taqtile.tq1demo.R;

public class EndlessScrollRenderer<T> extends Renderer<T> {

    @Override
    protected void setUpView(View rootView) {

    }

    @Override
    protected void hookListeners(View rootView) {

    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.recycler_view_loading_indicator,
                parent, false);
    }

    @Override
    public void render() {

    }
}
