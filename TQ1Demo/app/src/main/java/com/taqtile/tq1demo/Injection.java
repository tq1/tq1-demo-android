package com.taqtile.tq1demo;

import android.content.Context;
import android.support.annotation.NonNull;

import com.taqtile.tq1demo.customdata.domain.usecase.SaveCustomDataUseCase;
import com.taqtile.tq1demo.customdata.domain.usecase.SendCustomDataUseCase;
import com.taqtile.tq1demo.customdata.domain.usecase.RetrieveCustomDataUseCase;
import com.taqtile.tq1demo.customdata.presentation.viewmodel.mapper.CustomDataToCustomDataListItemViewModelMapper;
import com.taqtile.tq1demo.data.source.device.DeviceInfoLocalDataSource;
import com.taqtile.tq1demo.data.source.device.DeviceInfoRepository;
import com.taqtile.tq1demo.data.source.filter.FilterFakeDataSource;
import com.taqtile.tq1demo.data.source.notifications.NotificationLocalDataSource;
import com.taqtile.tq1demo.data.source.notifications.NotificationRepository;
import com.taqtile.tq1demo.data.source.settings.SettingsLocalDataSource;
import com.taqtile.tq1demo.data.source.settings.SettingsRepository;
import com.taqtile.tq1demo.data.source.triggers.TriggerLocalDataSource;
import com.taqtile.tq1demo.data.source.triggers.TriggerRepository;
import com.taqtile.tq1demo.data.source.user.UserFakeDataSource;
import com.taqtile.tq1demo.data.source.filter.FilterRepository;
import br.com.taqtile.android.cleanbase.domain.BaseUseCaseHandler;
import com.taqtile.tq1demo.data.source.product.ProductRepository;
import com.taqtile.tq1demo.data.source.user.UserLocalDataSource;
import com.taqtile.tq1demo.data.source.user.UserRepository;
import com.taqtile.tq1demo.filter.domain.usecase.GetFiltersUseCase;
import com.taqtile.tq1demo.filter.presentation.viewmodel.mapper.FilterCategoryToFilterCategoryViewModelMapper;
import com.taqtile.tq1demo.filter.presentation.viewmodel.mapper.FilterToFilterViewModelMapper;
import com.taqtile.tq1demo.data.source.product.ProductFakeDataSource;
import com.taqtile.tq1demo.notificationdetails.domain.usecase.ChangeNotificationReadStatusUseCase;
import com.taqtile.tq1demo.notificationdetails.domain.usecase.RequestNotificationDetailsUseCase;
import com.taqtile.tq1demo.notificationdetails.domain.usecase.SendCustomStatusUseCase;
import com.taqtile.tq1demo.notificationlist.domain.usecase.ListNotificationsUseCase;
import com.taqtile.tq1demo.notificationlist.presentation.viewmodel.mapper.NotificationToNotificationListItemViewModelMapper;
import com.taqtile.tq1demo.productlist.domain.usecase.SearchProductsUseCase;
import com.taqtile.tq1demo.productlist.presentation.viewmodel.mapper.ProductToProductListItemViewModelMapper;
import com.taqtile.tq1demo.settings.domain.usecase.LoadSettingsUseCase;
import com.taqtile.tq1demo.settings.domain.usecase.SaveSettingsUseCase;
import com.taqtile.tq1demo.settings.domain.usecase.SetTQ1StateUseCase;
import com.taqtile.tq1demo.settings.domain.usecase.SetTQGStateUseCase;
import com.taqtile.tq1demo.signin.domain.usecase.ForgotUserPasswordUseCase;
import com.taqtile.tq1demo.signin.domain.usecase.SignInUseCase;
import com.taqtile.tq1demo.triggerlist.presentation.viewmodel.mapper.TriggerToTriggerListItemViewModelMapper;
import com.taqtile.tq1demo.triggerlist.usecase.ListTriggersUseCase;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Enables injection of mock implementations for data sources
 * at compile time. This is useful for testing, since it allows us to use
 * a fake instance of the class to isolate the dependencies and run a test hermetically.
 */
public class Injection {

    public static UserRepository provideUserRepository(@NonNull Context context) {
        checkNotNull(context);

        return UserRepository.getInstance(UserLocalDataSource.getInstance(context),
                UserFakeDataSource.getInstance());
    }

    public static ProductRepository provideProductRepository(@NonNull Context context) {
        checkNotNull(context);

        return ProductRepository.getInstance(ProductFakeDataSource.getInstance());
    }

    public static NotificationRepository provideNotificationRepository(@NonNull Context context) {
        checkNotNull(context);

        return NotificationRepository.getInstance(NotificationLocalDataSource.getInstance(context));
    }

    public static RequestNotificationDetailsUseCase provideRequestNotificationDetailsUseCase(@NonNull Context context) {
        return new RequestNotificationDetailsUseCase(provideNotificationRepository(context));
    }

    public static SettingsRepository provideSettingsRepository(@NonNull Context context) {
        checkNotNull(context);

        return SettingsRepository.getInstance(SettingsLocalDataSource.getInstance(context));
    }

    public static TriggerRepository provideTriggerRepository(@NonNull Context context) {
        checkNotNull(context);

        return TriggerRepository.getInstance(TriggerLocalDataSource.getInstance(context));
    }

    public static ChangeNotificationReadStatusUseCase provideChangeNotificationReadStatusUseCase(@NonNull Context context) {
        return new ChangeNotificationReadStatusUseCase(provideNotificationRepository(context));
    }

    public static SendCustomStatusUseCase provideSendCustomStatusUseCase(@NonNull Context context) {
        return new SendCustomStatusUseCase(provideNotificationRepository(context));
    }

    public static BaseUseCaseHandler provideUseCaseHandler() {
        return BaseUseCaseHandler.getInstance();
    }

    public static SignInUseCase provideSignInUseCase(@NonNull Context context) {
        return new SignInUseCase(provideUserRepository(context));
    }

    public static ForgotUserPasswordUseCase provideForgotPasswordUseCase(
            @NonNull Context context) {
        return new ForgotUserPasswordUseCase(provideUserRepository(context));
    }

    public static SendCustomDataUseCase provideSendCustomDataUseCase(@NonNull Context context) {
        return new SendCustomDataUseCase(provideNotificationRepository(context));
    }

    public static SaveCustomDataUseCase provideSaveCustomDataUseCase(@NonNull Context context) {
        return new SaveCustomDataUseCase(provideNotificationRepository(context));
    }

    public static CustomDataToCustomDataListItemViewModelMapper provideCustomDataToCustomDataListItemViewModelMapper() {
        return new CustomDataToCustomDataListItemViewModelMapper();
    }

    public static RetrieveCustomDataUseCase provideRetrieveCustomDataUseCase(@NonNull Context context) {
        return new RetrieveCustomDataUseCase(provideNotificationRepository(context));
    }

    public static ProductToProductListItemViewModelMapper provideProductToProductListItemViewModelMapper() {
        return new ProductToProductListItemViewModelMapper();
    }

    public static SaveSettingsUseCase provideSaveSettingsUseCase(@NonNull Context context) {
        return new SaveSettingsUseCase(provideSettingsRepository(context));
    }

    public static LoadSettingsUseCase provideLoadSettingsUseCase(@NonNull Context context) {
        return new LoadSettingsUseCase(provideSettingsRepository(context));
    }

    public static SetTQGStateUseCase provideSetTQGStateUseCase(@NonNull Context context) {
        return new SetTQGStateUseCase(provideSettingsRepository(context));
    }

    public static SetTQ1StateUseCase provideSetTQ1StateUseCase(@NonNull Context context) {
        return new SetTQ1StateUseCase(provideSettingsRepository(context));
    }

    public static SearchProductsUseCase provideSearchProductsUseCase(@NonNull Context context) {
        return new SearchProductsUseCase(provideProductRepository(context));
    }

    public static ListNotificationsUseCase provideListNotificationUseCase(@NonNull Context context) {
        return new ListNotificationsUseCase(provideNotificationRepository(context));
    }

    public static NotificationToNotificationListItemViewModelMapper provideNotificationToNotificationListItemViewModelMapper() {
        return new NotificationToNotificationListItemViewModelMapper();
    }

    public static ListTriggersUseCase provideListTriggersUseCase(@NonNull Context context) {
        return new ListTriggersUseCase(provideTriggerRepository(context));
    }

    public static TriggerToTriggerListItemViewModelMapper provideTriggerToTriggerListItemViewModelMapper() {
        return new TriggerToTriggerListItemViewModelMapper();
    }

    public static FilterRepository provideFilterRepository(@NonNull Context context) {
        return FilterRepository.getInstance(FilterFakeDataSource.getInstance());
    }

    public static GetFiltersUseCase provideGetFiltersUseCase(@NonNull Context context) {
        return new GetFiltersUseCase(provideFilterRepository(context));
    }

    public static FilterToFilterViewModelMapper provideFilterToFilterViewModelMapper() {
        return new FilterToFilterViewModelMapper();
    }

    public static FilterCategoryToFilterCategoryViewModelMapper provideFilterCategoryToFilterCategoryViewModelMapper() {
        return new FilterCategoryToFilterCategoryViewModelMapper(provideFilterToFilterViewModelMapper());
    }

    public static DeviceInfoRepository provideDeviceInfoRepository(@NonNull Context context) {
        return DeviceInfoRepository.getInstance(DeviceInfoLocalDataSource.getInstance(context));
    }

}
