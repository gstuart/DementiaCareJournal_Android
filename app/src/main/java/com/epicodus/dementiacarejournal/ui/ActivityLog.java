package com.epicodus.dementiacarejournal.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.epicodus.dementiacarejournal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ActivityLog extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.saveButton) Button mSaveButton;

    private FirebaseAuth mAuth;
    private DatabaseReference mPatient;
    ArrayList<String> ValueHolder;

    ListView listview;
    ArrayList<String> ListViewItems = new ArrayList<String>();

    SparseBooleanArray sparseBooleanArray ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        ButterKnife.bind(this);

        mSaveButton.setOnClickListener(this);

        ListViewItems.add("Caring For Pet Or Person");
        ListViewItems.add("Cooking");
        ListViewItems.add("Eating Out");
        ListViewItems.add("Exercising");
        ListViewItems.add("Games");
        ListViewItems.add("Hobby");
        ListViewItems.add("Interacting With Pet");
        ListViewItems.add("Occupational Therapy");
        ListViewItems.add("Physical Therapy");
        ListViewItems.add("Reading");
        ListViewItems.add("Shopping");
        ListViewItems.add("Sleeping");
        ListViewItems.add("Socializing");
        ListViewItems.add("Walking");
        ListViewItems.add("Watching TV");
        ListViewItems.add("Yard Work");

        listview = (ListView) findViewById(R.id.listView);
        mAuth = FirebaseAuth.getInstance();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (ActivityLog.this,
                        android.R.layout.simple_list_item_multiple_choice,
                        android.R.id.text1, ListViewItems);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sparseBooleanArray = listview.getCheckedItemPositions();

                ValueHolder = new ArrayList<String>();

                int i = 0;
                while (i < sparseBooleanArray.size()) {
                    if (sparseBooleanArray.valueAt(i)) {
                        ValueHolder.add(ListViewItems.get(i));
                    }
                    i++;
                }

                Log.i("Array contents", ValueHolder.toString());
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == mSaveButton) {
            if (ValueHolder == null) {
                Toast.makeText(ActivityLog.this, "Please make a selection.", Toast.LENGTH_SHORT).show();
            } else {
                savePatientActivities();
                Toast.makeText(ActivityLog.this, "Selected Items Logged", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void savePatientActivities() {
        FirebaseUser user = mAuth.getCurrentUser();
        DatabaseReference ref = FirebaseDatabase
                .getInstance()
                .getReference(user.getUid())
                .child("patient")
                .child("logs")
                .child("activities");

        ref.push().setValue(ValueHolder);
        Log.i("  SAVE METHOD    ", ValueHolder.toString());

        Intent intent = new Intent(ActivityLog.this, MainActivity.class);
        startActivity(intent);
    }
}