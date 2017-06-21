package com.epicodus.dementiacarejournal.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.epicodus.dementiacarejournal.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ActivityLog extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.saveButton) Button mSaveButton;

    ListView listview;
    String[] ListViewItems = new String[] {
            "Sleeping",
            "Watching TV",
            "Shopping"
    };

    SparseBooleanArray sparseBooleanArray ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior_log);

        ButterKnife.bind(this);
        mSaveButton.setOnClickListener(this);

        listview = (ListView)findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (ActivityLog.this,
                        android.R.layout.simple_list_item_multiple_choice,
                        android.R.id.text1, ListViewItems );

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO 6/30 Save selected items to the database with a timestamp

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

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == mSaveButton) {
            Intent intent = new Intent(ActivityLog.this, MainActivity.class);
            startActivity(intent);
        }
    }
}