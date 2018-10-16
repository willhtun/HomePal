package willhtun.homepal;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.Calendar;

public class CalendarHelper {

    DatabaseHelper db;

    Calendar calendar;

    int currentDay;
    int currentMonth;
    int currentYear;

    int cycleMonth;
    int cycleYear;

    public CalendarHelper(DatabaseHelper in_db) {
        db = in_db;

        calendar = Calendar.getInstance();
        currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        currentMonth = calendar.get(Calendar.MONTH) + 1;
        currentYear = calendar.get(Calendar.YEAR);

        Log.d("Calendarhelper","calendar created");
    }

    public int getTodayDate() {
        return calendar.get(Calendar.DATE);
    }

    public int getCycleMonth(String type) {
        String temp_type = type + "_dd";
        int duedate = db.getDate_fromDueDate(type);
        if (currentDay > duedate) {
            cycleMonth = currentMonth + 1;
            if (cycleMonth > 12)
                cycleMonth = 1;
        }
        else {
            cycleMonth = currentMonth;
        }
        return cycleMonth;
    }

    public int getDaysLeftUntil (String type) {
        int duedate = db.getDate_fromDueDate(type);
        if (duedate < 0)
            return 1;
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
            cycleMonth = currentMonth + 1;
            cycleYear = currentYear;
            if (cycleMonth > 11) {
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
            cycleMonth = currentMonth + 1;
            cycleYear = currentYear;
            if (cycleMonth > 11) {
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
}
