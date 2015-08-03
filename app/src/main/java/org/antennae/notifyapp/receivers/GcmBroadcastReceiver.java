package org.antennae.notifyapp.receivers;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import org.antennae.notifyapp.constants.Globals;
import org.antennea.notifyapp.services.GcmIntentService;

public class GcmBroadcastReceiver extends WakefulBroadcastReceiver {

    public GcmBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i(Globals.TAG, "broadcast message received :");

        // Explicitly specify that GcmIntentService will handle the intent.
        ComponentName comp = new ComponentName(context.getPackageName(),
                                                GcmIntentService.class.getName());

        // Start the service, keeping the device awake while it is launching.
        startWakefulService(context, (intent.setComponent(comp)));

        setResultCode(Activity.RESULT_OK);
    }
}
