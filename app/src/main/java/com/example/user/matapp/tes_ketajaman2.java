package com.example.user.matapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;

public class tes_ketajaman2 extends AppCompatActivity implements View.OnClickListener{

    int acak = 0;
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
        setContentView(R.layout.activity_tes_ketajaman2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textViewSoal = (TextView) findViewById(R.id.soalKetajaman);
        acak = (int) (Math.random()*1000);
        String ez = acak+"";
        textViewSoal.setText(ez);



        btnOke = (Button)findViewById(R.id.buttonOK);
        btnOke.setOnClickListener(this);


    }


//    public void pencetJawaban(View v){
//        editTextJawaban = (EditText) findViewById(R.id.et_jawaban);
//        String jawabanS = editTextJawaban.getText().toString();
//
//        try {
//            int jawabanInt = Integer.parseInt(jawabanS);
//
//
//        }catch (Exception e){
//
//        }
//    }



    private void DialogForm() {
        dialog = new AlertDialog.Builder(tes_ketajaman2.this);
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
