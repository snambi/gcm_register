/**
 * Copyright 2015 Antennea Project. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.antennea.android.gcm;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.antennae.gcmtests.gcmtest.Globals;
import org.antennea.android.Constants;
import org.antennea.android.AntennaeContext;
import org.antennea.android.transport.AppInfo;
import org.antennea.android.transport.DeviceInfo;

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

                // register the app with GCM
                regId = gcm.register( projectId );

                // save the registrationId locally
                antennaeContext.saveRegistrationId(regId);

                // retrieve the device information
                DeviceInfo deviceInfo = antennaeContext.getDeviceInfo();

                // send registration Id + device information to the app server
                AppInfo appInfo = antennaeContext.getAppInfo();

                Log.i(Globals.TAG, "DeviceInfo " + deviceInfo.toJson());

            } catch (IOException e) {
                e.printStackTrace();
            }

            return regId;
        }
    }
}
