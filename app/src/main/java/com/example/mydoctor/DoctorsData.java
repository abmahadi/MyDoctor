package com.example.mydoctor;

public class DoctorsData {
    private String doctorRegistrationNo;
    private String firstName;
    private String lastName;
    private String specialization;
    private String degree;
    private String city;
    private String area;
    private String street;
    private String startTime;
    private String endTime;
    private String specialNotice;

    public DoctorsData() {
    }

    public DoctorsData(String doctorRegistrationNo, String firstName, String lastName, String specialization, String degree, String city, String area, String street, String startTime, String endTime, String specialNotice) {
        this.doctorRegistrationNo = doctorRegistrationNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
        this.degree = degree;
        this.city = city;
        this.area = area;
        this.street = street;
        this.startTime = startTime;
        this.endTime = endTime;
        this.specialNotice = specialNotice;
    }

    public String getDoctorRegistrationNo() {
        return doctorRegistrationNo;
    }

    public void setDoctorRegistrationNo(String doctorRegistrationNo) {
        this.doctorRegistrationNo = doctorRegistrationNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSpecialNotice() {
        return specialNotice;
    }

    public void setSpecialNotice(String specialNotice) {
        this.specialNotice = specialNotice;
    }
}
