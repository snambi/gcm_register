package org.antennea.android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.antennea.Constants;

/**
 * Created by nambi sankaran on 6/16/15.
 */
public class AntennaeContext {

    private SharedPreferences preferences=null;
    private Context context;

    public AntennaeContext(Context context){
        this.context = context;
        this.preferences = context.getSharedPreferences(Constants.PREF_ANTENNAE,Context.MODE_PRIVATE);
    }

    protected SharedPreferences getPreferences(){
        return preferences;
    }

    /*
        Save registrationId from GCM and appVersion in Shared-Prefrences
     */
    public void saveRegistrationId( String registrationId ){

        if( registrationId == null || registrationId.trim().equals("")){
            throw new NullPointerException("registrationId cannot be null or empty");
        }

        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putString(Constants.ANTENNAE_REGISTRATION_ID, registrationId);
        editor.apply();

        // save appVersion
        int appVersion = AppVersionUtils.getAppVersionFromApk(context);
        saveAppVersion(appVersion);
    }

    public boolean isRegistered(){
        boolean result=false;
        if( getPreferences().contains(Constants.ANTENNAE_REGISTRATION_ID) ){
            if( getPreferences().getString(Constants.ANTENNAE_REGISTRATION_ID, null) != null ){
                result = true;
            }
        }
        return result;
    }

    public String getRegistrationId(){
        String result=null;
        if( isRegistered() ){
            result = getPreferences().getString(Constants.ANTENNAE_REGISTRATION_ID,null);
        }

        return result;
    }


    public boolean isNewRegistrationIdNeeded(){
        boolean result = false;
        if( isNewAppVersion() ){
            result = true;
        }

        return result;
    }

    public boolean isNewAppVersion(){
        boolean result = false;

        int appVersion = AppVersionUtils.getAppVersionFromApk(context);
        if( getSavedAppVersion() < appVersion ){
            result = true;
        }

        return result;
    }

    public void saveAppVersion( int appVersion ){
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putInt(Constants.ANTENNAE_APP_VERSION, appVersion);
        editor.apply();
    }

    public int getSavedAppVersion(){
        int savedAppVersion = getPreferences().getInt(Constants.ANTENNAE_APP_VERSION, -1000000);
        return savedAppVersion;
    }
}
