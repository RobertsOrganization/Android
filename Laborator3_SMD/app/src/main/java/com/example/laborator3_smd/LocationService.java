package com.example.laborator3_smd;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class LocationService extends Service {

    class LocationServiceBinder extends Binder {
        private LocationService locationService = LocationService.this;
        public LocationService get() {
            return locationService;
        }
    }

    private LocationServiceBinder binder = new LocationServiceBinder();
    private static final String EXTRA_INFO = "locationservice.info";
    private Boolean serviceIsForeground;

    @Override

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("TAG", "Started service");
        // If the process is killed with no remaining start commands to deliver, then the
        // service will be stopped instead of restarted. It can be recreated later with an explicit
        // startService call
        return START_NOT_STICKY;
    }

    public static Intent getIntent(Context context, String info) {
        Intent intent = new Intent(context, LocationService.class);
        intent.putExtra(EXTRA_INFO, info);
        return intent;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void startLocationTracking(int interval) {
        Log.d("LocationService", "blabla");
        Notification notification = new Notification();
        setServiceForeground(true, notification);
    }

    private void setServiceForeground(Boolean serviceIsForeground, Notification notification) {
        if (this.serviceIsForeground != serviceIsForeground) {
            this.serviceIsForeground = serviceIsForeground;
            if (serviceIsForeground) {
                startForeground(10, notification);
            } else {
                stopForeground(true);
            }
        }
    }
}
