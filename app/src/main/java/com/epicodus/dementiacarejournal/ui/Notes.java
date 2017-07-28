package com.epicodus.dementiacarejournal.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.epicodus.dementiacarejournal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Notes extends AppCompatActivity implements  View.OnClickListener {
    @Bind(R.id.noteEditText) EditText mNoteEditText;
    @Bind(R.id.saveButton) Button mSaveButton;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        ButterKnife.bind(this);
        mSaveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mSaveButton) {
            saveNote();
        }
    }

    private void saveNote() {
        FirebaseUser user = mAuth.getCurrentUser();
        DatabaseReference ref = FirebaseDatabase
                .getInstance()
                .getReference(user.getUid())
                .child("patient")
                .child("logs")
                .child("behaviors");

        ref.push().setValue(mNoteEditText);

        Intent intent = new Intent(Notes.this, MainActivity.class);
        startActivity(intent);
    }
}
