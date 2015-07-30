package org.antennae.notifyapp.model;

import java.io.Serializable;
import java.util.Date;

import com.google.gson.Gson;

public class Alert implements Serializable {
    private static final long serialVersionUID = -3842436552095768406L;

    private String title;
    private String message;
    private String severity;
    private String action;
    private Date date= new Date();
    private long id = date.getTime();

    public Alert( String title, String msg, String severity, String action){
        this.title = title;
        this.message = msg;
        this.severity = severity;
        this.action = action;
    }

    public Alert(){
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this, Alert.class );
    }

    public static Alert fromJson( String json ){
        Gson gson = new Gson();
        return gson.fromJson( json, Alert.class);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("title=");
        sb.append(title);
        sb.append(",message=");
        sb.append(message);
        sb.append(",severity=");
        sb.append(severity);
        sb.append(",action=");
        sb.append(action);
        sb.append(",time=");
        sb.append(date.toString());
        return sb.toString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Alert other = (Alert) obj;
        if (id != other.id)
            return false;
        return true;
    }
}