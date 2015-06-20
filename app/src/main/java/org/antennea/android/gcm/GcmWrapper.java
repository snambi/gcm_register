package org.antennea.android.gcm;

import android.content.Context;
import android.os.AsyncTask;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.antennea.Constants;
import org.antennea.android.utils.AntennaeContext;

import java.io.IOException;

public class GcmWrapper {

    private Context context;
    private AntennaeContext antennaeContext;
    private GoogleCloudMessaging googleCloudMessaging;

    public GcmWrapper( Context context){

        this.context = context;
        this.antennaeContext = new AntennaeContext(context);

        this.googleCloudMessaging = GoogleCloudMessaging.getInstance(context);
    }

    /*
        Retrieves the saved App RegistrationId from SharedPreferences.
        If the app is unregistered or needs a new registerationId, kicks of a new refresh.
     */
    public String getRegistrationId(){
        String regId = getRegIdFromContext();
        if( regId == null || regId.trim().equals("") ){
            registerWithGcm();
        }

        return regId;
    }

    public String getRegIdFromContext(){

        String registrationId=null;

        if( antennaeContext.isRegistered() ){
            registrationId = antennaeContext.getRegistrationId();
        }else{
            // kickoff registrationId refresh in the background.
            registerWithGcm();
            return registrationId;
        }

        if( antennaeContext.isNewRegistrationIdNeeded() ){
            // kickoff registrationId refresh in the background
            registerWithGcm();
        }

        return registrationId;
    }

    public void registerWithGcm(){
        GcmRegistrationTask registerTask = new GcmRegistrationTask(googleCloudMessaging, antennaeContext, Constants.PROJECT_ID);
        registerTask.execute();
    }

    /**
     * Backgroud Async Task to register with GCM and Save the registrationId
     */
    public static class GcmRegistrationTask extends AsyncTask{

        private GoogleCloudMessaging gcm;
        private AntennaeContext antennaeContext;
        private String projectId;

        public GcmRegistrationTask( GoogleCloudMessaging gcm, AntennaeContext ac, String projectId ){
            this.gcm = gcm;
            this.antennaeContext = ac;
            this.projectId = projectId;
        }

        @Override
        protected Object doInBackground(Object[] params) {
            String regId = null;

            try {

                regId = gcm.register( projectId );
                antennaeContext.saveRegistrationId(regId);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return regId;
        }
    }
}
