package com.epicodus.dementiacarejournal.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.epicodus.dementiacarejournal.R;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfileFragment extends Fragment implements View.OnClickListener {
    private Button saveButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //TODO add functionality to pull user data to be displayed in the editText fields;

        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        saveButton = (Button) view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
