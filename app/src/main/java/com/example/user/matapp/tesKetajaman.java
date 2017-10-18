package com.example.user.matapp;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.Toolbar;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

public class tesKetajaman extends AppCompatActivity implements View.OnClickListener{
    Button _start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tes_ketajaman);

        _start = (Button) findViewById(R.id.buttonStart);
        _start.setOnClickListener(this);
    }


    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v == _start){
            Intent intent = new Intent(tesKetajaman.this,tes_ketajaman2.class);
            startActivity(intent);
        }
    }
}