package com.example.virtualshop;
import android.content.Context;
import android.content.SharedPreferences;
public class SharedPreferencesHelper {
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String KEY_IS_LOGGED_IN = "is_logged_in";
    public static void setLoggedIn(Context context, boolean isLoggedIn) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.apply();
    }
    public static boolean isLoggedIn(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false);
    }
}
