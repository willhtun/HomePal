package willhtun.homepal;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

public class Settings extends AppCompatActivity {
    int reminderDays = 5;
    int rentDueDate = 1;
    int internetDueDate = 1;
    int electricityDueDate = 1;
    int waterDueDate = 1;
    int gasDueDate = 1;
    int trashDueDate = 1;

    double rentCost = 0.0;
    double internetCost = 0.0;
    double electricityCost = 0.0;
    double waterCost = 0.0;
    double gasCost = 0.0;
    double trashCost = 0.0;

    int rentFlag = 0;
    int internetFlag = 0;
    int electricityFlag = 0;
    int waterFlag = 0;
    int gasFlag = 0;
    int trashFlag = 0;

    NumberPicker np_reminder;
    AlertDialog reminderpickerdialog;
    Button text_reminder;

    NumberPicker np_datePicker_day;
    EditText et_datePicker_cost;
    AlertDialog datePickerDialog;
    Button text_rent;
    Button text_internet;
    Button text_electricity;
    Button text_water;
    Button text_gas;
    Button text_trash;

    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mDatabaseHelper = new DatabaseHelper(getApplicationContext());

        reminderDays = mDatabaseHelper.getDate_fromDueDate("reminder_day");
        if (reminderDays < 0) reminderDays = 1;
        rentDueDate = mDatabaseHelper.getDate_fromDueDate("rent");
        if (rentDueDate < 0) rentDueDate = 1;
        internetDueDate = mDatabaseHelper.getDate_fromDueDate("internet");
        if (internetDueDate < 0) internetDueDate = 1;
        electricityDueDate = mDatabaseHelper.getDate_fromDueDate("electricity");
        if (electricityDueDate < 0) electricityDueDate = 1;
        waterDueDate = mDatabaseHelper.getDate_fromDueDate("water");
        if (waterDueDate < 0) waterDueDate = 1;
        gasDueDate = mDatabaseHelper.getDate_fromDueDate("gas");
        if (gasDueDate < 0) gasDueDate = 1;
        trashDueDate = mDatabaseHelper.getDate_fromDueDate("trash");
        if (trashDueDate < 0) trashDueDate = 1;

        rentCost = mDatabaseHelper.getCost_fromDueDate("rent");
        internetCost = mDatabaseHelper.getCost_fromDueDate("internet");
        electricityCost = mDatabaseHelper.getCost_fromDueDate("electricity");
        waterCost = mDatabaseHelper.getCost_fromDueDate("water");
        gasCost = mDatabaseHelper.getCost_fromDueDate("gas");
        trashCost = mDatabaseHelper.getCost_fromDueDate("trash");

        text_reminder = (Button) findViewById(R.id.settings_reminder);
        text_reminder.setText("Remind me " + reminderDays + " days before due date.");
        text_rent = (Button) findViewById(R.id.settings_rentdue);
        text_rent.setText("Rent. Due date " + rentDueDate + " of every month. $" + rentCost);
        text_internet = (Button) findViewById(R.id.settings_internetdue);
        text_internet.setText("Internet. Due date " + internetDueDate + " of every month. $" + internetCost);
        text_electricity = (Button) findViewById(R.id.settings_electricitydue);
        text_electricity.setText("Electricity. Due date " + electricityDueDate + " of every month. $" + electricityCost);
        text_water = (Button) findViewById(R.id.settings_waterdue);
        text_water.setText("Water. Due date " + waterDueDate + " of every month. $" + waterCost);
        text_gas = (Button) findViewById(R.id.settings_gasdue);
        text_gas.setText("Gas. Due date " + gasDueDate + " of every month. $" + gasCost);
        text_trash = (Button) findViewById(R.id.settings_trashdue);
        text_trash.setText("Trash. Due date " + trashDueDate + " of every month. $" + trashCost);
    }

    public void show_reminderPicker(View view) {
        AlertDialog.Builder reminderPicker_Builder = new AlertDialog.Builder(Settings.this);
        View reminder_view = getLayoutInflater().inflate(R.layout.dialog_reminderpicker, null);
        reminderPicker_Builder.setView(reminder_view);
        reminderpickerdialog = reminderPicker_Builder.create();

        np_reminder = (NumberPicker) reminder_view.findViewById(R.id.dialog_setRemindDaysPicker);
        np_reminder.setMaxValue(31); //Set to 28/29 for Feb if exceeds 28
        np_reminder.setMinValue(1);

        reminderpickerdialog.show();
    }

    public void show_datePicker(View view) {
        AlertDialog.Builder datePicker_Builder = new AlertDialog.Builder(Settings.this);
        View datepicker_view = getLayoutInflater().inflate(R.layout.dialog_datepicker, null);
        datePicker_Builder.setView(datepicker_view);
        datePickerDialog = datePicker_Builder.create();
        datePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                rentFlag = 0;
                internetFlag = 0;
                electricityFlag = 0;
                waterFlag = 0;
                gasFlag = 0;
                trashFlag = 0;
            }
        });

        np_datePicker_day = (NumberPicker) datepicker_view.findViewById(R.id.dialog_datePicker_day);
        np_datePicker_day.setMaxValue(31);
        np_datePicker_day.setMinValue(1);

        et_datePicker_cost = (EditText) datepicker_view.findViewById(R.id.dialog_datePicker_cost);
        if (rentFlag == 1) {
            np_datePicker_day.setValue(rentDueDate);
            et_datePicker_cost.setText(Double.toString(mDatabaseHelper.getCost_fromDueDate("rent")));
        }
        else if (internetFlag == 1) {
            np_datePicker_day.setValue(internetDueDate);
            et_datePicker_cost.setText(Double.toString(mDatabaseHelper.getCost_fromDueDate("internet")));
        }
        else if (electricityFlag == 1) {
            np_datePicker_day.setValue(electricityDueDate);
            et_datePicker_cost.setText(Double.toString(mDatabaseHelper.getCost_fromDueDate("electricity")));
        }
        else if (waterFlag == 1) {
            np_datePicker_day.setValue(waterDueDate);
            et_datePicker_cost.setText(Double.toString(mDatabaseHelper.getCost_fromDueDate("water")));
        }
        else if (gasFlag == 1) {
            np_datePicker_day.setValue(gasDueDate);
            et_datePicker_cost.setText(Double.toString(mDatabaseHelper.getCost_fromDueDate("gas")));
        }
        else if (trashFlag == 1) {
            np_datePicker_day.setValue(trashDueDate);
            et_datePicker_cost.setText(Double.toString(mDatabaseHelper.getCost_fromDueDate("trash")));
        }

        datePickerDialog.show();
    }

    public void show_datePicker_rent(View view) {
        rentFlag = 1;
        show_datePicker(view);
    }
    public void show_datePicker_internet(View view) {
        internetFlag = 1;
        show_datePicker(view);
    }
    public void show_datePicker_electricity(View view) {
        electricityFlag = 1;
        show_datePicker(view);
    }
    public void show_datePicker_water(View view) {
        waterFlag = 1;
        show_datePicker(view);
    }
    public void show_datePicker_gas(View view) {
        gasFlag = 1;
        show_datePicker(view);
    }
    public void show_datePicker_trash(View view) {
        trashFlag = 1;
        show_datePicker(view);
    }

    public void setReminderDays(View view) {
        reminderDays = np_reminder.getValue();
        mDatabaseHelper.addData_toDueDate("reminder_day", reminderDays, 0);
        text_reminder.setText("Remind me " + reminderDays + " days before due date.");
        reminderpickerdialog.dismiss();
    }

    public void setDatePicker(View view) {
        if (rentFlag == 1) {
            rentDueDate = np_datePicker_day.getValue();
            rentCost = Double.parseDouble(et_datePicker_cost.getText().toString());
            mDatabaseHelper.addData_toDueDate("rent", rentDueDate, rentCost);
            text_rent.setText("Rent. Due date " + rentDueDate + " of every month. $" + rentCost);
            rentFlag = 0;
        }
        else if (internetFlag == 1) {
            internetDueDate = np_datePicker_day.getValue();
            internetCost = Double.parseDouble(et_datePicker_cost.getText().toString());
            mDatabaseHelper.addData_toDueDate("internet", internetDueDate, internetCost);
            text_internet.setText("Internet. Due date " + internetDueDate + " of every month. $" + internetCost);
            internetFlag = 0;
        }
        else if (electricityFlag == 1) {
            electricityDueDate = np_datePicker_day.getValue();
            electricityCost = Double.parseDouble(et_datePicker_cost.getText().toString());
            mDatabaseHelper.addData_toDueDate("electricity", electricityDueDate, electricityCost);
            text_electricity.setText("Electricity. Due date " + electricityDueDate + " of every month. $" + electricityCost);
            electricityFlag = 0;
        }
        else if (waterFlag == 1) {
            waterDueDate = np_datePicker_day.getValue();
            waterCost = Double.parseDouble(et_datePicker_cost.getText().toString());
            mDatabaseHelper.addData_toDueDate("water", waterDueDate, waterCost);
            text_water.setText("Water. Due date " + waterDueDate + " of every month. $" + waterCost);
            waterFlag = 0;
        }
        else if (gasFlag == 1) {
            gasDueDate = np_datePicker_day.getValue();
            gasCost = Double.parseDouble(et_datePicker_cost.getText().toString());
            mDatabaseHelper.addData_toDueDate("gas", gasDueDate, gasCost);
            text_gas.setText("Gas. Due date " + gasDueDate + " of every month. $" + gasCost);
            gasFlag = 0;
        }
        else if (trashFlag == 1) {
            trashDueDate = np_datePicker_day.getValue();
            trashCost = Double.parseDouble(et_datePicker_cost.getText().toString());
            mDatabaseHelper.addData_toDueDate("trash", trashDueDate, trashCost);
            text_trash.setText("Trash. Due date " + trashDueDate + " of every month. $" + trashCost);
            trashFlag = 0;
        }
        datePickerDialog.dismiss();
    }
}
