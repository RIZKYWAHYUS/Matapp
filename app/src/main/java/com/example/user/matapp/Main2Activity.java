package com.example.user.matapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "di Main2Activity";
    String nama = "Matappers";

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    String namaLu = "", emailLu="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);






        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.Namadidrawer);
        TextView navEmail = (TextView) headerView.findViewById(R.id.Emaildidrawer);

        navUsername.setText(PengendaliAuth.nama);
        navEmail.setText(PengendaliAuth.email);

//        Intent i = getIntent();
//        Bundle b = i.getExtras();
//        if(b!=null){
//            nama = (String) b.get("namaUser");
//            if(!nama.trim().equalsIgnoreCase("")){
//                navUsername.setText(nama);
//                navEmail.setText(nama.toLowerCase().trim()+"@mail.com");
//            }
//        }

        navigationView.setNavigationItemSelectedListener(this);

        displaySelectedScreen(R.id.item_halaman_utama);


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }


        }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        displaySelectedScreen(item.getItemId());
        return true;
    }


    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.item_halaman_utama:
                fragment = new Fragment1();
                break;
            case R.id.item_akun:
                fragment = new Fragment2();
                break;
            case R.id.item_tentang:
                fragment = new Fragment3();
                break;
            case R.id.item_pengaturan:
                fragment = new Fragment4();
                break;
            case R.id.item_bagikan:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey Kamu! aku udah nggunain Matapp lo! Ayo ikut menjaga kesehatan mata menggunakan aplikasi Matapp! \n\nRegards, "+this.nama);
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Ayo ajak teman-temanmu !"));
                break;
            case R.id.item_saran:
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto: matappbcc@gmail.com"));
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "SARAN UNTUK MATAPP - "+this.nama);
                startActivity(Intent.createChooser(emailIntent, "Kami mengharapkan saranmu.."));

                break;

        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }




    private void addUserChangeListener() {
        // User data change listener
        mFirebaseDatabase.child(PengendaliAuth.userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                // Check for null
                if (user == null) {
                    Log.e(TAG, "User data is null!");
                    return;
                }

                namaLu = user.nama;
                emailLu = user.email;





            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });
    }



}
