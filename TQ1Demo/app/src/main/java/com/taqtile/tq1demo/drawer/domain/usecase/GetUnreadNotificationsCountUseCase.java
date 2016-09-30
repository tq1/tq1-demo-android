package com.taqtile.tq1demo.drawer.domain.usecase;

import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.source.notifications.NotificationRepository;

import br.com.taqtile.android.cleanbase.domain.BaseUseCase;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by taqtile on 7/27/16.
 */
public class GetUnreadNotificationsCountUseCase extends BaseUseCase<GetUnreadNotificationsCountUseCase.RequestValues,
        GetUnreadNotificationsCountUseCase.ResponseValue> {

    private final NotificationRepository mNotificationsRepository;

    public GetUnreadNotificationsCountUseCase(NotificationRepository notificationRepository) {
        mNotificationsRepository = checkNotNull(notificationRepository, "NotificationsRepository cannot be null!");
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        DataSourceResponse<Integer> response = mNotificationsRepository.getUnreadNotificationsCount();

        if (response.isSuccess()) {
            getUseCaseCallback().onSuccess(
                    new GetUnreadNotificationsCountUseCase.ResponseValue(response.getResponse()));
        } else {
            //TODO: send the error back through the callback
            getUseCaseCallback().onError();
        }
    }

    public static final class RequestValues implements BaseUseCase.RequestValues {

    }

    public static final class ResponseValue implements BaseUseCase.ResponseValue {

        private final Integer mUnreadNotificationsCount;

        public ResponseValue(Integer unreadNotificationsCount) {
            mUnreadNotificationsCount = checkNotNull(unreadNotificationsCount, "MenuItems cannot be null!");
        }

        public Integer getUnreadNotificationsCount() {
            return mUnreadNotificationsCount;
        }

    }

}
