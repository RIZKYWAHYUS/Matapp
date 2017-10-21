package com.example.user.matapp;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.net.URISyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class NyobaTimer extends AppCompatActivity {

    public static final String REALM_NAME = "FastBeer";
    public static Handler sHandler;
    private int secs = 0;
    private int mins = 0;
    private int millis = 0;
    private long currentTime = 0L;
    private boolean isBound = false;
    private boolean isRuning = false;
    private MyService myService;
    private Intent mIntent;
    private Realm mRealm;
    private AlertDialog.Builder mDialogBuilder;
    @BindView(R.id.timer)
    TextView time;
    @BindView(R.id.fab_playPause)
    FloatingActionButton fabPlayPause;

    BroadcastReceiver ScreenReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nyoba_timer);

        ScreenReceiver = new ScreenReceiver();


        myService = new MyService();
        ButterKnife.bind(this);

        mRealm = Realm.getInstance(new RealmConfiguration.Builder(this)
                .name(NyobaTimer.REALM_NAME)
                .build());



        mIntent = new Intent(this, MyService.class);
        startService(mIntent);
        bindService(mIntent, myConnection, Context.BIND_AUTO_CREATE);

        NyobaTimer.sHandler = new Handler() {

            @Override
            public void handleMessage(Message timeMsg) {
                super.handleMessage(timeMsg);

                currentTime = Long.valueOf(timeMsg.obj.toString());

                secs = (int) (currentTime / 1000);
                mins = secs / 60;
                secs = secs % 60;
                millis = (int) (currentTime % 1000);
                setTime();
            }
        };

    }


    @Override
    protected void onPause() {
        try {
            this.ScreenReceiver.onReceive(getApplicationContext(), Intent.getIntent(Intent.ACTION_SCREEN_OFF));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Log.d("STATUS:", "melalui nyobatimer onPause");

        mins = 0;
        secs = 0;
        millis = 0;
        setTime();
        super.onPause();

    }

    @Override
    protected void onResume() {

        try {
            this.ScreenReceiver.onReceive(getApplicationContext(), Intent.getIntent(Intent.ACTION_SCREEN_ON));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Log.d("STATUS:", "melalui nyobatimer onResume");
        super.onResume();
    }




    @OnClick(R.id.fab_playPause)
    public void playPause() {

        myService.startStop();
        if(!isRuning){
            fabPlayPause.setImageResource(R.drawable.ic_menu_share);
            isRuning = true;
        } else if(isRuning){
            fabPlayPause.setImageResource(R.drawable.coins);
            isRuning = false;
        }

    }



    @OnClick(R.id.fab_reset)
    public void reset() {

        myService.reset();
        mins = 0;
        secs = 0;
        millis = 0;
        setTime();
        isRuning=false;

    }


    private ServiceConnection myConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            MyService.LocalBinder binder = (MyService.LocalBinder) service;
            myService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public void setTime() {
        time.setText("" + mins + ":" + String.format("%02d", secs) + ":"
                + String.format("%03d", millis));
    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mRealm.close();
//    }

}
