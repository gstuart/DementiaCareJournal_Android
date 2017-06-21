package com.epicodus.dementiacarejournal.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.epicodus.dementiacarejournal.R;

public class BehaviorLog extends AppCompatActivity {

    ListView listview;
    String[] ListViewItems = new String[] {
            "Agitation",
            "Eating",
            "Good hygiene",
            "Hallucinations",
            "Incontinence",
            "Paranoia",
            "Repetitive speech or actions (Perseveration)",
            "Self dressing",
            "Sexually inappropriate behavior",
            "Shadowing, imitates and follows the caregiver",
            "Sleeplessness/Sundowning",
            "Uncooperative and resistant",
            "Verbal outbursts",
            "Wandering"
    };

    SparseBooleanArray sparseBooleanArray ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior_log);

        listview = (ListView)findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (BehaviorLog.this,
                        android.R.layout.simple_list_item_multiple_choice,
                        android.R.id.text1, ListViewItems );

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                sparseBooleanArray = listview.getCheckedItemPositions();

                String ValueHolder = "" ;

                int i = 0 ;
                while (i < sparseBooleanArray.size()) {
                    if (sparseBooleanArray.valueAt(i)) {

                        ValueHolder += ListViewItems [ sparseBooleanArray.keyAt(i) ] + ",";
                    }
                    i++ ;
                }

                ValueHolder = ValueHolder.replaceAll("(,)*$", "");

                Toast.makeText(BehaviorLog.this, "ListView Selected Values = " + ValueHolder, Toast.LENGTH_LONG).show();

            }
        });

    }
}