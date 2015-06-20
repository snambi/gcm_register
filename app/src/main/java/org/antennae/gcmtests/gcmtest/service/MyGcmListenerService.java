package org.antennae.gcmtests.gcmtest.service;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by nambi sankaran on 6/20/15.
 */
public class MyGcmListenerService extends GcmListenerService{

    private Log log;

    @Override
    public void onMessageReceived(String from, Bundle data) {
        showLog("Received: " + data.toString());
    }

    @Override
    public void onDeletedMessages() {
        showLog("Deleted messages on server");
    }

    @Override
    public void onMessageSent(String msgId) {
        showLog("Upstream message sent. Id=" + msgId);
    }

    @Override
    public void onSendError(String msgId, String error) {
        showLog("Upstream message send error. Id=" + msgId + ", error" + error);
    }

    private void showLog(String msg) {
        final int i = Log.i("GCMTEST", msg);
    }

}
