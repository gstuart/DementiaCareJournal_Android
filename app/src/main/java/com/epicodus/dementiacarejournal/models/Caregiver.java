package com.epicodus.dementiacarejournal.models;

public class Caregiver {
    private String mFirstName;
    private String mLastName;
    private String mEmail;
    private String mPhone;

    public Caregiver(String firstName, String lastName, String email, String phone) {
        this.mFirstName = firstName;
        this.mLastName = lastName;
        this.mEmail = email;
        this.mPhone = phone;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getPhone() {
        return mPhone;
    }
}
