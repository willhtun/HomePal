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

    @Override
    public void onReceive(Context context, Intent intent) {
        databaseHelper = new DatabaseHelper(context);
        calendarHelper = new CalendarHelper(databaseHelper, context);
        thisCon = context;
      //  if (isBillDueSoon() && isNotificationOn()) {
        Log.d("XXX", String.valueOf(isBillDueSoon()));
        Log.d("XXX", String.valueOf(isNotificationOn()));
        if (isBillDueSoon() && isNotificationOn())
            scheduleNotification(context, intent);
       // }
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

    private boolean isNotificationOn() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(thisCon);
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
