package com.example.laborator3_smd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.os.Build.VERSION_CODES.O;

public class MainActivity extends AppCompatActivity {

    private LocationService locationService;
    private MyBoundService myBoundService;
    private int INTERVAL_SECONDS = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startServiceButton = findViewById(R.id.start_service);
        Button stopServiceButton = findViewById(R.id.stop_service);
        Button showDate = findViewById(R.id.show_date);

        startServiceButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startLocationService();
            }
        });

        stopServiceButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                stopLocationService();
            }
        });

        showDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String date = myBoundService.getCurrentDate();
                Toast.makeText(MainActivity.this, "The current date is : " + date, Toast.LENGTH_SHORT).show();
            }
        });

        bindService();

        Intent intent = new Intent(this, MyIntentService.class);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService();
    }

    private void startLocationService() {

        Intent intent = LocationService.getIntent(this, "hello");

        if (isOreoOrHigher()) {
            startForegroundService(intent);
        } else {
            startService(intent);
        }
    }

    private Boolean isOreoOrHigher() {
        return Build.VERSION.SDK_INT >= O;
    }

    private void stopLocationService(){

        Intent intent = LocationService.getIntent(this, "hello");

        if (isOreoOrHigher()) {
            stopService(intent);
        } else {
            stopService(intent);
        }
    }

    private void bindToService() {
        if (locationService == null) {
            Intent intent = new Intent(this, LocationService.class);
            bindService(intent, serviceConnection, BIND_AUTO_CREATE);
        }
    }

    private void bindService(){
        if (myBoundService == null){
            Intent intent = new Intent(this, MyBoundService.class);
            bindService(intent, myBoundServiceConnection, BIND_AUTO_CREATE);
        }
    }

    private void unbindService(){
        if (myBoundService != null){
            unbindService(myBoundServiceConnection);
        }
    }

    private ServiceConnection myBoundServiceConnection =  new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myBoundService = ((MyBoundService.MyBoundServiceBinder) iBinder).get();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            locationService = null;
        }
    };

    private ServiceConnection serviceConnection =  new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            locationService = ((LocationService.LocationServiceBinder) iBinder).get();
            locationService.startLocationTracking(INTERVAL_SECONDS);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            locationService = null;
        }
    };


}
