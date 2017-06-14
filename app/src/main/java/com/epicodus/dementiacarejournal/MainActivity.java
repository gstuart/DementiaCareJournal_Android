package com.epicodus.dementiacarejournal;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    @Bind (R.id.aboutButton) Button mAboutButton;
    @Bind (R.id.logInButton) Button mLogInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface text = Typeface.createFromAsset(getAssets(), "Roboto/Roboto-Regular.ttf");
            mAboutButton.setTypeface(text);
            mLogInButton.setTypeface(text);

        mAboutButton.setOnClickListener(this);
        mLogInButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mAboutButton) {

        }
        if (v == mLogInButton) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
