package com.demo.storageinandroid.storage;

import android.content.Context;
import android.content.SharedPreferences;

public class StoreSharePref {

    private static SharedPreferences mSharedPreference;
    public static String mUserName = "mUserName";
    public static String mUserNumber= "mUserNumber";
    public static String mUserAge= "mUserAge";
    public static String isLogin= "isLogin";
    public static String mUserAddress= "mUserAddress";
    public static String mUserEmail= "mUserEmail";

    public static void init(Context context){
        if(mSharedPreference==null){
            mSharedPreference = context.getSharedPreferences("StorageApp", Context.MODE_PRIVATE);
        }
    }

    public static void write(String key, String value){
        SharedPreferences.Editor editor = mSharedPreference.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void write(String key, int value){
        SharedPreferences.Editor editor = mSharedPreference.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void write(String key, boolean value){
        SharedPreferences.Editor editor = mSharedPreference.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static String read(String key, String defaultValue){
        return mSharedPreference.getString(key, defaultValue);
    }

    public static Integer read(String key, Integer defaultValue){
        return mSharedPreference.getInt(key, defaultValue);
    }

    public static boolean read(String key, boolean defaultValue){
        return mSharedPreference.getBoolean(key, defaultValue);
    }

    public static void clearData(){
        mSharedPreference.getAll().clear();
    }

}
