package com.example.mydoctor;

public class Appoinment {

    private String appoinmentId;
    private String drId;
    private String userId;
    private String date;
    private String appoinmentStatus;
    private String remarks;

    public Appoinment(String appoinmentId, String drId, String userId, String date, String appoinmentStatus, String remarks) {
        this.appoinmentId = appoinmentId;
        this.drId = drId;
        this.userId = userId;
        this.date = date;
        this.appoinmentStatus = appoinmentStatus;
        this.remarks = remarks;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAppoinmentStatus() {
        return appoinmentStatus;
    }

    public void setAppoinmentStatus(String appoinmentStatus) {
        this.appoinmentStatus = appoinmentStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
