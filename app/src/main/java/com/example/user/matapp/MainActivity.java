package com.example.user.matapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void aksiMasuk(View v){
        Intent intent = new Intent(this.getApplicationContext(), Main2Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

        EditText et_nama = (EditText) findViewById(R.id.et_jawaban);
        intent.putExtra("namaUser", et_nama.getText().toString());

        startActivity(intent);
    }


    public void aksiDaftar(View v){
        Intent intent = new Intent(MainActivity.this, DaftarActivity.class);
        startActivity(intent);
    }
}
