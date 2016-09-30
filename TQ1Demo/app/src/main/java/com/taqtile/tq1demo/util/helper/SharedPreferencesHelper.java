package com.taqtile.tq1demo.util.helper;

/**
 * Created by taqtile on 8/1/16.
 */
import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;

import java.lang.reflect.Type;

public class SharedPreferencesHelper {

    public static final String PREFS_NAME = "MyPrefsFile";

    private static SharedPreferencesHelper mInstance = null;

    private SharedPreferences mPreferenceSettings;
    private SharedPreferences.Editor mPreferenceEditor;
    private Gson mGson;


    protected SharedPreferencesHelper(Context context) {
        mPreferenceSettings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        mPreferenceEditor = mPreferenceSettings.edit();
        mGson = new Gson();
    }


    public static SharedPreferencesHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPreferencesHelper(context);
        }
        return mInstance;
    }

    public void saveObj(String key, Object data) {
        mPreferenceEditor.putString(key, mGson.toJson(data));
        mPreferenceEditor.commit();
    }

    public void removeObj(String key) {
        mPreferenceEditor.remove(key);
        mPreferenceEditor.commit();
    }

    public Object loadObj(String key, Class<?> c) {
        String json = mPreferenceSettings.getString(key, "");
        return mGson.fromJson(json, c);
    }

    public Object loadObj(String key, Type t) {
        String json = mPreferenceSettings.getString(key, "");
        return mGson.fromJson(json, t);
    }

}
