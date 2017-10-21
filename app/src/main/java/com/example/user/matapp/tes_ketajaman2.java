package com.example.user.matapp;

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

public class tes_ketajaman2 extends AppCompatActivity {

    int acak = 0;
    TextView textViewSoal;
    EditText editTextJawaban;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tes_ketajaman2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        textViewSoal = (TextView) findViewById(R.id.soalKetajaman);
        acak = (int) (Math.random()*1000);
        String ez = acak+"";
        textViewSoal.setText(ez);



        final Button btnOpenPopup = (Button)findViewById(R.id.buttonOK);
        btnOpenPopup.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                LayoutInflater layoutInflater
                        = (LayoutInflater)getBaseContext()
                        .getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.pop_up_ketajaman, null);
                final PopupWindow popupWindow = new PopupWindow(
                        popupView,
                        LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT);

                Button btnDismiss = (Button)popupView.findViewById(R.id.dismiss);
                btnDismiss.setOnClickListener(new Button.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        popupWindow.dismiss();
                    }});

                popupWindow.showAsDropDown(textViewSoal);

            }});


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


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
