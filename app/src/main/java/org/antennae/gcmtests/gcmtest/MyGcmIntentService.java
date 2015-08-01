package org.antennae.gcmtests.gcmtest;

import android.app.IntentService;
import android.content.Intent;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import org.antennae.android.Constants;

import java.io.IOException;

/**
 * Created by nambi sankaran on 6/17/15.
 */
@Deprecated
public class MyGcmIntentService extends IntentService {


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyGcmIntentService(String name) {
        super(name);
    }

    public MyGcmIntentService(){
        this("MyGcmIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        InstanceID instanceID = InstanceID.getInstance(this);
        try {
            String token = instanceID.getToken(Constants.PROJECT_ID, GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
