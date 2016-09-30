package com.taqtile.tq1demo.signin.domain.usecase;

import br.com.taqtile.android.cleanbase.domain.BaseUseCase;
import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.source.user.UserRepository;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Sign in a user
 */
public class ForgotUserPasswordUseCase extends BaseUseCase<ForgotUserPasswordUseCase.RequestValues,
        ForgotUserPasswordUseCase.ResponseValue> {

    private final UserRepository mUserRepository;

    public ForgotUserPasswordUseCase(UserRepository userRepository) {
        mUserRepository = checkNotNull(userRepository, "userRepository cannot be null!");
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        DataSourceResponse<Boolean> response =
                mUserRepository.forgotUserPassword(requestValues.getUserEmail());

        if (response.isSuccess()) {
            getUseCaseCallback().onSuccess(
                    new ForgotUserPasswordUseCase.ResponseValue(response.getResponse()));
        } else {
            // TODO: send the error back through the callback
            getUseCaseCallback().onError();
        }
    }

    public static final class RequestValues implements BaseUseCase.RequestValues {

        private final String mUserEmail;

        public RequestValues(String userEmail) {
            mUserEmail = userEmail;
        }

        public String getUserEmail() {
            return mUserEmail;
        }

    }

    public static final class ResponseValue implements BaseUseCase.ResponseValue {

        private final Boolean mForgotRequestSent;

        public ResponseValue(Boolean forgotResponse) {
            checkNotNull(forgotResponse, "forgotResponse can't be null");
            mForgotRequestSent = forgotResponse;
        }

        public boolean isForgotRequestSent() {
            return mForgotRequestSent;
        }
    }
}
