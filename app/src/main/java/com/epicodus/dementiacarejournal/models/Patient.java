package com.epicodus.dementiacarejournal.models;


import java.util.ArrayList;

public class Patient {
    private String mFirstName;
    private String mLastName;
    private String mDob;
    private ArrayList<String> mMedications = new ArrayList<>();
    private ArrayList<String> mLogs = new ArrayList<>();

    public Patient(String firstName, String lastName, String DOB, ArrayList<String> medications, ArrayList<String> logs) {
        this.mFirstName = firstName;
        this.mLastName = lastName;
        this.mDob = DOB;
        this.mMedications = medications;
        this.mLogs = logs;
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

    public ArrayList<String> getMedications() {
        return mMedications;
    }

    public ArrayList<String> getLogs() {
        return mLogs;
    }

}
