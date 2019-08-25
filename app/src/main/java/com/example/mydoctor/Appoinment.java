package com.example.mydoctor;

public class Appoinment {

    private String appoinmentId;
    private String drId;
    private String userId;
    private String mobilenumber;
    private String date;
    private String time;
    private String appoinmentStatus;


    public Appoinment() {
    }

    public Appoinment(String appoinmentId, String drId, String userId, String mobilenumber, String date, String time, String appoinmentStatus) {
        this.appoinmentId = appoinmentId;
        this.drId = drId;
        this.userId = userId;
        this.mobilenumber = mobilenumber;
        this.date = date;
        this.time = time;
        this.appoinmentStatus = appoinmentStatus;
    }

    public String getAppoinmentId() {
        return appoinmentId;
    }

    public void setAppoinmentId(String appoinmentId) {
        this.appoinmentId = appoinmentId;
    }

    public String getDrId() {
        return drId;
    }

    public void setDrId(String drId) {
        this.drId = drId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAppoinmentStatus() {
        return appoinmentStatus;
    }

    public void setAppoinmentStatus(String appoinmentStatus) {
        this.appoinmentStatus = appoinmentStatus;
    }
}
