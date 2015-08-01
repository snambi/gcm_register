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
package org.antennae.android.common.gcm;

import android.content.Context;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.antennae.android.common.AntennaeContext;
import org.antennae.android.common.Constants;
import org.antennae.android.common.tasks.tasks.GcmRegistrationTask;

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
     */
    public String getRegistrationId(){
        String regId = getRegIdFromContext();
        return regId;
    }

    public String getRegIdFromContext(){

        String registrationId=null;

        if( antennaeContext.isRegistered() ){
            registrationId = antennaeContext.getRegistrationId();
        }/*else{
            // kickoff registrationId refresh in the background.
            registerWithGcmAsync();
            return registrationId;
        }

        if( antennaeContext.isNewRegistrationIdNeeded() ){
            // kickoff registrationId refresh in the background
            registerWithGcmAsync();
        }*/

        return registrationId;
    }


    /*
        Register with GCM in the background.
     */
    public void registerWithGcmAsync(){
        GcmRegistrationTask registerTask = new GcmRegistrationTask(googleCloudMessaging, antennaeContext, Constants.PROJECT_ID);
        registerTask.execute();
    }

}
