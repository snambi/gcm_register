package org.antennae.notifyapp.events;

import org.antennae.notifyapp.model.Alert;
import org.antennea.notifyapp.listeners.AlertReceivedListener;

import java.util.ArrayList;
import java.util.List;

/**
 * EventManager processes events through various listeners.
 *
 * It contains methods that relate to
 * <ol>
 *     <li>process events</li>
 *     <li>register and unregister listeners</li>
 * </ol>
 */
public class EventManager {

    private List<AlertReceivedListener> alertReceivedListenerList = new ArrayList<AlertReceivedListener>();

    private static EventManager SINGLETON = null;

    private EventManager(){
    }

    public static EventManager getInstance(){

        if( SINGLETON == null ){
            SINGLETON = new EventManager();
        }

        return SINGLETON;
    }

    /**************************************************************************
     * process events
     * methods following this marker process the events received, through the listeners
     *
    ***************************************************************************/

    /**
     * process received alerts
     * @param alert
     */
    public void processAlertsReceived( Alert alert ){
        if( alertReceivedListenerList != null ){
            for( AlertReceivedListener listener : alertReceivedListenerList ){
                listener.onReceive(alert);
            }
        }
    }


    /**************************************************************************
     *  manage listeners
     *
     *
     *************************************************************************/

    /**
     * Register the AlertReceivedListener
     *
     * This adds one listener only once.
     */
    public void registerAlertReceivedListener( AlertReceivedListener listner){
        if( alertReceivedListenerList != null ){
            if( alertReceivedListenerList.indexOf(listner) == -1 ){
                alertReceivedListenerList.add(listner);
            }
        }
    }

    /**
     * Un-Register the AlertReceivedListener
     */
    public void unregisterAlertReceivedListener( AlertReceivedListener listener ){
        if( alertReceivedListenerList != null ){
            for( AlertReceivedListener l : alertReceivedListenerList ){
                if( listener == l ){
                    alertReceivedListenerList.remove(listener);
                }
            }
        }
    }
}
