package org.antennae.gcmtests.gcmtest;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by nsankaran on 6/18/15.
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

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
