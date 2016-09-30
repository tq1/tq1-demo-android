package com.taqtile.tq1demo.base.presentation.renderer;

import com.pedrogomez.renderers.ListAdapteeCollection;

import static com.google.common.base.Preconditions.checkNotNull;

public class EndlessScrollRendererCollection<T> extends ListAdapteeCollection<T> {

    private boolean mShowLoadMore = false;

    public void setShowLoadMore(boolean showLoadMore) {
        mShowLoadMore = showLoadMore;
    }

    public boolean isShowingLoadMore() {
        return mShowLoadMore;
    }

    @Override
    public int size() {
        int size = super.size();
        return mShowLoadMore ? size + 1 : size;
    }

    @Override public T get(int i) {
        T item = null;
        if (i < super.size()) {
            item = super.get(i);
        }
        return item;
    }

}
