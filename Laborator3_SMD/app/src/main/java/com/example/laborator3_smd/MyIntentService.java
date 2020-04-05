package com.example.laborator3_smd;

import android.app.IntentService;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.concurrent.ThreadLocalRandom;

public class MyIntentService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent == null){

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void generate_random_number(){
        int rand_number = ThreadLocalRandom.current().nextInt(10,1000);
        Log.d("Random Number", "Random money number is : " + Integer.toString(rand_number));
        Log.d("Intent Service Thread", Thread.currentThread().getName());
    }
}
