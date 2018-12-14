package willhtun.homepal;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.Calendar;

public class CalendarHelper {

    DatabaseHelper db;

    Calendar calendar;

    Context context;

    int currentDay;
    int currentMonth;
    int currentYear;

    int cycleMonth;
    int cycleYear;

    public CalendarHelper(DatabaseHelper in_db, Context c) {
        db = in_db;
        context = c;
        calendar = Calendar.getInstance();
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        currentMonth = calendar.get(Calendar.MONTH);
        currentYear = calendar.get(Calendar.YEAR);

        Log.d("Calendarhelper","calendar created");
    }

    public int getTodayDate() {
        return calendar.get(Calendar.DATE);
    }

    public int getCycleMonth(String type) {
        int duedate = db.getDate_fromDueDate(type);
        if (currentDay > duedate) {
            cycleMonth = currentMonth + 1 + 1; // one to increment, one to offset 0-11 to 1-12
            if (cycleMonth > 12)
                cycleMonth = 1;
        }
        else {
            cycleMonth = currentMonth + 1;
        }
        return cycleMonth;
    }

    public int getDaysLeftUntil (String type) {
        int duedate = db.getDate_fromDueDate(type);
        if (duedate < 0)
            return 99;
        if (currentDay <= duedate)
            return duedate - currentDay;
        else {
            if (currentMonth == 1 || currentMonth == 3 || currentMonth == 5 ||  currentMonth == 7 ||
                    currentMonth == 8 || currentMonth == 10 || currentMonth == 12) {
                return (31 - currentDay) + duedate;
            }
            else if (currentMonth == 2) {
                if (currentYear % 4 == 0) // leap year
                    return (29 - currentDay) + duedate;
                else
                    return (28 - currentDay) + duedate;
            }
            else {
                return (30 - currentDay) + duedate;
            }
        }
    }

    public int getCycleYear(String type) {
        String temp_type = type + "_dd";
        int duedate = db.getDate_fromDueDate(temp_type);
        if (currentDay > duedate) {
            cycleMonth = currentMonth + 1 + 1;
            cycleYear = currentYear;
            if (cycleMonth > 12) {
                cycleYear = currentYear + 1;
            }
        }
        else {
            cycleYear = currentYear;
        }
        return cycleYear;
    }

    public String getCycleMonthYear(String type) {
        String temp_type = type + "_dd";
        int duedate = db.getDate_fromDueDate(temp_type);
        if (currentDay > duedate) {
            cycleMonth = currentMonth + 1 + 1;
            cycleYear = currentYear;
            if (cycleMonth > 12) {
                cycleMonth = 1;
                cycleYear = currentYear + 1;
            }
        }
        else {
            cycleMonth = currentMonth;
            cycleYear = currentYear;
        }
        return cycleMonth + "_" + cycleYear;
    }

    public int getClosestDueDate() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int closestduedate = 99;
        if ((getDaysLeftUntil("rent") < closestduedate) && !isPaid("rent") && preferences.getBoolean("check_box_preference_bills_rent", false))
            closestduedate = getDaysLeftUntil("rent");
        if ((getDaysLeftUntil("car") < closestduedate) && !isPaid("car") && preferences.getBoolean("check_box_preference_bills_car", false))
            closestduedate = getDaysLeftUntil("car");
        if ((getDaysLeftUntil("internet") < closestduedate) && !isPaid("internet") && preferences.getBoolean("check_box_preference_bills_internet", false))
            closestduedate = getDaysLeftUntil("internet");
        if ((getDaysLeftUntil("mobile") < closestduedate) && !isPaid("mobile") && preferences.getBoolean("check_box_preference_bills_mobile", false))
            closestduedate = getDaysLeftUntil("mobile");
        if ((getDaysLeftUntil("electricity") < closestduedate) && !isPaid("electricity") && preferences.getBoolean("check_box_preference_bills_electricity", false))
            closestduedate = getDaysLeftUntil("electricity");
        if ((getDaysLeftUntil("water") < closestduedate) && !isPaid("water") && preferences.getBoolean("check_box_preference_bills_water", false))
            closestduedate = getDaysLeftUntil("water");
        if ((getDaysLeftUntil("gas") < closestduedate) && !isPaid("gas") && preferences.getBoolean("check_box_preference_bills_gas", false))
            closestduedate = getDaysLeftUntil("gas");
        if ((getDaysLeftUntil("trash") < closestduedate) && !isPaid("trash") && preferences.getBoolean("check_box_preference_bills_trash", false))
            closestduedate = getDaysLeftUntil("trash");
        if ((getDaysLeftUntil("custom1") < closestduedate) && !isPaid("custom1") && preferences.getBoolean("check_box_preference_bills_custom1", false))
            closestduedate = getDaysLeftUntil("custom1");
        if ((getDaysLeftUntil("custom2") < closestduedate) && !isPaid("custom2") && preferences.getBoolean("check_box_preference_bills_custom2", false))
            closestduedate = getDaysLeftUntil("custom2");
        if ((getDaysLeftUntil("custom3") < closestduedate) && !isPaid("custom3") && preferences.getBoolean("check_box_preference_bills_custom3", false))
            closestduedate = getDaysLeftUntil("custom3");
        if ((getDaysLeftUntil("custom4") < closestduedate) && !isPaid("custom4") && preferences.getBoolean("check_box_preference_bills_custom4", false))
            closestduedate = getDaysLeftUntil("custom4");
        if ((getDaysLeftUntil("custom5") < closestduedate) && !isPaid("custom5") && preferences.getBoolean("check_box_preference_bills_custom5", false))
            closestduedate = getDaysLeftUntil("custom5");
        return closestduedate;
    }

    public boolean isPaid(String type) {
        return db.isDataExists_fromHistory(type, getCycleMonthYear(type),0);
    }

}
