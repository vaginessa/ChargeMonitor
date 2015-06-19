package com.practice.vlad.chargemonitor.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;

/**
 * Created by vlad.itu on 6/19/2015.
 */
public class SettingsManager {
    private static final String SHARED_PREFERENCES_NAME = "charge.monitor.settings";
    private static final String KEY_LED_CHARGING_COLOR = "key_led_charging_color";
    private static final String KEY_LED_CHARGED_COLOR = "key_led_charged_color";

    private static SettingsManager mInstance;
    private SharedPreferences sharedPreferences;

    private SettingsManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static SettingsManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SettingsManager(context);
        }
        return mInstance;
    }

    public void saveLedChargingColor(int color) {
        sharedPreferences.edit().putInt(KEY_LED_CHARGING_COLOR, color).apply();
    }

    public int getLedChargingColor() {
        return sharedPreferences.getInt(KEY_LED_CHARGING_COLOR, 0);
    }

    public void saveLedChargedColor(int color) {
        sharedPreferences.edit().putInt(KEY_LED_CHARGED_COLOR, color).apply();
    }

    public int getLedChargedColor() {
        return sharedPreferences.getInt(KEY_LED_CHARGED_COLOR, 0);
    }

}
