package com.taqtile.tq1demo.data.source.user;

import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.model.User;

/**
 * Created by indigo on 5/20/16.
 */
public class UserFakeDataSource implements UserDataSource {

    private static UserFakeDataSource INSTANCE;

    private static final DataSourceResponse<User> USER_FAKE_RESPONSE =
            new DataSourceResponse<>(User.create("1234", "Thiago", "Masculino"));

    //Prevent direct instantiation
    private UserFakeDataSource() {}

    public static UserFakeDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserFakeDataSource();
        }

        return INSTANCE;
    }

    @Override
    public DataSourceResponse<User> getCurrentUser() {
        return USER_FAKE_RESPONSE;
    }

    @Override
    public DataSourceResponse<User> signInUser(boolean usingFacebook, String email, String password) {
        return USER_FAKE_RESPONSE;
    }

    @Override
    public DataSourceResponse<Boolean> signOutUser() {
        return new DataSourceResponse<>(true);
    }

    @Override
    public DataSourceResponse<User> signUpUser(User user) {
        return USER_FAKE_RESPONSE;
    }

    @Override
    public DataSourceResponse<User> saveUser(User user) {
        return USER_FAKE_RESPONSE;
    }

    @Override
    public DataSourceResponse<Boolean> forgotUserPassword(String email) {
        return new DataSourceResponse<>(true);
    }
}
