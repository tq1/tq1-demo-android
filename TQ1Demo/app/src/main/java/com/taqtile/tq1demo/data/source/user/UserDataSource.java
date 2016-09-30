package com.taqtile.tq1demo.data.source.user;

import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.model.User;

/**
 * Created by indigo on 5/18/16.
 */
public interface UserDataSource {

    DataSourceResponse<User> getCurrentUser();

    DataSourceResponse<User> signInUser(boolean usingFacebook, String email, String password);

    DataSourceResponse<Boolean> signOutUser();

    DataSourceResponse<User> signUpUser(User user);

    DataSourceResponse<User> saveUser(User user);

    DataSourceResponse<Boolean> forgotUserPassword(String email);

}
