package com.epicodus.dementiacarejournal.models;


import java.util.ArrayList;

public class Patient {
    private String mFirstName;
    private String mLastName;
    private String mDob;
    private String mDrName;
    private String mDrEmail;
    private String mDrPhone;
//    private ArrayList<String> mMedications = new ArrayList<>();
//    private ArrayList<String> mLogs = new ArrayList<>();

    public Patient(String firstName, String lastName, String DOB, String DrName, String DrEmail, String DrPhone) {
        this.mFirstName = firstName;
        this.mLastName = lastName;
        this.mDob = DOB;
        this.mDrName = DrName;
        this.mDrEmail = DrEmail;
        this.mDrPhone = DrPhone;
//        this.mMedications = medications;
//        this.mLogs = logs;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getDob() {
        return mDob;
    }

    public String getDrName() {
        return mDrName;
    }

    public String getDrEmail() {
        return mDrEmail;
    }

    public String getDrPhone() {
        return mDrPhone;
    }

//    public ArrayList<String> getMedications() {
//        return mMedications;
//    }
//
//    public ArrayList<String> getLogs() {
//        return mLogs;
//    }

}
