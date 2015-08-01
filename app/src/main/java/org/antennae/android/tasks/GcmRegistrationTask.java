package org.antennae.android.tasks;

/**
 * Created by nsankaran on 6/23/15.
 */

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.antennae.android.AntennaeContext;
import org.antennae.android.transport.DeviceInfo;

import org.antennae.android.transport.AppDetails;
import org.antennae.android.transport.AppInfo;
import org.antennae.notifyapp.constants.Globals;

import java.io.IOException;

/**
 * Backgroud Async Task to register with GCM and Save the registrationId
 */
public class GcmRegistrationTask extends AsyncTask {

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
            appInfo.setGcmRegistrationId(regId);

            AppDetails appDetails = new AppDetails();
            appDetails.setAppInfo(appInfo);
            appDetails.setDeviceInfo(deviceInfo);

            Log.i(Globals.TAG, "AppDetails " + appDetails.toJson());

            AntenneaServerRegistrationTask antenneaServerRegistrationTask = new AntenneaServerRegistrationTask("http://192.168.1.160:8080/api/registration", appDetails);
            antenneaServerRegistrationTask.execute();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return regId;
    }
}