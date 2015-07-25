package org.antennea.android.transport;

import com.google.gson.Gson;

/**
 * Created by nsankaran on 6/22/15.
 */
public class AppInfo {

    private String gcmRegistrationId;
    private String apnsRegistrationId;

    private int appVersion;
    private String appId;
    private long firstInstallTime;
    private long lastUpdateTime;

    public String getGcmRegistrationId() {
        return gcmRegistrationId;
    }

    public void setGcmRegistrationId(String gcmRegistrationId) {
        this.gcmRegistrationId = gcmRegistrationId;
    }

    public String getApnsRegistrationId() {
        return apnsRegistrationId;
    }

    public void setApnsRegistrationId(String apnsRegistrationId) {
        this.apnsRegistrationId = apnsRegistrationId;
    }

    public int getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(int appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public long getFirstInstallTime() {
        return firstInstallTime;
    }

    public void setFirstInstallTime(long firstInstallTime) {
        this.firstInstallTime = firstInstallTime;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson( this);
    }

    public static AppInfo fromJson( String json ){
        Gson gson = new Gson();
        return gson.fromJson(json, AppInfo.class);
    }

}
