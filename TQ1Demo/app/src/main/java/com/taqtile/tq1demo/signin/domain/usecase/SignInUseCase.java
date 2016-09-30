package com.taqtile.tq1demo.signin.domain.usecase;

import br.com.taqtile.android.cleanbase.domain.BaseUseCase;
import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.model.User;
import com.taqtile.tq1demo.data.source.user.UserRepository;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Sign in a user
 */
public class SignInUseCase extends BaseUseCase<SignInUseCase.RequestValues,
        SignInUseCase.ResponseValue> {

    private final UserRepository mUserRepository;

    public SignInUseCase(UserRepository userRepository) {
        mUserRepository = checkNotNull(userRepository, "userRepository cannot be null!");
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        DataSourceResponse<User> response = mUserRepository.signInUser(requestValues.isFacebookLogin(),
                requestValues.getUserEmail(), requestValues.getUserPassword());

        if (response.isSuccess()) {
            getUseCaseCallback().onSuccess(
                    new SignInUseCase.ResponseValue(response.getResponse()));
        } else {
            // TODO: send the error back through the callback
            getUseCaseCallback().onError();
        }
    }

    public static final class RequestValues implements BaseUseCase.RequestValues {

        private final String mUserEmail;
        private final String mUserPassword;
        private final boolean mFacebookLogin;

        public RequestValues(String userEmail, String userPassword, boolean isFacebookLogin) {
            mUserEmail = userEmail;
            mUserPassword = userPassword;
            mFacebookLogin = isFacebookLogin;
        }

        public String getUserEmail() {
            return mUserEmail;
        }

        public String getUserPassword() {
            return mUserPassword;
        }

        public boolean isFacebookLogin() {
            return mFacebookLogin;
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
