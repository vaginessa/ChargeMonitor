package com.practice.vlad.chargemonitor.services;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import com.practice.vlad.chargemonitor.receivers.PowerConnectionReceiver;

public class BatteryService extends Service {
    public BatteryService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        IntentFilter filterBatteryChanged = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(new PowerConnectionReceiver(), filterBatteryChanged);

        IntentFilter filterScreenOff = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        registerReceiver(new PowerConnectionReceiver(), filterScreenOff);
    }
}
