package org.antennae.gcmtests.gcmtest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.antennea.gcm.GcmWrapper;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;


public class MainActivity extends ActionBarActivity {

    public static final String EXTRA_MESSAGE = "message";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    GoogleCloudMessaging gcm;
    String regid;
    Context context;
    AtomicInteger msgId = new AtomicInteger();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        context = getApplicationContext();
        // GcmWrapper startup


//        if( checkPlayServices() ){
//            Intent intent = new Intent(this, MyGcmIntentService.class);
//            startService(intent);
//        }
        GcmWrapper gcmwrapper = new GcmWrapper(context);
        String registrationId = gcmwrapper.getRegId();

        Toast.makeText(context, "REG_ID = "+ registrationId, Toast.LENGTH_SHORT).show();
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
     * Gets the current registration ID for application on GcmWrapper service, if there
     * is one.
     * <p>
     * If result is empty, the app needs to register.
     *
     * @return registration ID, or empty string if there is no existing
     *         registration ID.
     */
    private String getRegistrationId(Context context)
    {
        final SharedPreferences prefs = getGcmPreferences(context);
        String registrationId = prefs.getString(Globals.PREFS_PROPERTY_REG_ID, "");
        if (registrationId == null || registrationId.equals(""))
        {
            Log.i(Globals.TAG, "Registration not found.");
            return "";
        }
        // Check if app was updated; if so, it must clear the registration ID
        // since the existing regID is not guaranteed to work with the new
        // app version.
        int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
        int currentVersion = getAppVersion(context);
        if (registeredVersion != currentVersion)
        {
            Log.i(Globals.TAG, "App version changed.");
            return "";
        }
        return registrationId;
    }


    /**
     * @return Application's {@code SharedPreferences}.
     */
    private SharedPreferences getGcmPreferences(Context context)
    {
        // This sample app persists the registration ID in shared preferences,
        // but how you store the regID in your app is up to you.
        return getSharedPreferences(Globals.PREFS_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Sends the registration ID to the 3rd party server via an upstream
     * GcmWrapper message. Ideally this would be done via HTTP to guarantee success or failure
     * immediately, but it would require an HTTP endpoint.
     */
    private void sendRegistrationIdToBackend()
    {
        Log.d(Globals.TAG, "REGISTER USERID: " + regid);
        //String name = ((EditText)findViewById(R.id.name_edittext)).getText().toString();
        new AsyncTask<String, Void, String>()
        {
            @Override
            protected String doInBackground(String... params) {

                String msg = "";
                try
                {
                    Bundle data = new Bundle();
                    data.putString("name", params[0]);
                    data.putString("action", "com.antoinecampbell.gcmdemo.REGISTER");
                    String id = Integer.toString(msgId.incrementAndGet());
                    gcm.send(Globals.GCM_SENDER_ID + "@gcm.googleapis.com", id, Globals.GCM_TIME_TO_LIVE, data);
                    msg = "Sent registration";
                }
                catch (IOException ex)
                {
                    msg = "Error :" + ex.getMessage();
                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg)
            {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }

        }.execute("NAME : Nambi");
    }

    /**
     * @return Application's version code from the {@code PackageManager}.
     */
    private static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
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
