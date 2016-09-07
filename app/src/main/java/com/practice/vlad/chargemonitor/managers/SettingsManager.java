package com.practice.vlad.chargemonitor.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.RingtoneManager;

/**
 * Created by vlad.itu on 6/19/2015.
 */
public class SettingsManager {
    private static final String SHARED_PREFERENCES_NAME = "charge.monitor.settings";
    private static final String KEY_LED_CHARGING_COLOR = "key_led_charging_color";
    private static final String KEY_LED_CHARGED_COLOR = "key_led_charged_color";
    private static final String KEY_LOW_BATTERY_WARNING_THRESHOLD = "key_low_battery_warning_threshold";
    private static final String KEY_NOTIFICATION_SOUND_URI = "key_notification_sound_uri";
    private static final String KEY_NOTIFICATION_VIBRATE = "key_notification_vibrate";
    private static final String KEY_PERSISTENT_NOTIFICATION = "key_persistent_notification";

    private static final int DEFAULT_COLOR = 0;
    private static final int DEFAULT_LOW_BATTERY_WARNING_THRESHOLD = 20;

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
        return sharedPreferences.getInt(KEY_LED_CHARGING_COLOR, DEFAULT_COLOR);
    }

    public void saveLedChargedColor(int color) {
        sharedPreferences.edit().putInt(KEY_LED_CHARGED_COLOR, color).apply();
    }

    public int getLedChargedColor() {
        return sharedPreferences.getInt(KEY_LED_CHARGED_COLOR, DEFAULT_COLOR);
    }

    public void saveLowBatteryWarningThreshold(int threshold) {
        sharedPreferences.edit().putInt(KEY_LOW_BATTERY_WARNING_THRESHOLD, threshold).apply();
    }

    public int getLowBatteryWarningThreshold() {
        return sharedPreferences.getInt(KEY_LOW_BATTERY_WARNING_THRESHOLD, DEFAULT_LOW_BATTERY_WARNING_THRESHOLD);
    }

    public void saveNotificationSoundRingtoneUri(String uri) {
        sharedPreferences.edit().putString(KEY_NOTIFICATION_SOUND_URI, uri).apply();
    }

    public String getNotificationSoundUri() {
        return sharedPreferences.getString(KEY_NOTIFICATION_SOUND_URI, String.valueOf(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)));
    }

    public void saveNotificationVibrate(boolean shouldVibrate) {
        sharedPreferences.edit().putBoolean(KEY_NOTIFICATION_VIBRATE, shouldVibrate).apply();
    }

    public boolean getNotificationVibrate() {
        return sharedPreferences.getBoolean(KEY_NOTIFICATION_VIBRATE, false);
    }

    public void saveNotificationPersistent(boolean persistent) {
        sharedPreferences.edit().putBoolean(KEY_PERSISTENT_NOTIFICATION, persistent).apply();
    }

    public boolean getNotificationPersistent() {
        return sharedPreferences.getBoolean(KEY_PERSISTENT_NOTIFICATION, false);
    }

}
