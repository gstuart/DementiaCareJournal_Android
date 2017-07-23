package com.epicodus.dementiacarejournal.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.epicodus.dementiacarejournal.R;
import com.epicodus.dementiacarejournal.models.Caregiver;
import com.epicodus.dementiacarejournal.ui.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.createUserButton) Button mCreateUserButton;
    @Bind(R.id.firstNameEditText) EditText mFirstNameEditText;
    @Bind(R.id.lastNameEditText) EditText mLastNameEditText;
    @Bind(R.id.phoneEditText) EditText mphoneEditText;
    @Bind(R.id.emailEditText) EditText mEmailEditText;
    @Bind(R.id.passwordEditText) EditText mPasswordEditText;
    @Bind(R.id.confirmPasswordEditText) EditText mConfirmPasswordEditText;

    public static final String TAG = CreateAccountActivity.class.getSimpleName();
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog mAuthProgressDialog;
    private String mDisplayName;
    private String mPhone;
    private String mEmail;
    private String mLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        createAuthStateListener();
        createAuthProgressDialog();

        mCreateUserButton.setOnClickListener(this);
    }

    private void createAuthProgressDialog() {
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("Authenticating with Firebase...");
        mAuthProgressDialog.setCancelable(false);
    }

    @Override
    public void onClick(View v) {
        createNewUser();
    }

    private void createAuthStateListener() {

        mAuthListener =new FirebaseAuth.AuthStateListener() {
             @Override
             public void onAuthStateChanged (@NonNull FirebaseAuth firebaseAuth){
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(CreateAccountActivity.this, Profile.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
             }
        };
    }

    private boolean isValidFirstName(String firstName) {
        if (firstName.equals("")) {
            mFirstNameEditText.setError("Please enter your name.");
            return false;
        }
        return true;
    }

    private boolean isValidLastName(String lastName) {
        if (lastName.equals("")) {
            mLastNameEditText.setError("Please enter your name.");
            return false;
        }
        return true;
    }

    private boolean isValidPhone(String phone) {
        if (phone.equals("")) {
            mphoneEditText.setError("Please enter your phone number");
            return false;
        }
        return true;
    }

    private boolean isValidEmail(String email) {
        boolean isGoodEmail = (email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isGoodEmail) {
            mEmailEditText.setError("Please enter a valid email address");
            return false;
        }
        return isGoodEmail;
    }

    private boolean isValidPassword(String password, String confirmPassword) {
        if (password.length() < 6 ) {
            mPasswordEditText.setError("Please create a password with at least 6 characters.");
            return false;
        } else if (!password.equals(confirmPassword)) {
            mConfirmPasswordEditText.setError("Passwords do not match");
            return false;
        }
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void createNewUser() {
        mDisplayName = mFirstNameEditText.getText().toString().trim();
        mLastName = mLastNameEditText.getText().toString().trim();
        mPhone = mphoneEditText.getText().toString().trim();
        mEmail = mEmailEditText.getText().toString().trim();
        final String password = mPasswordEditText.getText().toString().trim();
        String confirmPassword = mConfirmPasswordEditText.getText().toString().trim();

        boolean validEmail = isValidEmail(mEmail);
        boolean validFirstName = isValidFirstName(mDisplayName);
        boolean validLastName = isValidLastName(mLastName);
        boolean validPhone = isValidPhone(mPhone);

        boolean validPassword = isValidPassword(password, confirmPassword);
        if (!validEmail || !validFirstName || !validLastName|| !validPhone || !validPassword) return;

        mAuthProgressDialog.show();

        mAuth.createUserWithEmailAndPassword(mEmail, password).addOnCompleteListener(this, new OnCompleteListener< AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mAuthProgressDialog.dismiss();

                if (task.isSuccessful()) {
                    createCaregiver();
                    createFirebaseUserProfile(task.getResult().getUser());
                } else {
                    Log.w(TAG, "  createUserWithEmailAndPassword Method ", task.getException());
                    Toast.makeText(CreateAccountActivity.this, "Authentication failed. Please try again.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void createCaregiver() {
        Caregiver caregiver = new Caregiver(mDisplayName, mLastName, mEmail, mPhone);
        FirebaseUser user = mAuth.getCurrentUser();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(user.getUid()).child("caregiver");
        ref.setValue(caregiver);
    }

    public void createFirebaseUserProfile(final FirebaseUser user) {

        UserProfileChangeRequest addProfileName = new UserProfileChangeRequest.Builder()
                .setDisplayName(mDisplayName)
                .build();

        user.updateProfile(addProfileName).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, user.getDisplayName());
                }
            }
        });
    }



}
