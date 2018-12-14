package willhtun.homepal;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

public class SettingsNDueDates extends AppCompatActivity {

    int rentDueDate = 1;
    int carDueDate = 1;
    int internetDueDate = 1;
    int mobileDueDate = 1;
    int electricityDueDate = 1;
    int waterDueDate = 1;
    int gasDueDate = 1;
    int trashDueDate = 1;
    int custom1DueDate = 1;
    int custom2DueDate = 1;
    int custom3DueDate = 1;
    int custom4DueDate = 1;
    int custom5DueDate = 1;

    int rentFlag = 0;
    int carFlag = 0;
    int internetFlag = 0;
    int mobileFlag = 0;
    int electricityFlag = 0;
    int waterFlag = 0;
    int gasFlag = 0;
    int trashFlag = 0;
    int custom1Flag = 0;
    int custom2Flag = 0;
    int custom3Flag = 0;
    int custom4Flag = 0;
    int custom5Flag = 0;

    NumberPicker np_datePicker_day;
    AlertDialog datePickerDialog;

    DatabaseHelper mDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDatabaseHelper = new DatabaseHelper(getApplicationContext());

        rentDueDate = mDatabaseHelper.getDate_fromDueDate("rent");
        if (rentDueDate < 0) rentDueDate = 99;
        carDueDate = mDatabaseHelper.getDate_fromDueDate("car");
        if (carDueDate < 0) carDueDate = 99;
        internetDueDate = mDatabaseHelper.getDate_fromDueDate("internet");
        if (internetDueDate < 0) internetDueDate = 99;
        mobileDueDate = mDatabaseHelper.getDate_fromDueDate("mobile");
        if (mobileDueDate < 0) mobileDueDate = 99;
        electricityDueDate = mDatabaseHelper.getDate_fromDueDate("electricity");
        if (electricityDueDate < 0) electricityDueDate = 99;
        waterDueDate = mDatabaseHelper.getDate_fromDueDate("water");
        if (waterDueDate < 0) waterDueDate = 99;
        gasDueDate = mDatabaseHelper.getDate_fromDueDate("gas");
        if (gasDueDate < 0) gasDueDate = 99;
        trashDueDate = mDatabaseHelper.getDate_fromDueDate("trash");
        if (trashDueDate < 0) trashDueDate = 99;
        custom1DueDate = mDatabaseHelper.getDate_fromDueDate("custom1");
        if (custom1DueDate < 0) custom1DueDate = 99;
        custom2DueDate = mDatabaseHelper.getDate_fromDueDate("custom2");
        if (custom2DueDate < 0) custom2DueDate = 99;
        custom3DueDate = mDatabaseHelper.getDate_fromDueDate("custom3");
        if (custom3DueDate < 0) custom3DueDate = 99;
        custom4DueDate = mDatabaseHelper.getDate_fromDueDate("custom4");
        if (custom4DueDate < 0) custom4DueDate = 99;
        custom5DueDate = mDatabaseHelper.getDate_fromDueDate("custom5");
        if (custom5DueDate < 0) custom5DueDate = 99;

        setContentView(R.layout.activity_settings_n_due_dates);
    }

    public void openDialog(int i) {
        switch (i) {
            case 0:
                show_datePicker_rent();
                break;
            case 1:
                show_datePicker_car();
                break;
            case 2:
                show_datePicker_internet();
                break;
            case 3:
                show_datePicker_mobile();
                break;
            case 4:
                show_datePicker_electricity();
                break;
            case 5:
                show_datePicker_water();
                break;
            case 6:
                show_datePicker_gas();
                break;
            case 7:
                show_datePicker_trash();
                break;
            case 8:
                show_datePicker_custom1();
                break;
            case 9:
                show_datePicker_custom2();
                break;
            case 10:
                show_datePicker_custom3();
                break;
            case 11:
                show_datePicker_custom4();
                break;
            case 12:
                show_datePicker_custom5();
                break;
            default:
                break;
        }
    }

    public void show_datePicker() {
        AlertDialog.Builder datePicker_Builder = new AlertDialog.Builder(SettingsNDueDates.this);
        View datepicker_view = getLayoutInflater().inflate(R.layout.dialog_datepicker, null);
        datePicker_Builder.setView(datepicker_view);
        datePickerDialog = datePicker_Builder.create();
        datePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                rentFlag = 0;
                carFlag = 0;
                internetFlag = 0;
                mobileFlag = 0;
                electricityFlag = 0;
                waterFlag = 0;
                gasFlag = 0;
                trashFlag = 0;
                custom1Flag = 0;
                custom2Flag = 0;
                custom3Flag = 0;
                custom4Flag = 0;
                custom5Flag = 0;
            }
        });

        np_datePicker_day = datepicker_view.findViewById(R.id.dialog_datePicker_day);
        np_datePicker_day.setMaxValue(31);
        np_datePicker_day.setMinValue(1);

        if (rentFlag == 1) {
            np_datePicker_day.setValue(rentDueDate);
        }
        else if (carFlag == 1) {
            np_datePicker_day.setValue(carDueDate);
        }
        else if (internetFlag == 1) {
            np_datePicker_day.setValue(internetDueDate);
        }
        else if (mobileFlag == 1) {
            np_datePicker_day.setValue(mobileDueDate);
        }
        else if (electricityFlag == 1) {
            np_datePicker_day.setValue(electricityDueDate);
        }
        else if (waterFlag == 1) {
            np_datePicker_day.setValue(waterDueDate);
        }
        else if (gasFlag == 1) {
            np_datePicker_day.setValue(gasDueDate);
        }
        else if (trashFlag == 1) {
            np_datePicker_day.setValue(trashDueDate);
        }
        else if (custom1Flag == 1) {
            np_datePicker_day.setValue(custom1DueDate);
        }
        else if (custom2Flag == 1) {
            np_datePicker_day.setValue(custom2DueDate);
        }
        else if (custom3Flag == 1) {
            np_datePicker_day.setValue(custom3DueDate);
        }
        else if (custom4Flag == 1) {
            np_datePicker_day.setValue(custom4DueDate);
        }
        else if (custom5Flag == 1) {
            np_datePicker_day.setValue(custom5DueDate);
        }
        datePickerDialog.show();
    }

    public void show_datePicker_rent() {
        rentFlag = 1;
        show_datePicker();
    }
    public void show_datePicker_car() {
        carFlag = 1;
        show_datePicker();
    }
    public void show_datePicker_internet() {
        internetFlag = 1;
        show_datePicker();
    }
    public void show_datePicker_mobile() {
        mobileFlag = 1;
        show_datePicker();
    }
    public void show_datePicker_electricity() {
        electricityFlag = 1;
        show_datePicker();
    }
    public void show_datePicker_water() {
        waterFlag = 1;
        show_datePicker();
    }
    public void show_datePicker_gas() {
        gasFlag = 1;
        show_datePicker();
    }
    public void show_datePicker_trash() {
        trashFlag = 1;
        show_datePicker();
    }
    public void show_datePicker_custom1() {
        custom1Flag = 1;
        show_datePicker();
    }
    public void show_datePicker_custom2() {
        custom2Flag = 1;
        show_datePicker();
    }
    public void show_datePicker_custom3() {
        custom3Flag = 1;
        show_datePicker();
    }
    public void show_datePicker_custom4() {
        custom4Flag = 1;
        show_datePicker();
    }
    public void show_datePicker_custom5() {
        custom5Flag = 1;
        show_datePicker();
    }

    public void setDatePicker(View view) {
        if (rentFlag == 1) {
            rentDueDate = np_datePicker_day.getValue();
            mDatabaseHelper.addData_toDueDate("rent", rentDueDate, 0);
            rentFlag = 0;
        }
        else if (carFlag == 1) {
            carDueDate = np_datePicker_day.getValue();
            mDatabaseHelper.addData_toDueDate("car", carDueDate, 0);
            carFlag = 0;
        }
        else if (internetFlag == 1) {
            internetDueDate = np_datePicker_day.getValue();
            mDatabaseHelper.addData_toDueDate("internet", internetDueDate, 0);
            internetFlag = 0;
        }
        else if (mobileFlag == 1) {
            mobileDueDate = np_datePicker_day.getValue();
            mDatabaseHelper.addData_toDueDate("mobile", mobileDueDate, 0);
            mobileFlag = 0;
        }
        else if (electricityFlag == 1) {
            electricityDueDate = np_datePicker_day.getValue();
            mDatabaseHelper.addData_toDueDate("electricity", electricityDueDate, 0);
            electricityFlag = 0;
        }
        else if (waterFlag == 1) {
            waterDueDate = np_datePicker_day.getValue();
            mDatabaseHelper.addData_toDueDate("water", waterDueDate, 0);
            waterFlag = 0;
        }
        else if (gasFlag == 1) {
            gasDueDate = np_datePicker_day.getValue();
            mDatabaseHelper.addData_toDueDate("gas", gasDueDate, 0);
            gasFlag = 0;
        }
        else if (trashFlag == 1) {
            trashDueDate = np_datePicker_day.getValue();
            mDatabaseHelper.addData_toDueDate("trash", trashDueDate, 0);
            trashFlag = 0;
        }
        else if (custom1Flag == 1) {
            custom1DueDate = np_datePicker_day.getValue();
            mDatabaseHelper.addData_toDueDate("custom1", custom1DueDate, 0);
            custom1Flag = 0;
        }
        else if (custom2Flag == 1) {
            custom2DueDate = np_datePicker_day.getValue();
            mDatabaseHelper.addData_toDueDate("custom2", custom2DueDate, 0);
            custom2Flag = 0;
        }
        else if (custom3Flag == 1) {
            custom3DueDate = np_datePicker_day.getValue();
            mDatabaseHelper.addData_toDueDate("custom3", custom3DueDate, 0);
            custom3Flag = 0;
        }
        else if (custom4Flag == 1) {
            custom4DueDate = np_datePicker_day.getValue();
            mDatabaseHelper.addData_toDueDate("custom4", custom4DueDate, 0);
            custom4Flag = 0;
        }
        else if (custom5Flag == 1) {
            custom5DueDate = np_datePicker_day.getValue();
            mDatabaseHelper.addData_toDueDate("custom5", custom5DueDate, 0);
            custom5Flag = 0;
        }

        datePickerDialog.dismiss();

        getSupportFragmentManager().popBackStack();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.settings_window, new SettingsDueDateBills_Frag())
                .commit();
    }
}
