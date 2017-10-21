package com.example.user.matapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class LeaderboardAsli extends AppCompatActivity implements View.OnClickListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard_asli);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        initViews();



    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    private ArrayList countries;
    private ArrayList gambar;
    private ArrayList no;
    private ArrayList lcertificate;
    private ArrayList lmedal;
    private ArrayList lcoins;

    private void initViews(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.leader_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        no = new ArrayList<>();
        no.add("1");
        no.add("2");
        no.add("3");
        no.add("4");
        no.add("5");
        no.add("6");
        no.add("7");

        countries = new ArrayList<>();
        countries.add("Sundar Pichai");
        countries.add("Van Persie");
        countries.add("Ibrahimovic");
        countries.add("Wayne Rooney");
        countries.add("Mesut Ozil");
        countries.add("C.Ronaldo");
        countries.add("Lionel Messi");



        gambar = new ArrayList<>();
        gambar.add(R.drawable.sundar);
        gambar.add(R.drawable.van);
        gambar.add(R.drawable.ibrahimovic);
        gambar.add(R.drawable.rooney);
        gambar.add(R.drawable.ozil);
        gambar.add(R.drawable.ronaldo);
        gambar.add(R.drawable.messi);


        lcertificate = new ArrayList<>();
        lcertificate.add("1");
        lcertificate.add("1");
        lcertificate.add("1");
        lcertificate.add("1");
        lcertificate.add("1");
        lcertificate.add("1");
        lcertificate.add("1");

        lmedal = new ArrayList<>();
        lmedal.add("5");
        lmedal.add("5");
        lmedal.add("4");
        lmedal.add("4");
        lmedal.add("3");
        lmedal.add("3");
        lmedal.add("2");

        lcoins = new ArrayList<>();
        lcoins.add("10000");
        lcoins.add("8000");
        lcoins.add("7000");
        lcoins.add("6000");
        lcoins.add("5000");
        lcoins.add("4000");
        lcoins.add("3000");



        RecyclerView.Adapter adapter = new DataLeaderboard(countries, gambar,no,lcertificate,lmedal,lcoins);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

                @Override public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if(child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);
                    mulaiSubMenu(position);

                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }




    public void mulaiSubMenu(int position){
        Intent intent;
        switch (position) {
            case 0:
                Toast.makeText(getApplicationContext(), (CharSequence) countries.get(position), Toast.LENGTH_SHORT).show();

                break;
            case 1:
                Toast.makeText(getApplicationContext(), (CharSequence) countries.get(position), Toast.LENGTH_SHORT).show();

                break;
            case 2:
                Toast.makeText(getApplicationContext(), (CharSequence) countries.get(position), Toast.LENGTH_SHORT).show();
                break;
            case 3 :
                Toast.makeText(getApplicationContext(), (CharSequence) countries.get(position), Toast.LENGTH_SHORT).show();
                break;
            case 4 :
                intent = new Intent(getApplicationContext(), Ozil.class);
                startActivity(intent);
                break;
            case 5:
                Toast.makeText(getApplicationContext(), (CharSequence) countries.get(position), Toast.LENGTH_SHORT).show();
                break;
            case 6:
                Toast.makeText(getApplicationContext(), (CharSequence) countries.get(position), Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(getApplicationContext(), (CharSequence) countries.get(position), Toast.LENGTH_SHORT).show();
                break;
        }



    }


}

