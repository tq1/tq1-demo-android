package com.taqtile.tq1demo.customdata.presentation.presenter;

import com.taqtile.tq1demo.customdata.presentation.viewmodel.CustomDataListItemViewModel;
import com.taqtile.tq1demo.navigation.NavigationManager;

import java.util.ArrayList;
import java.util.Map;

import br.com.taqtile.android.cleanbase.presentation.presenter.BasePresenter;
import br.com.taqtile.android.cleanbase.presentation.view.BaseView;

/**
 * Created by taqtile on 7/29/16.
 */
public interface CustomDataContract {
    interface View extends BaseView<Presenter, NavigationManager> {

        void showCustomDataList(ArrayList<CustomDataListItemViewModel> customDataListItemViewModelArrayList);

        void showSendDataSuccessMessage();

        void setSendDataButtonEnabled();

        void showSendDataErrorMessage();

        void showAddDataErrorMessage();

        void showAddCustomDataDialog();

        void showDeleteDataErrorMessage();

        void showEditCustomDataDialog(String key, String value);

        void showDeleteCustomDataDialog(String key, String value);

    }

    interface Presenter extends BasePresenter {

        void sendCustomData(ArrayList<CustomDataListItemViewModel> customDataListItemViewModelArrayList);

        void addCustomData(String key, String value);

        void updateCustomData(String oldKey, String newKey, String value);

        void deleteCustomData(String key, String value);

        void addCustomDataButtonClicked();

        void customDataCellClicked(String key, String value);

        void customDataCellLongPressed(String key, String value);

    }

}

