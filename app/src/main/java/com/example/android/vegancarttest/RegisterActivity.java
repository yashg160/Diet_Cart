package com.example.android.vegancarttest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.internal.firebase_auth.zzep;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.zzu;
import com.google.firebase.auth.zzv;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RegisterActivity extends AppCompatActivity {

    private String mPhoneNumber;
    private EditText phoneNumberEdit;
    private Button mRegisterButton;
    private String TAG = "Phone verification";
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private ProgressBar progressBar;

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i(TAG, "signInWithCredential:success");

                            Intent intent = new Intent(RegisterActivity.this, Choice.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            progressBar.setVisibility(View.INVISIBLE);
                            startActivity(intent);
                            FirebaseUser user = task.getResult().getUser();
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.i(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential credential) {
            Log.i(TAG, "onVerificationCompleted:" + credential);
            Toast.makeText(getApplicationContext(), "Verified. Continue", Toast.LENGTH_LONG).show();
            signInWithPhoneAuthCredential(credential);
            if(credential==null){ Log.i(TAG, "Phone verification-Null credential");}
            else{ Log.i(TAG, "Phone verification-non null credential");}

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Log.i(TAG, "onVerificationFailed", e);

            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                Toast.makeText(getApplicationContext(), "Invalid phone number", Toast.LENGTH_LONG).show();
            } else if (e instanceof FirebaseTooManyRequestsException) {
                Toast.makeText(getApplicationContext(), "Too many requests. Please wait", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "Verification failed. Check the internet connection", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {

            Log.i(TAG, "onCodeSent:" + verificationId);
            Toast.makeText(getApplicationContext(), "Code sent", Toast.LENGTH_LONG).show();
            // Save verification ID and resending token so we can use them later
            mVerificationId = verificationId;
            mResendToken = token;
        }
    };

    private void startPhoneVerification() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                mPhoneNumber,
                60,
                TimeUnit.SECONDS,
                this,
                mCallbacks);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        phoneNumberEdit = findViewById(R.id.phone_number_edit);
        progressBar = findViewById(R.id.progress_bar);
        mRegisterButton = findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
                try {
                    mPhoneNumber = "+91" + phoneNumberEdit.getText().toString();

                    if(mPhoneNumber.length() != 13){
                        Toast.makeText(getApplicationContext(), "Invalid phone number", Toast.LENGTH_LONG).show();
                    }
                    else{

                        startPhoneVerification();
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Required Field", Toast.LENGTH_LONG).show();

                }
            }
        });

    }

}
