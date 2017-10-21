package com.example.user.matapp;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

public class MyService extends Service {


    private boolean isRunning = false;
    private long startTime = 0;
    private long timeInMilliseconds = 0;
    private long timeSwapBuff = 0;
    private long updatedTime = 0;
    private final IBinder mBinder = new LocalBinder();
    private Message timeMsg;

    public MyService() { }

    public Runnable updateTimer = new Runnable() {
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;
            Log.d("Czas:", String.valueOf(updatedTime));

            timeMsg = new Message();
            timeMsg.obj = updatedTime;
            NyobaTimer.sHandler.sendMessage(timeMsg);

            NyobaTimer.sHandler.postDelayed(this, 10);
        }
    };

    @Override
    public void onCreate(){
        super.onCreate();


        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        BroadcastReceiver mReceiver = new ScreenReceiver();
        registerReceiver(mReceiver, filter);

    }

    @Override
    public void onStart(Intent intent, int startId) {
        boolean screenOn = intent.getBooleanExtra("screen_state", false);
        if (!screenOn) {
            reset();
            Log.d("STATUS:", "melalui MyService onStart if");

        } else {
            Log.d("STATUS:", "melalui MyService onStart else");
            startStop();
        }
    }




    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    public void startStop(){

        if (false) {
            timeSwapBuff += timeInMilliseconds;
            NyobaTimer.sHandler.removeCallbacks(updateTimer);
            isRunning = false;
        } else {
            startTime = SystemClock.uptimeMillis();
            updateTimer.run();
//            MainActivity.sHandler.postDelayed(updateTimer, 10);
            isRunning = true;
        }
    }


    public void reset(){

        NyobaTimer.sHandler.removeCallbacks(updateTimer);
        isRunning=false;
        startTime = 0L;
        timeInMilliseconds = 0L;
        timeSwapBuff = 0L;
        updatedTime = 0L;

        timeMsg = new Message();
        timeMsg.obj = updatedTime;
        NyobaTimer.sHandler.sendMessage(timeMsg);

    }

    public class LocalBinder extends Binder {
        public MyService getService(){
            return MyService.this;
        }
    }


}
