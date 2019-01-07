package willhtun.homepal;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;

public class SettingsN extends AppCompatActivity {

    public static int remainderDays = 7; // static or not?

    NumberPicker np_reminder;
    AlertDialog reminderpickerdialog;

    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settingsn);

        mDatabaseHelper = new DatabaseHelper(getApplicationContext());

        remainderDays = mDatabaseHelper.getDate_fromDueDate("reminder_day");
        if (remainderDays < 0) remainderDays = 99;
    }

    public void goToActivityAddBills() {
        Intent intent_addbills = new Intent(this, SettingsNAddBills.class);
        startActivity(intent_addbills);
    }

    public void goToActivityDueDates() {
        Intent intent_duedates = new Intent(this, SettingsNDueDates.class);
        startActivity(intent_duedates);
    }

    public void goToActivityBillSharing() {
        Intent intent_billsharing = new Intent(this, SettingsNBillSharing.class);
        startActivity(intent_billsharing);
    }

    public void goToActivityHousemates() {
        Intent intent_housemates = new Intent(this, SettingsNHousemates.class);
        startActivity(intent_housemates);
    }

    public void show_reminderPicker() {
        AlertDialog.Builder reminderPicker_Builder = new AlertDialog.Builder(SettingsN.this);
        View reminder_view = getLayoutInflater().inflate(R.layout.dialog_reminderpicker, null);
        reminderPicker_Builder.setView(reminder_view);
        reminderpickerdialog = reminderPicker_Builder.create();

        np_reminder = reminder_view.findViewById(R.id.dialog_setRemindDaysPicker);
        np_reminder.setMaxValue(10); //Set to 28/29 for Feb if exceeds 28
        np_reminder.setMinValue(1);
        np_reminder.setValue(remainderDays);

        reminderpickerdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        reminderpickerdialog.show();
    }

    public void setReminderDays(View view) {
        remainderDays = np_reminder.getValue();
        mDatabaseHelper.addData_toDueDate("reminder_day", remainderDays, 0);
        reminderpickerdialog.dismiss();
    }
}
