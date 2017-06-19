package com.epicodus.dementiacarejournal.models;


import java.util.ArrayList;

public class Log {
    private ArrayList<String> mActivities = new ArrayList<>();
    private ArrayList<String> mMoods = new ArrayList<>();
    private ArrayList<String> mBehaviors = new ArrayList<>();

    public Log(ArrayList<String> activities, ArrayList<String> moods, ArrayList<String> behaviors) {
        this.mActivities = activities;
        this.mBehaviors = behaviors;
        this.mMoods = moods;
    }

    public ArrayList<String> getActivities() {
        return mActivities;
    }

    public ArrayList<String> getMoods() {
        return mMoods;
    }

    public ArrayList<String> getBehaviors() {
        return mBehaviors;
    }
}
