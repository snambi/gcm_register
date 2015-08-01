package org.antennea.notifyapp.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.antennae.notifyapp.receivers.GcmBroadcastReceiver;

/**
 * Created by nambi sankaran on 6/18/15.
 */
public class GcmIntentService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public GcmIntentService(String name) {
        super(name);
    }

    public GcmIntentService(){
        super("GcmIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Bundle extras = intent.getExtras();

        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);

        String messageType = gcm.getMessageType(intent);

        if( !extras.isEmpty() ){

            // filter messages based "message_type".
            // ignore messages that we don't recognize

            if( GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)){

            }else if( GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)){

            }else if( GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)){

            }

            Log.i("GCMTEST", extras.toString());
        }

        // Release the wake lock provided by the WakefulBroadcastReceiver.
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }
}
