package com.practice.vlad.chargemonitor.receivers;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.RingtoneManager;
import android.os.BatteryManager;
import android.support.v4.app.NotificationCompat;

import com.practice.vlad.chargemonitor.R;
import com.practice.vlad.chargemonitor.managers.SettingsManager;

public class PowerConnectionReceiver extends BroadcastReceiver {

    static final int NOTIFICATION_ID = 932749823;
    static final int NOTIFICATION_ID_CHARGE_SOUND = 932749821;
    static final int MAXIMUM_BATTERY_PERCENTAGE = 1;
    static final int LED_ON_TIME = 999999999;
    static final int LED_OFF_TIME = 1;

    private NotificationManager mNotificationManager;

    public PowerConnectionReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent batteryChangedIntent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int plugged = batteryChangedIntent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);

        int level = batteryChangedIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryChangedIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        float batteryPct = level / (float) scale;

        if (plugged > 0) {
            int color = SettingsManager.getInstance(context).getLedChargingColor();
            if (batteryPct == MAXIMUM_BATTERY_PERCENTAGE) {
                color = SettingsManager.getInstance(context).getLedChargedColor();;
            }
            turnOnLight(context, color);
        } else {
            turnOffLight(context);
        }
        if (100 * batteryPct <= SettingsManager.getInstance(context).getLowBatteryWarningThreshold()) {
            showLowBatteryNotification(context, (int) (100 * batteryPct));
        } else {
            hideLowBatteryNotification(context);
        }
    }

    private void showLowBatteryNotification(Context context, int batteryPct) {
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.abc_btn_radio_material)
                        .setContentTitle(context.getResources().getString(R.string.low_battery_warning_title))
                        .setContentText(context.getResources().getString(R.string.low_battery_warning_content) + batteryPct)
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        mNotificationManager.notify(NOTIFICATION_ID_CHARGE_SOUND, mBuilder.build());
    }

    private void hideLowBatteryNotification(Context context) {
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancel(NOTIFICATION_ID_CHARGE_SOUND);
    }

    private void turnOnLight(Context context, int color) {
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        final Notification notification = new Notification();
        notification.ledARGB = color;
        notification.ledOnMS = LED_ON_TIME;
        notification.ledOffMS = LED_OFF_TIME;
        notification.flags |= Notification.FLAG_SHOW_LIGHTS;
        mNotificationManager.notify(NOTIFICATION_ID, notification);
    }

    private void turnOffLight(Context context) {
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancel(NOTIFICATION_ID);
    }
}
