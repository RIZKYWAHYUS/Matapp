package com.example.user.matapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "KONDISI USE";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;



    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("users");


        PengendaliAuth.mAuth = FirebaseAuth.getInstance();
        PengendaliAuth.mAuthListener = new FirebaseAuth.AuthStateListener() {


            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    PengendaliAuth.userId = user.getUid();
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }


    @Override
    public void onStart() {
        super.onStart();
        PengendaliAuth.mAuth.addAuthStateListener(PengendaliAuth.mAuthListener);
    }

//    @Override
//    public void onStop() {
//        super.onStop();
//        if (mAuthListener != null) {
//            mAuth.removeAuthStateListener(mAuthListener);
//        }
//    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);



        // [START sign_in_with_email]
        PengendaliAuth.mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user =PengendaliAuth.mAuth.getCurrentUser();

                            addUserChangeListener(user.getUid());

                            Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Mungkin kamu belom terdaftar atau email dan passwordmu salah :)",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // [START_EXCLUDE]

                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }


    public void aksiMasuk(View v){

        EditText et_nama = (EditText) findViewById(R.id.et_fullname);
        EditText et_pass = (EditText) findViewById(R.id.et_password);
//        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
        String email="", pass="";
        try {
            email =  et_nama.getText().toString();
            pass = et_pass.getText().toString();
            if(email.trim().equalsIgnoreCase("")||pass.trim().equalsIgnoreCase("")){
                Toast.makeText(this, "Tolong isi email dan password kamu", Toast.LENGTH_SHORT).show();
            }else{
                this.signIn(email, pass);
            }
        }catch (Exception e){
            Toast.makeText(this, "Tolong isi email dan password kamu", Toast.LENGTH_SHORT).show();
        }



//        intent.putExtra("namaUser", et_nama.getText().toString());
//
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//
//        startActivity(intent);


    }


    public void aksiDaftar(View v){
        Intent intent = new Intent(MainActivity.this, DaftarActivity.class);
        startActivity(intent);
    }


    private void addUserChangeListener(final String uid) {
        // User data change listener

        mFirebaseDatabase.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                // Check for null
                if (user == null) {
                    Log.e(TAG, "User data is null!");
                    return;
                }else if(uid == null){
                    Log.e(TAG, "User id is null!");
                    return;
                }
                PengendaliAuth.nama = user.nama;
                PengendaliAuth.email = user.email;
                PengendaliAuth.jenisKelamin = user.jenisKelamin;
                PengendaliAuth.noTelp = user.nomorTelp;
                PengendaliAuth.umur = user.umur;







            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });
    }



}
