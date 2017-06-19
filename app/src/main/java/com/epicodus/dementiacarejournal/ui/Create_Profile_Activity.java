package com.epicodus.dementiacarejournal.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.epicodus.dementiacarejournal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import butterknife.Bind;

public class Create_Profile_Activity extends AppCompatActivity {

    @Bind(R.id.userName) EditText caregiverName;
    @Bind(R.id.userEmail) EditText caregiverEmail;
    @Bind(R.id.userPassword) EditText caregiverPassword;
    @Bind(R.id.userPhone) EditText caregiverPhone;
    @Bind(R.id.patientFirstName) EditText patientFirstName;
    @Bind(R.id.patientLastName) EditText patientLastName;
    @Bind(R.id.patientDob) EditText patientDob;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__profile_);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(Create_Profile_Activity.this, MainActivity.class); //TODO change mainActivity to LogActivity
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

}
