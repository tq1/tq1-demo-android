package com.taqtile.tq1demo.notificationdetails.presentation.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.taqtile.tq1demo.R;
import com.taqtile.tq1demo.navigation.NavigationManager;
import com.taqtile.tq1demo.notificationdetails.presentation.presenter.NotificationDetailsContract;
import com.taqtile.tq1demo.util.KeyboardHelper;

import br.com.taqtile.android.cleanbase.presentation.view.BaseFragment;
import br.com.taqtile.android.textviews.CustomTextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import taqtile.android.sdk.push.TQInboxMessage;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 7/27/16.
 */
public class NotificationDetailsFragment extends BaseFragment implements NotificationDetailsContract.View, View.OnClickListener {

    private NotificationDetailsContract.Presenter mPresenter;

    private NavigationManager mNavigationManager;

    //region ViewComponents

    @BindView(R.id.notification_title_text_view)
    CustomTextView mNotificationTitle;

    @BindView(R.id.notification_message_text_view)
    CustomTextView mNotificationMessage;

    @BindView(R.id.notification_data_sent_text_view)
    CustomTextView mNotificationDataSent;

    @BindView(R.id.notification_data_received_text_view)
    CustomTextView mNotificationDataReceived;

    @BindView(R.id.notification_status_sent_text_view)
    CustomTextView mNotificationStatusSent;

    @BindView(R.id.notification_custom_action_text_view)
    CustomTextView mNotificationCustomAction;

    @BindView(R.id.notification_content_type_text_view)
    CustomTextView mNotificationContentType;

    @BindView(R.id.notification_content_text_view)
    CustomTextView mNotificationContent;

    @BindView(R.id.notification_open_content_button)
    Button mOpenContentButton;

    @BindView(R.id.notification_send_custom_status_button)
    Button mSendCustomStatusButton;

    @BindView(R.id.notification_mark_as_read_unread_button)
    Button mMarkAsReadUnreadButton;

    //endregion

    public static NotificationDetailsFragment newInstance() {
        return new NotificationDetailsFragment();
    }

    public NotificationDetailsFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notification_details, container, false);
        ButterKnife.bind(this, root);
        setupButtons();
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.start();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.resume();
    }

    @Override
    public boolean refresh() {
        return false;
    }

    @Override
    public void willAppear() {

    }

    @Override
    public void willDisappear() {

    }

    @Override
    public void showNotificationDetails(TQInboxMessage notification) {
        mNotificationTitle.setText(notification.getAlert());
        mNotificationMessage.setText(notification.getAlert());
        mNotificationDataSent.setText(notification.getScheduled());
        mNotificationDataReceived.setText(notification.getTimestamp());
        mNotificationStatusSent.setText(notification.getCustomSentStatus());
        mNotificationCustomAction.setText(notification.getCustomAction());
        mNotificationContentType.setText(notification.getType().name());
        mNotificationContent.setText(notification.getContent());
    }

    @Override
    public void showMarkAsReadButtonText() {
        mMarkAsReadUnreadButton.setText(getString(R.string.notification_details_mark_as_read_button_text));
    }

    @Override
    public void showMarkAsUnreadButtonText() {
        mMarkAsReadUnreadButton.setText(getString(R.string.notification_details_mark_as_unread_button_text));
    }

    @Override
    public void showSendCustomStatusDialog() {
        final Activity activity = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(getString(R.string.notification_send_custom_status_dialog_message));

        // Creating an EditText to display in the dialog
        final EditText customStatusEditText = new EditText(getActivity());
        builder.setView(customStatusEditText);

        // Creating the positive button
        builder.setPositiveButton(getString(R.string.notification_send_custom_status_dialog_positive_text),
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.sendCustomStatus(customStatusEditText.getText().toString());
                KeyboardHelper.hideKeyboard(getActivity());
            }
        });

        // Creating the negative button
        builder.setNegativeButton(getString(R.string.notification_send_custom_status_dialog_negative_text),
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                KeyboardHelper.hideKeyboard(getActivity());
            }
        });

        // Create the dialog
        AlertDialog dialog = builder.create();

        // Setting an input mode, so that keyboard shows up
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        // Show the dialog
        dialog.show();
    }

    @Override
    public void showContentView() {

    }

    @Override
    public void showPlaceholder() {

    }

    @Override
    public void setPresenter(NotificationDetailsContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setNavigationManager(NavigationManager navigationManager) {
        this.mNavigationManager = navigationManager;
    }

    private void setupButtons() {
        mOpenContentButton.setOnClickListener(this);
        mSendCustomStatusButton.setOnClickListener(this);
        mMarkAsReadUnreadButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mOpenContentButton) {
            mPresenter.openNotificationContent();
        } else if (v == mSendCustomStatusButton) {
            mPresenter.customStatusButtonClicked();
        } else if (v == mMarkAsReadUnreadButton) {
            mPresenter.markNotificationAsReadOrUnread();
        }
    }
}