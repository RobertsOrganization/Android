package com.example.laborator3_smd;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.pm.PermissionInfoCompat;

import java.util.Random;

public class MyStartedService extends Service {

    @Override
    public void onCreate(){
        super.onCreate();
        Log.d("MyStartedService", "onCreate" + Thread.currentThread().getName());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        super.onStartCommand(intent, flags, startId);
        Log.d("MyStartedService", "onStartCommand" + Thread.currentThread().getName());
        generateRandom();
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("MyStartedService", "onBind" + Thread.currentThread().getName());
        return null;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("MyStartedService", "onDestroy");
    }

    public void generateRandom(){
        Random random = new Random(1000);
        int number = random.nextInt();
        Toast.makeText(MyStartedService.this,  "Bits saved: " + Integer.toString(number), Toast.LENGTH_SHORT).show();
    }
}
