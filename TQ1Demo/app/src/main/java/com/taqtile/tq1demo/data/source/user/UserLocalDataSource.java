package com.taqtile.tq1demo.data.source.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.model.DataSourceResponseError;
import com.taqtile.tq1demo.data.model.User;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by indigo on 5/18/16.
 */
public class UserLocalDataSource implements UserDataSource {

    private static final String USER_PREFERENCE_FILE_KEY = "USER_FILE_KEY";
    private static final String USER_PREFERENCE_OBJ_KEY = "USER_OBJ_KEY";

    private static UserLocalDataSource INSTANCE;

    @NonNull
    private final Context mContext;

    // Prevent direct instantiation
    private UserLocalDataSource(@NonNull Context context) {
        mContext = checkNotNull(context, "context can't be null");
    }

    public static UserLocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new UserLocalDataSource(context);
        }

        return INSTANCE;
    }

    /**
     * Gets the current saved user.
     *
     * @return A {@link DataSourceResponse} object containing either the deleted user
     * or a {@link DataSourceResponseError}
     */
    @Override
    public DataSourceResponse<User> getCurrentUser() {
        String serializedUser = userSharedPreferences().getString(USER_PREFERENCE_OBJ_KEY, null);
        User user = User.create(serializedUser);

        return new DataSourceResponse<>(user);
    }

    /**
     * This method is not required for the local data source.
     *
     * @return Never called, so it always returns null.
     */
    @Override
    public DataSourceResponse<User> signInUser(boolean usingFacebook, String email, String password) {
        return null;
    }

    /**
     * Deletes all user information stored on the shared preferences.
     *
     * @return A {@link DataSourceResponse} object containing either the deleted user
     * or a {@link DataSourceResponseError}
     */
    @Override
    public DataSourceResponse<Boolean> signOutUser() {
        boolean success = userSharedPreferences().edit()
                .remove(USER_PREFERENCE_OBJ_KEY).commit();
        DataSourceResponse<Boolean> response;

        if (success) {
            response = new DataSourceResponse<>(true);
        } else {
            // TODO: handle error
            response = new DataSourceResponse<>(new DataSourceResponseError()
            );
        }

        return response;
    }

    /**
     * This method is not required for the local data source.
     *
     * @param user
     * @return Never called, so it always returns null.
     */
    @Override
    public DataSourceResponse signUpUser(User user) {
        return null;
    }

    /**
     * Saves user on shared preferences.
     *
     * @param user user to be saved
     * @return A {@link DataSourceResponse} object containing either the saved user
     * or a {@link DataSourceResponseError}
     */
    @Override
    public DataSourceResponse saveUser(User user) {

        DataSourceResponse response;

        boolean success = userSharedPreferences().edit()
                .putString(USER_PREFERENCE_OBJ_KEY, user.serialize())
                .commit();

        if (success) {
            response = new DataSourceResponse<>(user);
        } else {
            // TODO: handle error
            response = new DataSourceResponse<>(new DataSourceResponseError()
            );
        }

        return response;
    }

    /**
     * This method is not required for the local data source.
     *
     * @return Never called, so it always returns null.
     */
    @Override
    public DataSourceResponse<Boolean> forgotUserPassword(String email) {
        return null;
    }

    private SharedPreferences userSharedPreferences() {
        String sharedPreferencesFileName = String.format("%s.%s",
                mContext.getApplicationContext().getPackageName(), USER_PREFERENCE_FILE_KEY);

        return mContext.getSharedPreferences(sharedPreferencesFileName,
                Context.MODE_PRIVATE);
    }
}
