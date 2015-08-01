package org.antennea.notifyapp.listeners;

import org.antennae.notifyapp.model.Alert;

public interface AlertReceivedListener {
    public void onReceive( Alert alert );
}
