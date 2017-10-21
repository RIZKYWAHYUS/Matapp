package com.example.user.matapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ScreenReceiver extends BroadcastReceiver {

    private boolean screenOff;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("STATUS:", "melalui ScreenREceiver onReceive()");

        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            screenOff = true;
            Log.d("STATUS:", "melalui nyobatimer onPause if");

        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            screenOff = false;
            Log.d("STATUS:", "melalui nyobatimer onPause else if");

        }
        Intent i = new Intent(context, MyService.class);
        i.putExtra("screen_state", screenOff);
        context.startService(i);
    }

}