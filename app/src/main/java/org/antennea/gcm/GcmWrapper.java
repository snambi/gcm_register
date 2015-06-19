package org.antennea.gcm;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.antennea.Constants;
import org.antennea.android.utils.AppVersionUtils;
import org.antennea.android.utils.PreferencesUtil;

import java.io.IOException;


/**
 * Created by nsankaran on 6/11/15.
 */
public class GcmWrapper {



    private Context context;

    public GcmWrapper( Context context){
        this.context = context;
    }

    public String getRegId(){
        String regId = getRegIdFromPrefs();
        if( regId == null || regId.trim().equals("") ){
            registerWithGcm();
        }

        return regId;
    }

    public String getRegIdFromPrefs(){

        String registrationId=null;

        SharedPreferences preferences = context.getSharedPreferences(Constants.PREF_ANTENNAE, Context.MODE_PRIVATE);
        registrationId  = preferences.getString(Constants.PREF_REGISTRATION_ID, "");

        int appVersion = AppVersionUtils.getAppVersion(context);
        SharedPreferences prefs =PreferencesUtil.getSharedPreferences(context);
        if( appVersion != AppVersionUtils.getSavedAppVersion( prefs ) ){
            // if different, clear the saved regId
            registrationId=null;
        }

        return registrationId;
    }

    public void registerWithGcm(){
        GcmRegistrationTask registerTask = new GcmRegistrationTask(context, Constants.PROJECT_ID);
        registerTask.execute();
    }

    public static class GcmRegistrationTask extends AsyncTask{

        private Context context;
        private String projectId;

        public GcmRegistrationTask( Context context, String projectId ){
            this.context = context;
            this.projectId = projectId;
        }

        @Override
        protected Object doInBackground(Object[] params) {
            String regId = null;

            GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);
            try {
                regId = gcm.register( projectId );
            } catch (IOException e) {
                e.printStackTrace();
            }

            return regId;
        }
    }
}
