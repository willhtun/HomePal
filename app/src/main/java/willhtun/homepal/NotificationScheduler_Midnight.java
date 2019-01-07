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

public class NotificationScheduler_Midnight extends BroadcastReceiver {

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

        handleOverdues("rent");
        preferences.edit().putBoolean("housemate5_On", true).apply();
        preferences.edit().putBoolean("housemate4_On", true).apply();
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
            databaseHelper.addData_toHistory(type, monyr, calendarHelper.getTodayDate(), 8, 0);
            preferences.edit().putBoolean("overdue_" + type, true).apply();
        }
        else
            preferences.edit().putBoolean("overdue_" + type, false).apply();
    }
}
