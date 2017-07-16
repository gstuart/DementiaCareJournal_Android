package com.epicodus.dementiacarejournal.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.epicodus.dementiacarejournal.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
//    @Bind(R.id.profileButton) Button mProfileButton;
    @Bind(R.id.emailButton) Button mEmailButton;
    @Bind(R.id.journalButton) Button mJournalButton;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        mProfileButton.setOnClickListener(this);
        mJournalButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == mJournalButton) {
            Intent intent = new Intent(MainActivity.this, BehaviorLog.class);
            startActivity(intent);
        }
//        if (v == mProfileButton) {
//            Intent intent = new Intent(MainActivity.this, Profile.class);
//            startActivity(intent);
//        }
        if (v == mEmailButton) {
            sendEmail();
        }
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
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    protected void sendEmail() {
        Log.i("Send email", "");
        String[] TO = {"recipient"};
        String[] Subject = {"subject"};
        String[] MessageBody = {"Message Body"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, Subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, MessageBody);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail...."));
            finish();
            Log.i("Finished sending email", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed. Please Try again after installation.", Toast.LENGTH_LONG).show();
        }

    }

}
