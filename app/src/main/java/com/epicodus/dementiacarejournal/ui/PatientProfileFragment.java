package com.epicodus.dementiacarejournal.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.epicodus.dementiacarejournal.R;
import com.epicodus.dementiacarejournal.models.Patient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PatientProfileFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.saveButton) Button mSaveButton;
    @Bind(R.id.firstNameEditText) EditText mFirstName;
    @Bind(R.id.lastNameEditText) EditText mLastName;
    @Bind(R.id.dobEditText) EditText mDob;
    @Bind(R.id.doctorName) EditText mDrName;
    @Bind(R.id.doctorEmail)EditText mDrEmail;
    @Bind(R.id.doctorPhone) EditText mDrPhone;

    private FirebaseAuth mAuth;
    private Button saveButton;
    private String firstName;
    private String lastName;
    private String dob;
    private String drName;
    private String drEmail;
    private String drPhone;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patient_profile, container, false);
        ButterKnife.bind(this, view);

        saveButton = (Button) view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mSaveButton) {
            createNewPatient();
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }


//        TODO add functionality to save entered information into database as Patient object

    }

    private void createNewPatient() {
        firstName = mFirstName.getText().toString().trim();
        lastName = mLastName.getText().toString().trim();
        dob = mDob.getText().toString().trim();
        drName = mDrName.getText().toString().trim();
        drEmail = mDrEmail.getText().toString().trim();
        drPhone = mDrPhone.getText().toString().trim();

        Patient patient = new Patient(firstName, lastName, dob, drName, drEmail, drPhone);
        FirebaseUser user = mAuth.getCurrentUser();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(user.getUid()).child("patient");
        ref.setValue(patient);
        Toast.makeText(getActivity(), "Patient profile updated", Toast.LENGTH_LONG).show();
    }

}
