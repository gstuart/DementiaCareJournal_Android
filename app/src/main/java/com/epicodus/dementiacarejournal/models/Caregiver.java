package com.epicodus.dementiacarejournal.models;

public class Caregiver {
    private String mName;
    private String mEmail;
    private String mPhone;

    public Caregiver(String name, String email, String phone) {
        this.mName = name;
        this.mEmail = email;
        this.mPhone = phone;
    }

    public String getName() {
        return mName;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getPhone() {
        return mPhone;
    }
}
