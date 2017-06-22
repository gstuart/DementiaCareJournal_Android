package com.epicodus.dementiacarejournal.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.epicodus.dementiacarejournal.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Notes extends AppCompatActivity implements  View.OnClickListener {
    @Bind(R.id.noteEditText) EditText mNoteEditText;
    @Bind(R.id.saveButton) Button mSaveButton;

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
            Intent intent = new Intent(Notes.this, MainActivity.class);
            Toast.makeText(Notes.this, "Note Logged", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
    }
}
