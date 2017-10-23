package com.example.user.matapp;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TesKewarnaan extends AppCompatActivity implements View.OnClickListener{

    TextView textViewSoal;
    EditText editTextJawaban;
    Button btnOke,btn;
    //keperluan popup
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tes_kewarnaan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnOke = (Button)findViewById(R.id.button);
        btnOke.setOnClickListener(this);
    }

    private void DialogForm() {
        dialog = new AlertDialog.Builder(TesKewarnaan.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.pop_up_ketajaman, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.show();
        dialog.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // do something
            }
        });



    }

    @Override
    public void onClick(View v) {
        if (v == btnOke){

            DialogForm();
        }
    }
}
