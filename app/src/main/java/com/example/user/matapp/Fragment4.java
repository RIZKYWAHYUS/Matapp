package com.example.user.matapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

/**
 * Created by Belal on 18/09/16.
 */


public class Fragment4 extends Fragment implements View.OnClickListener{

    Switch switch1 ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_4, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Menu 4");
        switch1 = (Switch) view.findViewById(R.id.switch1);
        switch1.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
            if (switch1.isChecked()){
                Intent in = new Intent(getActivity().getApplicationContext(), MainActivity_jarak.class);
                startActivity(in);
            }
    }
}