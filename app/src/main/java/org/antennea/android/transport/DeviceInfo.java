package org.antennea.android.transport;

import com.google.gson.Gson;

/**
 * Created by nsankaran on 6/22/15.
 */
public class DeviceInfo {

    private String deviceId;
    private String softwareVersion;
    private String simSerialNumber;
    private String voiceMailNumber;
    private String simOperatorName;

    private String phoneNumber;
    private PhoneTypeEnum phoneType;
    private String networkCountryIso;
    private String networkOperatorId;
    private String networkOperatorName;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public String getSimSerialNumber() {
        return simSerialNumber;
    }

    public void setSimSerialNumber(String simSerialNumber) {
        this.simSerialNumber = simSerialNumber;
    }

    public String getVoiceMailNumber() {
        return voiceMailNumber;
    }

    public void setVoiceMailNumber(String voiceMailNumber) {
        this.voiceMailNumber = voiceMailNumber;
    }

    public String getSimOperatorName() {
        return simOperatorName;
    }

    public void setSimOperatorName(String simOperatorName) {
        this.simOperatorName = simOperatorName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PhoneTypeEnum getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(PhoneTypeEnum phoneType) {
        this.phoneType = phoneType;
    }

    public String getNetworkCountryIso() {
        return networkCountryIso;
    }

    public void setNetworkCountryIso(String networkCountryIso) {
        this.networkCountryIso = networkCountryIso;
    }

    public String getNetworkOperatorId() {
        return networkOperatorId;
    }

    public void setNetworkOperatorId(String networkOperatorId) {
        this.networkOperatorId = networkOperatorId;
    }

    public String getNetworkOperatorName() {
        return networkOperatorName;
    }

    public void setNetworkOperatorName(String networkOperatorName) {
        this.networkOperatorName = networkOperatorName;
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson( this);
    }

    public static DeviceInfo fromJson( String json ){
        Gson gson = new Gson();
        return gson.fromJson(json, DeviceInfo.class);
    }
}
