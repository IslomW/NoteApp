package com.example.noteapp.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.noteapp.BuildConfig;
import com.example.noteapp.model.User;
import com.google.gson.Gson;

public class PreferenceManger implements PreferenceHelper {

    private static String SHARED_PREFERENCE_NAME = BuildConfig.APPLICATION_ID + ".local";

    private SharedPreferences mPreference;

    private static PreferenceManger mInstance;

    public PreferenceManger(Context context) {
        mPreference = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
    }


    public static PreferenceManger getInstance(Context context) {
        if (mInstance == null)
            mInstance = new PreferenceManger(context);

        return mInstance;
    }


    @Override
    public void setValue(String key, Object value) {
        if (value.getClass().equals(String.class)) {
            mPreference.edit().putString(key, (String) value).apply();
        } else if (value.getClass().equals(Boolean.class)) {
            mPreference.edit().putBoolean(key, (Boolean) value).apply();
        } else if (value.getClass().equals(User.class)) {
            String json = new Gson().toJson((User) value);
            setValue(key, json);
        }
    }

    @Override
    public <T> Object getValue(Class<T> tClass, String key, Object defaultValue) {

        if (tClass.equals(String.class)) {
            return mPreference.getString(key, (String) defaultValue);
        } else if (tClass.equals(Boolean.class)) {
            return mPreference.getBoolean(key, (Boolean) defaultValue);
        }else if (tClass.equals(User.class)) {
            String json = mPreference.getString(key, "");
            if (!json.isEmpty()) {
                User user = new Gson().fromJson(json, User.class);
                return user;
            }
        }
        return defaultValue;
    }

    @Override
    public void clear() {
        mPreference.edit().clear().apply();
    }

    @Override
    public void remoteKey(String key) {
        mPreference.edit().remove(key).apply();
    }
}
