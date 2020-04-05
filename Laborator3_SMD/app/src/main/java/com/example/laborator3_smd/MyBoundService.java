package com.example.laborator3_smd;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyBoundService extends Service {

    class MyBoundServiceBinder extends Binder {
        private MyBoundService myBoundService = MyBoundService.this;
        public MyBoundService get() {
            return myBoundService;
        }
    }

    private MyBoundServiceBinder binder = new MyBoundServiceBinder();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public String getCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
