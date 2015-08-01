package org.antennae.notifyapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import org.antennae.notifyapp.adapters.AlertAdapter;
import org.antennae.gcmtests.gcmtest.R;
import org.antennae.notifyapp.model.Alert;
import org.antennae.notifyapp.model.AlertSeverityEnum;
import org.antennae.android.common.gcm.GcmWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class MainActivity extends ActionBarActivity {

    public static final String EXTRA_MESSAGE = "message";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    public static final String ALERT_PARAM = "alert";
    public static final String ALERT_NOTIFICATION_INTENT_FILTER = "ALERT_NOTIFICATION_INTENT_FILTER";

    ListView lvMessages;
    private List<Alert> alerts;
    private AlertAdapter alertsAdapter;

    Context context;
    AtomicInteger msgId = new AtomicInteger();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        context.getSharedPreferences("ANTENNAE", MODE_PRIVATE);

        GcmWrapper gcmwrapper = new GcmWrapper(context);
        String registrationId = gcmwrapper.getRegistrationId();

        if( registrationId == null ){
            gcmwrapper.registerWithGcmAsync();
        }

        setContentView(R.layout.activity_main);

        lvMessages = (ListView) findViewById(R.id.lvMessages);

        populateAlerts();

        lvMessages.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, MessageDetailActivity.class);
                i.putExtra(ALERT_PARAM, alerts.get(position));
                startActivity(i);
            }
        });
    }

    private void populateAlerts() {
        alerts = new ArrayList<Alert>();

        Alert alert1 = new Alert("High I/O on ADP Apps", "ADP backend has abnormally high IO", AlertSeverityEnum.HIGH.toString(), "Join the bridge on 1-873-555-3846 #556");
        Alert alert2 = new Alert("MySQL index issue", "MySql index is slower on quote system", AlertSeverityEnum.SEVERE.toString(), "Join the bridge on 1-873-555-3846 #598");

        alerts.add( alert1 );
        alerts.add( alert2 );

        alertsAdapter = new AlertAdapter(this, alerts);

        // Attach the adapter to a ListView
        lvMessages.setAdapter(alertsAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i("TAG", "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }
}
