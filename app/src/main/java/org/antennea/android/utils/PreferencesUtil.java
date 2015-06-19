package org.antennea.android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by nsankaran on 6/16/15.
 */
public class PreferencesUtil {

    public static SharedPreferences getSharedPreferences(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences;
    }
}
