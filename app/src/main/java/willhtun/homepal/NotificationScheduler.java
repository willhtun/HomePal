package willhtun.homepal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.Log;

public class NotificationScheduler extends BroadcastReceiver {

    CalendarHelper calendarHelper;
    DatabaseHelper databaseHelper;
    Context thisCon;
    SharedPreferences preferences;

    @Override
    public void onReceive(Context context, Intent intent) {
        databaseHelper = new DatabaseHelper(context);
        calendarHelper = new CalendarHelper(databaseHelper, context);
        thisCon = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(thisCon);
        if (isBillDueSoon() && isNotificationOn())
            scheduleNotification(context, intent);

        prepTable_empty("rent");
        prepTable_empty("car");
        prepTable_empty("internet");
        prepTable_empty("mobile");

        handleOverdues("rent");
        handleOverdues("car");
        handleOverdues("internet");
        handleOverdues("mobile");

    }

    //=================================================================

    private boolean isBillDueSoon () {
        int rd = databaseHelper.getDate_fromDueDate("reminder_day");
        if (rd < 0)
            rd = 1;

        if (calendarHelper.getClosestDueDate() <= rd)
            return true;
        else
            return false;
    }

    private void prepTable_empty(String type) {    // Adds empty rent_10_18 0 0 0 0 0 0 line
        int mon = calendarHelper.getCycleMonth(type);
        int yr = calendarHelper.getCycleYear(type);
        String monyr = String.valueOf(mon) + "_" + String.valueOf(yr);
        if (!databaseHelper.isEntryExists_fromHistory(type, monyr)) {
            databaseHelper.addData_toHistory(type, monyr, calendarHelper.getTodayDate(), 8, 0);
        }
    }

    private void handleOverdues(String type) {
        int mon = calendarHelper.getCycleMonth(type);
        int yr = calendarHelper.getCycleYear(type);
        if (mon == 1) {
            mon = 12;
            yr = yr - 1;
        }
        else {
            mon = mon - 1;
        }
        String monyr = String.valueOf(mon) + "_" + String.valueOf(yr);
        if (!databaseHelper.isDataExists_fromHistory(type, monyr, 0)) {
            preferences.edit().putBoolean("overdue_" + type, true).apply();
        }
        else
            preferences.edit().putBoolean("overdue_" + type, false).apply();
    }

    private boolean isNotificationOn() {
        return preferences.getBoolean("settings_notificationOn", true);
    }

    private void scheduleNotification(Context context, Intent intent) {
        Intent notificationIntent = new Intent(context, NotificationTask.class);
        notificationIntent.putExtra(NotificationTask.NOTIFICATION_ID, 1);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime();
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }
}
