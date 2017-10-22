package com.example.user.matapp;

import android.app.AlertDialog;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class MyService extends Service {


    private boolean isRunning = false;
    private long startTime = 0;
    private long timeInMilliseconds = 0;
    private long timeSwapBuff = 0;
    private long updatedTime = 0;
    private final IBinder mBinder = new LocalBinder();
    private Message timeMsg;
    String waktu="";
    public MyService() { }

        //keperluan popup
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;

    public Runnable updateTimer = new Runnable() {
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;
            waktu = String.valueOf(updatedTime);
            Log.d("Czas:", waktu);

            timeMsg = new Message();
            timeMsg.obj = updatedTime;
            if (waktu.equals("20000")){

            }
            Statistika.sHandler.sendMessage(timeMsg);

            Statistika.sHandler.postDelayed(this, 10);

        }
    };

    @Override
    public void onCreate(){
        super.onCreate();



    }

//
//    private void DialogForm() {
//        dialog = new AlertDialog.Builder(Statistika.this);
//        inflater = getLayoutInflater();
//        dialogView = inflater.inflate(R.layout.popup_durasi, null);
//        dialog.setView(dialogView);
//        dialog.setCancelable(true);
//        dialog.show();
//        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialog) {
//                Toast.makeText(getApplicationContext(), "percobaan", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//
//    }
//
//    public void pencet(View v){
//        Toast.makeText(getApplicationContext(), "percobaan", Toast.LENGTH_SHORT).show();
//
//    }




    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public void startStopTanpaTombol(){
            startTime = SystemClock.uptimeMillis();
            updateTimer.run();
//            MainActivity.sHandler.postDelayed(updateTimer, 10);
            isRunning = true;
    }

    public void startStop(){

        if (isRunning) {
            timeSwapBuff += timeInMilliseconds;
            Statistika.sHandler.removeCallbacks(updateTimer);
            isRunning = false;
        } else {
            startTime = SystemClock.uptimeMillis();
            updateTimer.run();
//            MainActivity.sHandler.postDelayed(updateTimer, 10);
            isRunning = true;
        }
    }


    public void reset(){

        Statistika.sHandler.removeCallbacks(updateTimer);
        isRunning=false;
        startTime = 0L;
        timeInMilliseconds = 0L;
        timeSwapBuff = 0L;
        updatedTime = 0L;

        timeMsg = new Message();
        timeMsg.obj = updatedTime;
        Statistika.sHandler.sendMessage(timeMsg);

    }

    public class LocalBinder extends Binder {
        public MyService getService(){
            return MyService.this;
        }
    }


}
