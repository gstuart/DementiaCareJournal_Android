package com.epicodus.dementiacarejournal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.signInButton) Button mSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }
}
