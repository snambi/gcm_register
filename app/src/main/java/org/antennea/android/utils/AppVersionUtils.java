package org.antennea.android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by nsankaran on 6/16/15.
 */
public class AppVersionUtils {

    public static final String PROPERTY_APP_VERSION = "appVersion";

    public static int getAppVersion( Context context){
        int appversion = getAppVersionFromApk(context);
        return appversion;
    }

    public static int getAppVersionFromApk( Context context ){

        int appversion;

        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            appversion = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Could not get package name: " + e);
        }

        return appversion;
    }

    public static int getSavedAppVersion(SharedPreferences prefs){
        int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
        return registeredVersion;
    }

    public static void saveAppVersion( SharedPreferences prefs , int appVersion){
        prefs.edit().putInt(PROPERTY_APP_VERSION, appVersion );
    }



}
