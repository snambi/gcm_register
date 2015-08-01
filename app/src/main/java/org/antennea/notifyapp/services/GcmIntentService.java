package org.antennea.notifyapp.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.antennae.notifyapp.constants.Globals;
import org.antennae.notifyapp.events.EventManager;
import org.antennae.notifyapp.model.Alert;
import org.antennae.notifyapp.model.AlertSeverityEnum;
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

        EventManager eventManager = EventManager.getInstance();

        if( !extras.isEmpty() ){

            // filter messages based "message_type".
            // ignore messages that we don't recognize

            if( GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)){
                Log.i(Globals.TAG, "GCM MSG SEND ERROR: " + extras.toString());
            }else if( GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)){
                Log.i(Globals.TAG, "GCM MSG DELETED: " + extras.toString());
            }else if( GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)){
                Log.i(Globals.TAG, "GCM MSG DELIVERED: " + extras.toString());

                Alert alert = new Alert();
                alert.setAction("test");
                alert.setId(982378921738921L);
                alert.setSeverity(AlertSeverityEnum.HIGH.toString());
                alert.setMessage("TEST MESSAGE");
                alert.setTitle("TEST1");

                eventManager.processAlertsReceived( alert );
            }

            Log.i(Globals.TAG, extras.toString());
        }

        // Release the wake lock provided by the WakefulBroadcastReceiver.
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }
}
