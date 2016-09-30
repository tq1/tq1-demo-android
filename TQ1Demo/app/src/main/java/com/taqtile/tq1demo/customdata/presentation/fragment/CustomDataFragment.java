package com.taqtile.tq1demo.customdata.presentation.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.pedrogomez.renderers.ListAdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.RendererBuilder;
import com.taqtile.tq1demo.R;
import com.taqtile.tq1demo.customdata.presentation.presenter.CustomDataContract;
import com.taqtile.tq1demo.customdata.presentation.renderer.CustomDataRenderer;
import com.taqtile.tq1demo.customdata.presentation.viewmodel.CustomDataListItemViewModel;
import com.taqtile.tq1demo.navigation.NavigationManager;
import com.taqtile.tq1demo.util.KeyboardHelper;

import java.util.ArrayList;

import br.com.taqtile.android.cleanbase.presentation.view.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 7/29/16.
 */
public class CustomDataFragment extends BaseFragment
        implements CustomDataContract.View {

    private CustomDataContract.Presenter mPresenter;
    private RVRendererAdapter<CustomDataListItemViewModel> mCustomDataAdapter;
    private ListAdapteeCollection<CustomDataListItemViewModel> mCustomDataList;

    private NavigationManager mNavigationManager;

    //region ViewComponents

    @BindView(R.id.custom_data_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.custom_data_send_data_button)
    Button mSendDataButton;

    @BindView(R.id.custom_data_add_data_button)
    Button mAddDataButton;

    //endregion

    public static CustomDataFragment newInstance() {
        return new CustomDataFragment();
    }

    public CustomDataFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_custom_data, container, false);
        ButterKnife.bind(this, root);
        setupRecyclerView();
        setupButtons();
        return root;
    }

    @Override
    public void setPresenter(@NonNull CustomDataContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public boolean refresh() {
        if (mRecyclerView.computeVerticalScrollOffset() > 0) {
            mRecyclerView.getLayoutManager().smoothScrollToPosition(mRecyclerView, null, 0);
            return true;
        }

        return false;
    }

    @Override
    public void willAppear() {
    }

    @Override
    public void willDisappear() {
    }

    @Override
    public void setNavigationManager(NavigationManager navigationManager) {
        this.mNavigationManager = navigationManager;
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

    private void setupRecyclerView() {
        mCustomDataList = new ListAdapteeCollection<>();
        RendererBuilder<CustomDataListItemViewModel> rendererBuilder = new RendererBuilder<>
                (new CustomDataRenderer(mPresenter));
        mCustomDataAdapter = new RVRendererAdapter<>(rendererBuilder, mCustomDataList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setAdapter(mCustomDataAdapter);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void setupButtons() {
        mSendDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSendDataButton.setEnabled(false);
                mPresenter.sendCustomData(mCustomDataList);
            }
        });

        mAddDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.addCustomDataButtonClicked();

            }
        });
    }

    @Override
    public void showCustomDataList(ArrayList<CustomDataListItemViewModel> customDataListItemViewModelArrayList) {
        mCustomDataList.clear();
        mCustomDataList.addAll(customDataListItemViewModelArrayList);
        mCustomDataAdapter.notifyDataSetChanged();
    }

    @Override
    public void showSendDataSuccessMessage() {
        showSnackBar(getString(R.string.custom_data_delete_data_success_message));
    }

    @Override
    public void setSendDataButtonEnabled() {
        mSendDataButton.setEnabled(true);
    }

    @Override
    public void showSendDataErrorMessage() {
        showSnackBar(getString(R.string.custom_data_send_data_error_message));
    }

    @Override
    public void showAddDataErrorMessage() {
        showSnackBar(getString(R.string.custom_data_add_data_error_message));
    }

    @Override
    public void showDeleteDataErrorMessage() {
        showSnackBar(getString(R.string.custom_data_delete_data_error_message));
    }

    @Override
    public void showAddCustomDataDialog() {
        final Activity activity = getActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(getString(R.string.custom_data_add_data_dialog_message));

        LayoutInflater factory = LayoutInflater.from(activity);
        View dialogCustomView = factory.inflate(R.layout.dialog_custom_data, null);

        builder.setView(dialogCustomView);

        final EditText keyEditText = (EditText) dialogCustomView.findViewById(R.id.custom_data_dialog_key_edit_text);
        final EditText valueEditText = (EditText) dialogCustomView.findViewById(R.id.custom_data_dialog_value_edit_text);

        // Creating the positive button
        builder.setPositiveButton(getString(R.string.custom_data_add_data_dialog_positive_button_text),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.addCustomData(keyEditText.getText().toString(), valueEditText.getText().toString());
                        KeyboardHelper.hideKeyboard(activity);
                    }
                });

        // Creating the negative button
        builder.setNegativeButton(getString(R.string.custom_data_add_data_dialog_negative_button_text),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        KeyboardHelper.hideKeyboard(activity);
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
    public void showEditCustomDataDialog(final String key, String value) {
        final Activity activity = getActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(getString(R.string.custom_data_edit_data_dialog_message));

        LayoutInflater factory = LayoutInflater.from(activity);
        View dialogCustomView = factory.inflate(R.layout.dialog_custom_data, null);

        builder.setView(dialogCustomView);

        final EditText keyEditText = (EditText) dialogCustomView.findViewById(R.id.custom_data_dialog_key_edit_text);
        final EditText valueEditText = (EditText) dialogCustomView.findViewById(R.id.custom_data_dialog_value_edit_text);

        keyEditText.setText(key);
        valueEditText.setText(value);

        keyEditText.setSelection(key.length());
        valueEditText.setSelection(value.length());

        // Creating the positive button
        builder.setPositiveButton(getString(R.string.custom_data_edit_data_dialog_positive_button_text),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.updateCustomData(key, keyEditText.getText().toString(), valueEditText.getText().toString());
                        KeyboardHelper.hideKeyboard(activity);
                    }
                });

        // Creating the negative button
        builder.setNegativeButton(getString(R.string.custom_data_edit_data_dialog_negative_button_text),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        KeyboardHelper.hideKeyboard(activity);
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
    public void showDeleteCustomDataDialog(final String key, final String value) {
        final Activity activity = getActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(getString(R.string.custom_data_delete_data_dialog_message, key, value));

        // Creating the positive button
        builder.setPositiveButton(getString(R.string.custom_data_delete_data_dialog_positive_button_text),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.deleteCustomData(key, value);
                        KeyboardHelper.hideKeyboard(activity);
                    }
                });

        // Creating the negative button
        builder.setNegativeButton(getString(R.string.custom_data_delete_data_dialog_negative_button_text),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        KeyboardHelper.hideKeyboard(activity);
                    }
                });

        // Create the dialog
        AlertDialog dialog = builder.create();

        // Setting an input mode, so that keyboard shows up
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        // Show the dialog
        dialog.show();
    }

    private void showSnackBar(String message) {
        // We can pass any view element as the first parameter
        Snackbar snackBar = Snackbar.make(mAddDataButton, message, Snackbar.LENGTH_SHORT);
        snackBar.show();
    }
}
