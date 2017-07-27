package com.epicodus.dementiacarejournal.ui;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.epicodus.dementiacarejournal.R;
import com.epicodus.dementiacarejournal.models.Patient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BehaviorLog extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.saveButton) Button mSaveButton;

    private FirebaseAuth mAuth;
    private DatabaseReference mPatient;
    ArrayList <String> ValueHolder;

    ListView listview;
    ArrayList<String> ListViewItems = new ArrayList<String>();
// todo create an array which contains the following :
//    ListViewItems.add(
//            "Agitation",
//            "Eating",
//            "Good hygiene",
//            "Hallucinations",
//            "Incoherient",
//            "Incontinence",
//            "Lethargic",
//            "Silent",
//            "Paranoia",
//            "Repetitive speech or actions (Perseveration)",
//            "Self dressing",
//            "Sexually inappropriate behavior",
//            "Shadowing, imitates and follows the caregiver",
//            "Sleeplessness/Sundowning",
//            "Uncooperative and resistant",
//            "Verbal outbursts",
//            "Wandering");

    SparseBooleanArray sparseBooleanArray ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        ButterKnife.bind(this);

        mSaveButton.setOnClickListener(this);

        //  add items to ArrayList ListViewItems
        ListViewItems.add("Agitation");
        ListViewItems.add("Eating");
        ListViewItems.add("Good hygiene");
        ListViewItems.add("Hallucinations");
        ListViewItems.add("Incoherient");
        ListViewItems.add("Incontinence");
        ListViewItems.add("Silent");
        ListViewItems.add("Paranoia");
        ListViewItems.add("Repetitive speech or actions (Perseveration)");
        ListViewItems.add("Self dressing");
        ListViewItems.add("Sexually inappropriate behavior");
        ListViewItems.add("Shadowing, imitates and follows the caregiver");
        ListViewItems.add("Sleeplessness/Sundowning");
        ListViewItems.add("Uncooperative and resistant");
        ListViewItems.add("Verbal outbursts");
        ListViewItems.add("Wandering");

        listview = (ListView)findViewById(R.id.listView);
        mAuth = FirebaseAuth.getInstance();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (BehaviorLog.this,
                        android.R.layout.simple_list_item_multiple_choice,
                        android.R.id.text1, ListViewItems );

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sparseBooleanArray = listview.getCheckedItemPositions();

                ValueHolder = new ArrayList<String>();

                int i = 0 ;
                while (i < sparseBooleanArray.size()) {
                    if (sparseBooleanArray.valueAt(i)) {
                        ValueHolder.add(ListViewItems.get(i));
                    }
                    i++ ;
                }
//                ValueHolder = ValueHolder.replaceAll("(,)*$", "");
                Log.i("Array contents", ValueHolder.toString());
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == mSaveButton) {
            savePatientBehaviors();
            Toast.makeText(BehaviorLog.this, "Selected Items Logged", Toast.LENGTH_SHORT).show();
        }
    }

    private void savePatientBehaviors() {
        FirebaseUser user = mAuth.getCurrentUser();
        DatabaseReference ref = FirebaseDatabase
                .getInstance()
                .getReference(user.getUid())
                .child("patient")
                .child("behaviors");

        ref.push().setValue(ValueHolder);
        Log.i("SAVE METHOD    ", ValueHolder.toString());
        Intent intent = new Intent(BehaviorLog.this, EmotionLog.class);
        startActivity(intent);
    }
}