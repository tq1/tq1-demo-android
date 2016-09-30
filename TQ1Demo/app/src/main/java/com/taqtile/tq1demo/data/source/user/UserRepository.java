package com.taqtile.tq1demo.data.source.user;

import android.support.annotation.NonNull;

import com.taqtile.tq1demo.data.model.DataSourceResponse;
import com.taqtile.tq1demo.data.model.DataSourceResponseError;
import com.taqtile.tq1demo.data.model.User;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Concrete implementation to load a user from the data sources into a cache.
 */
public class UserRepository implements UserDataSource {

    private static UserRepository INSTANCE = null;

    private final UserDataSource mUserLocalDataSource;
    private final UserDataSource mUserRemoteDataSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    User mCachedUser;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean mCacheIsDirty = false;

    /**
     * Prevents direct instantiation. This class is instantiated by the Injection class.
     */
    private UserRepository(@NonNull UserDataSource userLocalDataSource,
                           @NonNull UserDataSource userRemoteDataSource) {
        mUserLocalDataSource = checkNotNull(userLocalDataSource);
        mUserRemoteDataSource = checkNotNull(userRemoteDataSource);
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param userLocalDataSource the local storage data source
     * @param userRemoteDataSource the backend data source
     * @return the {@link UserRepository} instance
     */
    public static UserRepository getInstance(UserDataSource userLocalDataSource,
                                             UserDataSource userRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository(userLocalDataSource, userRemoteDataSource);
        }

        return INSTANCE;
    }

    /**
     * Used to force {@link #getInstance(UserDataSource, UserDataSource)} to create a new instance
     * next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public DataSourceResponse<User> getCurrentUser() {
        DataSourceResponse<User> dataSourceResponse;

        // Respond immediately with cache if available and not dirty
        if (mCachedUser != null && !mCacheIsDirty) {
            dataSourceResponse = new DataSourceResponse<>(mCachedUser);

            return dataSourceResponse;
        }

        if (mCacheIsDirty) {
            // If the cache is dirty we need to fetch new data from the network.

            return mUserRemoteDataSource.getCurrentUser();
        } else {
            // Query the local storage if available. If not, query the network.

            dataSourceResponse = mUserLocalDataSource.getCurrentUser();

            if (dataSourceResponse.isSuccess()) {
                return dataSourceResponse;
            } else {
                dataSourceResponse = mUserRemoteDataSource.getCurrentUser();

                if (dataSourceResponse.isSuccess()) {
                    return dataSourceResponse ;
                } else {
                    // TODO: handle remote data source error
                    return null;
                }
            }
        }
    }

    /**
     * This method calls only the remote data source. It doesn't make sense
     * to ask the local data source to sign a user in.
     *
     * @param usingFacebook whether the sign in should be done through facebook
     * @param email
     * @param password
     */
    @Override
    public DataSourceResponse<User> signInUser(boolean usingFacebook, String email, String password) {
        DataSourceResponse<User> response = mUserRemoteDataSource.signInUser(usingFacebook,
                email, password);

        if (response.isSuccess()) {
            return response;
        } else {
            // TODO: handle remote data source error
            return null;
        }
    }

    /**
     * Sign out user. We only need to call sign out on the local data source.
     *
     * @return A {@link DataSourceResponse} containing either a user or a
     * {@link DataSourceResponseError}.
     */
    @Override
    public DataSourceResponse<Boolean> signOutUser() {
        DataSourceResponse<Boolean> response = mUserLocalDataSource.signOutUser();

        if (response.isSuccess()) {
            return response;
        } else {
            // TODO: handle local data source error
            return null;
        }
    }

    /**
     * Sign up user. We only need to call sign up on the remote data source.
     *
     * @return A {@link DataSourceResponse} containing either a user or a
     * {@link DataSourceResponseError}.
     */
    @Override
    public DataSourceResponse<User> signUpUser(User user) {
        checkNotNull(user, "You can't sign a null user up");

        DataSourceResponse<User> response = mUserRemoteDataSource.signUpUser(user);

        if (response.isSuccess()) {
            return response;
        } else {
            // TODO: handle remote source error
            return null;
        }
    }

    /**
     * Saves user on the local data source.
     *
     * @param user
     * @return A {@link DataSourceResponse} containing either a user or a
     * {@link DataSourceResponseError}.
     */
    @Override
    public DataSourceResponse<User> saveUser(User user) {
        DataSourceResponse<User> response = mUserLocalDataSource.saveUser(user);

        if (response.isSuccess()) {
            return response;
        } else {
            // TODO: handle remote source error
            return null;
        }
    }

    /**
     * Calls remote user data source.
     *
     * @param email
     * @return A {@link DataSourceResponse} containing either a user or a
     * {@link DataSourceResponseError}.
     */
    @Override
    public DataSourceResponse<Boolean> forgotUserPassword(String email) {
        DataSourceResponse<Boolean> response = mUserRemoteDataSource.forgotUserPassword(email);

        if (response.isSuccess()) {
            return response;
        } else {
            // TODO: handle remote source error
            return null;
        }
    }
}
