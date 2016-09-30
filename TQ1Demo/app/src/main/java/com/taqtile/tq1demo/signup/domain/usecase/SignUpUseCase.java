package com.taqtile.tq1demo.signup.domain.usecase;

import br.com.taqtile.android.cleanbase.domain.BaseUseCase;
import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.model.User;
import com.taqtile.tq1demo.data.source.user.UserRepository;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Sign in a user
 */
public class SignUpUseCase extends BaseUseCase<SignUpUseCase.RequestValues,
        SignUpUseCase.ResponseValue> {

    private final UserRepository mUserRepository;

    public SignUpUseCase(UserRepository userRepository) {
        mUserRepository = checkNotNull(userRepository, "userRepository cannot be null!");
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {

        DataSourceResponse<User> response = mUserRepository.signUpUser(
                requestValues.getUser());

        if (response.isSuccess()) {
            getUseCaseCallback().onSuccess(
                    new SignUpUseCase.ResponseValue(response.getResponse()));
        } else {
            // TODO: send the error back through the callback
            getUseCaseCallback().onError();
        }
    }

    public static final class RequestValues implements BaseUseCase.RequestValues {

        private final User mUser;

        public RequestValues(User user) {
            mUser = user;
        }

        public User getUser() {
            return mUser;
        }
    }

    public static final class ResponseValue implements BaseUseCase.ResponseValue {

        private final User mUser;

        public ResponseValue(User user) {
            mUser = checkNotNull(user, "user can't be null");
        }

        public User getUser() {
            return mUser;
        }

    }
}
