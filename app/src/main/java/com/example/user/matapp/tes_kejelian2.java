package com.example.user.matapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class tes_kejelian2 extends AppCompatActivity implements View.OnClickListener{
    Button _start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tes_kejelian2);
        _start = (Button) findViewById(R.id.buttonStart);
        _start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == _start){
            Intent intent = new Intent(tes_kejelian2.this,TesKejalian.class);
            startActivity(intent);
        }
    }
}
