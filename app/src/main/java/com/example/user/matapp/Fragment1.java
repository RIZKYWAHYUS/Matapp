package com.example.user.matapp;

/**
 * Created by user on 06/10/2017.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.nio.BufferUnderflowException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Belal on 18/09/16.
 */


public class Fragment1 extends Fragment {

    public static String nama="";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        Intent i = getActivity().getIntent();
        Bundle b = i.getExtras();
        if(b!=null){
            nama = (String) b.get("namaUser");
        }

        initViews(view);



        return view;
}


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Menu 1");

    }





    private ArrayList countries;
    private ArrayList gambar;

    private void initViews(View v){
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        countries = new ArrayList<>();
        countries.add("Profile");
        countries.add("Leaderboard");
        countries.add("Tes Ketajaman");
        countries.add("Tes Kejelian");
        countries.add("Tes Warna");
        countries.add("Statistik");
        countries.add("Yuk Donasi");


        gambar = new ArrayList<>();
        gambar.add(R.drawable.ic_profileman);
        gambar.add(R.drawable.ic_leaderboard);
        gambar.add(R.drawable.ic_ketajaman);
        gambar.add(R.drawable.ic_kejelian);
        gambar.add(R.drawable.ic_warna);
        gambar.add(R.drawable.ic_statistic);
        gambar.add(R.drawable.ic_donation);

        RecyclerView.Adapter adapter = new DataAdapter(countries, gambar);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getActivity().getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

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
                intent = new Intent(getActivity().getApplicationContext(), ProfileAsli.class);
                intent.putExtra("namaUser", nama);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(getActivity().getApplicationContext(), LeaderboardAsli.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(getActivity().getApplicationContext(), tesKetajaman.class);
                startActivity(intent);
                break;
            case 3 :
                intent = new Intent(getActivity().getApplicationContext(), TesKejalian.class);
                startActivity(intent);
                break;
            case 4 :
                intent = new Intent(getActivity().getApplicationContext(), TesKewarnaan.class);
                startActivity(intent);
                break;
            case 5:
                intent = new Intent(getActivity().getApplicationContext(), Statistika.class);
                startActivity(intent);
                break;
            case 6:
                intent = new Intent(getActivity().getApplicationContext(), Donasi.class);
                startActivity(intent);
                break;
            default:
                Toast.makeText(getActivity().getApplicationContext(), (CharSequence) countries.get(position), Toast.LENGTH_SHORT).show();
                break;
            }



        }




    }




