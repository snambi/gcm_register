package org.antennea.android.transport;

/**
 * Created by nsankaran on 6/20/15.
 */
public class AppDetails {

    private String gcmRegistrationId;
    private DeviceInfo deviceInfo;
    private AppInfo appInfo;

    // do we need an appState?


    public AppInfo getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(AppInfo appInfo) {
        this.appInfo = appInfo;
    }

    public String getGcmRegistrationId() {
        return gcmRegistrationId;
    }

    public void setGcmRegistrationId(String gcmRegistrationId) {
        this.gcmRegistrationId = gcmRegistrationId;
    }

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }
}
