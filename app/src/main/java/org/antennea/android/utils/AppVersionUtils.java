package org.antennea.android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import org.antennea.Constants;

/**
 * Created by nsankaran on 6/16/15.
 */
public class AppVersionUtils {


    /*
        Get the App version from installed APK file
     */
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

}
