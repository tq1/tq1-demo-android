package com.taqtile.tq1demo.notificationlist.presentation.renderer;

import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedrogomez.renderers.Renderer;
import com.taqtile.tq1demo.R;
import com.taqtile.tq1demo.notificationlist.presentation.presenter.NotificationListContract;
import com.taqtile.tq1demo.notificationlist.presentation.viewmodel.NotificationListItemViewModel;

import br.com.taqtile.android.textviews.CustomTextView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by taqtile on 7/26/16.
 */
public class NotificationListRenderer extends Renderer<NotificationListItemViewModel> implements View.OnClickListener {

    NotificationListContract.Presenter mPresenter;

    // region views
    @BindView(R.id.notification_list_date)
    CustomTextView mNotificationReceivedDate;

    @BindView(R.id.notification_list_cell_title)
    CustomTextView mNotificationTitle;

    @BindView(R.id.notification_list_cell_description)
    CustomTextView mNotificationMessage;

    // end region

    public NotificationListRenderer(NotificationListContract.Presenter notificationListPresenter) {
        mPresenter = notificationListPresenter;
    }

    @Override
    protected void setUpView(View rootView) {
        rootView.setOnClickListener(this);
    }

    @Override
    protected void hookListeners(View rootView) {
        /*
         * Empty implementation substituted with the usage of ButterKnife library by Jake Wharton.
         */
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        View inflatedView = inflater.inflate(R.layout.renderer_notificationlist, parent, false);
        ButterKnife.bind(this, inflatedView);
        return inflatedView;
    }

    @Override
    public void render() {
        NotificationListItemViewModel model = getContent();
        renderNotificationInfo(model);
    }

    private void renderNotificationInfo(NotificationListItemViewModel model) {
        mNotificationTitle.setText(model.getTitle());
        mNotificationMessage.setText(model.getMessage());
        mNotificationReceivedDate.setText(model.getReceivedDate());

        if (model.isPendingRead()) {
            mNotificationTitle.setTypeface(Typeface.DEFAULT_BOLD);
            mNotificationReceivedDate.setTypeface(mNotificationTitle.getTypeface(), R.style.TextElement_Date);
            mNotificationReceivedDate.setTextColor(ContextCompat.getColor(getContext(), R.color.color_brand_blue));
        } else {
            mNotificationTitle.setTypeface(Typeface.DEFAULT);
            mNotificationReceivedDate.setTypeface(mNotificationTitle.getTypeface(), R.style.TextElement_Date_Bold);
            mNotificationReceivedDate.setTypeface(mNotificationTitle.getTypeface(), R.style.TextElement_Caption);
        }
    }

    @Override
    public void onClick(View v) {
        NotificationListItemViewModel model = getContent();
        mPresenter.selectNotificationDetails(model.getPushId());
    }
}
