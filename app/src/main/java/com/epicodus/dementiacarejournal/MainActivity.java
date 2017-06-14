package com.epicodus.dementiacarejournal;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
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

    }
}
