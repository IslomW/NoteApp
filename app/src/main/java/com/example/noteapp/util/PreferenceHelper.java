package com.example.noteapp.util;

public interface PreferenceHelper {

    void setValue(String key, Object value);

    <T> Object getValue(Class<T> tClass, String key, Object defaultValue);

    void clera();

    void remoteKey(String key);


}
