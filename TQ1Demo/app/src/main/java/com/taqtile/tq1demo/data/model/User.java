package com.taqtile.tq1demo.data.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.ryanharter.auto.value.gson.AutoValueGsonTypeAdapterFactory;

/**
 * Created by indigo on 5/18/16.
 */

@AutoValue
public abstract class User {

    public static User create(String id, String name, String gender) {
        return new AutoValue_User(id, name, gender);
    }

    public static User create(String serializedUser) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new AutoValueGsonTypeAdapterFactory())
                .create();
        User user = gson.fromJson(serializedUser, User.class);
        return user;
    }

    public String serialize() {
        return new Gson().toJson(this);
    }

    public abstract String getId();

    public abstract String getName();

    public abstract String getGender();

    public static TypeAdapter<User> typeAdapter(Gson gson) {
        return new AutoValue_User.GsonTypeAdapter(gson);
    }
}

