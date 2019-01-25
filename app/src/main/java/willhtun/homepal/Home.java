package willhtun.homepal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.provider.SyncStateContract;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class Home extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    CalendarHelper mCalendarHelper;
    SharedPreferences preferences;

    int rentDaysLeftUntil = 1;
    int carDaysLeftUntil = 1;
    int internetDaysLeftUntil = 1;
    int mobileDaysLeftUntil = 1;
    int electricityDaysLeftUntil = 1;
    int waterDaysLeftUntil = 1;
    int gasDaysLeftUntil = 1;
    int trashDaysLeftUntil = 1;
    int custom1DaysLeftUntil = 1;
    int custom2DaysLeftUntil = 1;
    int custom3DaysLeftUntil = 1;
    int custom4DaysLeftUntil = 1;
    int custom5DaysLeftUntil = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mDatabaseHelper = new DatabaseHelper(this);
        mCalendarHelper = new CalendarHelper(mDatabaseHelper, getApplicationContext());
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        loadDueDates();
        loadPaidHistory();
        loadVisibility();

        setAlarm();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDueDates();
        loadPaidHistory();
        loadVisibility();
    }

    public void open_Rent(View view) {
        Intent intent_rent = new Intent(this, Rent.class);
        startActivity(intent_rent);
    }

    public void open_Car(View view) {
        Intent intent_car = new Intent(this, Car.class);
        startActivity(intent_car);
    }

    public void open_Internet(View view) {
        Intent intent_internet = new Intent(this, Internet.class);
        startActivity(intent_internet);
    }

    public void open_Mobile(View view) {
        Intent intent_mobile = new Intent(this, Mobile.class);
        startActivity(intent_mobile);
    }

    public void open_Electricity(View view) {
        Intent intent_electricity = new Intent(this, Electricity.class);
        startActivity(intent_electricity);
    }

    public void open_Water(View view) {
        Intent intent_water = new Intent(this, Water.class);
        startActivity(intent_water);
    }

    public void open_Gas(View view) {
        Intent intent_gas = new Intent(this, Gas.class);
        startActivity(intent_gas);
    }

    public void open_Trash(View view) {
        Intent intent_trash = new Intent(this, Trash.class);
        startActivity(intent_trash);
    }

    public void open_Groceries(View view) {

    }

    public void open_Custom1(View view) {
        Intent intent_custom1 = new Intent(this, Custom1.class);
        startActivity(intent_custom1);
    }

    public void open_Custom2(View view) {
        Intent intent_custom2 = new Intent(this, Custom2.class);
        startActivity(intent_custom2);
    }

    public void open_Custom3(View view) {
        Intent intent_custom3 = new Intent(this, Custom3.class);
        startActivity(intent_custom3);
    }

    public void open_Custom4(View view) {
        Intent intent_custom4 = new Intent(this, Custom4.class);
        startActivity(intent_custom4);
    }

    public void open_Custom5(View view) {
        Intent intent_custom5 = new Intent(this, Custom5.class);
        startActivity(intent_custom5);
    }

    public void open_Settings(View view) {
        Intent intent_settingsN = new Intent(this, SettingsN.class);
        startActivity(intent_settingsN);
    }

    private void loadDueDates() {

        int warningPeriod = mDatabaseHelper.getDate_fromDueDate("reminder_day");

        rentDaysLeftUntil = mCalendarHelper.getDaysLeftUntil("rent");
        carDaysLeftUntil = mCalendarHelper.getDaysLeftUntil("car");
        internetDaysLeftUntil = mCalendarHelper.getDaysLeftUntil("internet");
        mobileDaysLeftUntil = mCalendarHelper.getDaysLeftUntil("mobile");
        electricityDaysLeftUntil = mCalendarHelper.getDaysLeftUntil("electricity");
        waterDaysLeftUntil = mCalendarHelper.getDaysLeftUntil("water");
        gasDaysLeftUntil = mCalendarHelper.getDaysLeftUntil("gas");
        trashDaysLeftUntil = mCalendarHelper.getDaysLeftUntil("trash");
        custom1DaysLeftUntil = mCalendarHelper.getDaysLeftUntil("custom1");
        custom2DaysLeftUntil = mCalendarHelper.getDaysLeftUntil("custom2");
        custom3DaysLeftUntil = mCalendarHelper.getDaysLeftUntil("custom3");
        custom4DaysLeftUntil = mCalendarHelper.getDaysLeftUntil("custom4");
        custom5DaysLeftUntil = mCalendarHelper.getDaysLeftUntil("custom5");


        ((TextView) findViewById(R.id.rent_diamondText)).setText(String.valueOf(rentDaysLeftUntil));
        ((TextView) findViewById(R.id.car_diamondText)).setText(String.valueOf(carDaysLeftUntil));
        ((TextView) findViewById(R.id.internet_diamondText)).setText(String.valueOf(internetDaysLeftUntil));
        ((TextView) findViewById(R.id.mobile_diamondText)).setText(String.valueOf(mobileDaysLeftUntil));
        ((TextView) findViewById(R.id.electricity_diamondText)).setText(String.valueOf(electricityDaysLeftUntil));
        ((TextView) findViewById(R.id.water_diamondText)).setText(String.valueOf(waterDaysLeftUntil));
        ((TextView) findViewById(R.id.gas_diamondText)).setText(String.valueOf(gasDaysLeftUntil));
        ((TextView) findViewById(R.id.trash_diamondText)).setText(String.valueOf(trashDaysLeftUntil));
        ((TextView) findViewById(R.id.custom1_diamondText)).setText(String.valueOf(custom1DaysLeftUntil));
        ((TextView) findViewById(R.id.custom2_diamondText)).setText(String.valueOf(custom2DaysLeftUntil));
        ((TextView) findViewById(R.id.custom3_diamondText)).setText(String.valueOf(custom3DaysLeftUntil));
        ((TextView) findViewById(R.id.custom4_diamondText)).setText(String.valueOf(custom4DaysLeftUntil));
        ((TextView) findViewById(R.id.custom5_diamondText)).setText(String.valueOf(custom5DaysLeftUntil));


        if (preferences.getBoolean("overdue_rent", false)) {
            ((ImageView) findViewById(R.id.rent_greyDiamonds)).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.rent_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
            ((TextView) findViewById(R.id.rent_diamondText)).setText("!");
        }
        else if (rentDaysLeftUntil <= 3) {
            ((ImageView) findViewById(R.id.rent_greyDiamonds)).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.rent_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
        }
        else if (rentDaysLeftUntil <= warningPeriod) {
            ((ImageView) findViewById(R.id.rent_greyDiamonds)).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.rent_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
        }
        else {
            ((ImageView) findViewById(R.id.rent_greyDiamonds)).setImageResource(R.drawable.ic_border_greendiamond);
            ((TextView) findViewById(R.id.rent_diamondText)).setTextColor(Color.parseColor("#000000"));
        }
        //------------------------------------------------------------------------------------------------------------
        if (preferences.getBoolean("overdue_car", false)) {
            ((ImageView) findViewById(R.id.car_greyDiamonds)).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.car_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
            ((TextView) findViewById(R.id.car_diamondText)).setText("!");
        }
        else if (carDaysLeftUntil <= 3) {
            ((ImageView) findViewById(R.id.car_greyDiamonds)).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.car_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
        }
        else if (carDaysLeftUntil <= warningPeriod) {
            ((ImageView) findViewById(R.id.car_greyDiamonds)).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.car_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
        }
        else {
            ((ImageView) findViewById(R.id.car_greyDiamonds)).setImageResource(R.drawable.ic_border_greendiamond);
            ((TextView) findViewById(R.id.car_diamondText)).setTextColor(Color.parseColor("#000000"));
        }
        //------------------------------------------------------------------------------------------------------------
        if (preferences.getBoolean("overdue_internet", false)) {
            ((ImageView) findViewById(R.id.internet_greyDiamonds)).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.internet_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
            ((TextView) findViewById(R.id.internet_diamondText)).setText("!");
        }
        else if (internetDaysLeftUntil <= 3) {
            ((ImageView) findViewById(R.id.internet_greyDiamonds)).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.internet_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
        }
        else if (internetDaysLeftUntil <= warningPeriod) {
            ((ImageView) findViewById(R.id.internet_greyDiamonds)).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.internet_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
        }
        else {
            ((ImageView) findViewById(R.id.internet_greyDiamonds)).setImageResource(R.drawable.ic_border_greendiamond);
            ((TextView) findViewById(R.id.internet_diamondText)).setTextColor(Color.parseColor("#000000"));
        }
        //------------------------------------------------------------------------------------------------------------
        if (preferences.getBoolean("overdue_mobile", false)) {
            ((ImageView) findViewById(R.id.mobile_greyDiamonds)).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.mobile_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
            ((TextView) findViewById(R.id.mobile_diamondText)).setText("!");
        }
        else if (mobileDaysLeftUntil <= 3) {
            ((ImageView) findViewById(R.id.mobile_greyDiamonds)).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.mobile_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
        }
        else if (mobileDaysLeftUntil <= warningPeriod) {
            ((ImageView) findViewById(R.id.mobile_greyDiamonds)).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.mobile_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
        }
        else {
            ((ImageView) findViewById(R.id.mobile_greyDiamonds)).setImageResource(R.drawable.ic_border_greendiamond);
            ((TextView) findViewById(R.id.mobile_diamondText)).setTextColor(Color.parseColor("#000000"));
        }
        //------------------------------------------------------------------------------------------------------------
        if (preferences.getBoolean("overdue_electricity", false)) {
            ((ImageView) findViewById(R.id.electricity_greyDiamonds)).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.electricity_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
            ((TextView) findViewById(R.id.electricity_diamondText)).setText("!");
        }
        else if (electricityDaysLeftUntil <= 3) {
            ((ImageView) findViewById(R.id.electricity_greyDiamonds)).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.electricity_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
        }
        else if (electricityDaysLeftUntil <= warningPeriod) {
            ((ImageView) findViewById(R.id.electricity_greyDiamonds)).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.electricity_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
        }
        else {
            ((ImageView) findViewById(R.id.electricity_greyDiamonds)).setImageResource(R.drawable.ic_border_greendiamond);
            ((TextView) findViewById(R.id.electricity_diamondText)).setTextColor(Color.parseColor("#000000"));
        }
        //------------------------------------------------------------------------------------------------------------
        if (preferences.getBoolean("overdue_water", false)) {
            ((ImageView) findViewById(R.id.water_greyDiamonds)).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.water_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
            ((TextView) findViewById(R.id.water_diamondText)).setText("!");
        }
        else if (waterDaysLeftUntil <= 3) {
            ((ImageView) findViewById(R.id.water_greyDiamonds)).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.water_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
        }
        else if (waterDaysLeftUntil <= warningPeriod) {
            ((ImageView) findViewById(R.id.water_greyDiamonds)).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.water_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
        }
        else {
            ((ImageView) findViewById(R.id.water_greyDiamonds)).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.water_diamondText)).setTextColor(Color.parseColor("#000000"));
        }
        //------------------------------------------------------------------------------------------------------------
        if (preferences.getBoolean("overdue_gas", false)) {
            ((ImageView) findViewById(R.id.gas_greyDiamonds)).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.gas_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
            ((TextView) findViewById(R.id.gas_diamondText)).setText("!");
        }
        else if (gasDaysLeftUntil <= 3) {
            ((ImageView) findViewById(R.id.gas_greyDiamonds)).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.gas_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
        }
        else if (gasDaysLeftUntil <= warningPeriod) {
            ((ImageView) findViewById(R.id.gas_greyDiamonds)).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.gas_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
        }
        else {
            ((ImageView) findViewById(R.id.gas_greyDiamonds)).setImageResource(R.drawable.ic_border_greendiamond);
            ((TextView) findViewById(R.id.gas_diamondText)).setTextColor(Color.parseColor("#000000"));
        }
        //------------------------------------------------------------------------------------------------------------
        if (preferences.getBoolean("overdue_trash", false)) {
            ((ImageView) findViewById(R.id.trash_greyDiamonds)).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.trash_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
            ((TextView) findViewById(R.id.trash_diamondText)).setText("!");
        }
        else if (trashDaysLeftUntil <= 3) {
            ((ImageView) findViewById(R.id.trash_greyDiamonds)).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.trash_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
        }
        else if (trashDaysLeftUntil <= warningPeriod) {
            ((ImageView) findViewById(R.id.trash_greyDiamonds)).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.trash_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
        }
        else {
            ((ImageView) findViewById(R.id.trash_greyDiamonds)).setImageResource(R.drawable.ic_border_greendiamond);
            ((TextView) findViewById(R.id.trash_diamondText)).setTextColor(Color.parseColor("#000000"));
        }
        //------------------------------------------------------------------------------------------------------------
        if (preferences.getBoolean("overdue_custom1", false)) {
            ((ImageView) findViewById(R.id.custom1_greyDiamonds)).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.custom1_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
            ((TextView) findViewById(R.id.custom1_diamondText)).setText("!");
        }
        else if (custom1DaysLeftUntil <= 3) {
            ((ImageView) findViewById(R.id.custom1_greyDiamonds)).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.custom1_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
        }
        else if (custom1DaysLeftUntil <= warningPeriod) {
            ((ImageView) findViewById(R.id.custom1_greyDiamonds)).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.custom1_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
        }
        else {
            ((ImageView) findViewById(R.id.custom1_greyDiamonds)).setImageResource(R.drawable.ic_border_greendiamond);
            ((TextView) findViewById(R.id.custom1_diamondText)).setTextColor(Color.parseColor("#000000"));
        }
        //------------------------------------------------------------------------------------------------------------
        if (preferences.getBoolean("overdue_custom2", false)) {
            ((ImageView) findViewById(R.id.custom2_greyDiamonds)).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.custom2_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
            ((TextView) findViewById(R.id.custom2_diamondText)).setText("!");
        }
        else if (custom2DaysLeftUntil <= 3) {
            ((ImageView) findViewById(R.id.custom2_greyDiamonds)).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.custom2_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
        }
        else if (custom2DaysLeftUntil <= warningPeriod) {
            ((ImageView) findViewById(R.id.custom2_greyDiamonds)).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.custom2_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
        }
        else {
            ((ImageView) findViewById(R.id.custom2_greyDiamonds)).setImageResource(R.drawable.ic_border_greendiamond);
            ((TextView) findViewById(R.id.custom2_diamondText)).setTextColor(Color.parseColor("#000000"));
        }
        //------------------------------------------------------------------------------------------------------------
        if (preferences.getBoolean("overdue_custom3", false)) {
            ((ImageView) findViewById(R.id.custom3_greyDiamonds)).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.custom3_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
            ((TextView) findViewById(R.id.custom3_diamondText)).setText("!");
        }
        else if (custom3DaysLeftUntil <= 3) {
            ((ImageView) findViewById(R.id.custom3_greyDiamonds)).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.custom3_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
        }
        else if (custom3DaysLeftUntil <= warningPeriod) {
            ((ImageView) findViewById(R.id.custom3_greyDiamonds)).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.custom3_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
        }
        else {
            ((ImageView) findViewById(R.id.custom3_greyDiamonds)).setImageResource(R.drawable.ic_border_greendiamond);
            ((TextView) findViewById(R.id.custom3_diamondText)).setTextColor(Color.parseColor("#000000"));
        }
        //------------------------------------------------------------------------------------------------------------
        if (preferences.getBoolean("overdue_custom4", false)) {
            ((ImageView) findViewById(R.id.custom4_greyDiamonds)).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.custom4_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
            ((TextView) findViewById(R.id.custom4_diamondText)).setText("!");
        }
        else if (custom4DaysLeftUntil <= 3) {
            ((ImageView) findViewById(R.id.custom4_greyDiamonds)).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.custom4_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
        }
        else if (custom4DaysLeftUntil <= warningPeriod) {
            ((ImageView) findViewById(R.id.custom4_greyDiamonds)).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.custom4_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
        }
        else {
            ((ImageView) findViewById(R.id.custom4_greyDiamonds)).setImageResource(R.drawable.ic_border_greendiamond);
            ((TextView) findViewById(R.id.custom4_diamondText)).setTextColor(Color.parseColor("#000000"));
        }
        //------------------------------------------------------------------------------------------------------------
        if (preferences.getBoolean("overdue_custom5", false)) {
            ((ImageView) findViewById(R.id.custom5_greyDiamonds)).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.custom5_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
            ((TextView) findViewById(R.id.custom5_diamondText)).setText("!");
        }
        else if (custom5DaysLeftUntil <= 3) {
            ((ImageView) findViewById(R.id.custom5_greyDiamonds)).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.custom5_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
        }
        else if (custom5DaysLeftUntil <= warningPeriod) {
            ((ImageView) findViewById(R.id.custom5_greyDiamonds)).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.custom5_diamondText)).setTextColor(Color.parseColor("#A12C2C"));
        }
        else {
            ((ImageView) findViewById(R.id.custom5_greyDiamonds)).setImageResource(R.drawable.ic_border_greendiamond);
            ((TextView) findViewById(R.id.custom5_diamondText)).setTextColor(Color.parseColor("#000000"));
        }
    }

    public void loadPaidHistory() {
        if (mDatabaseHelper.isDataExists_fromHistory("rent", mCalendarHelper.getCycleMonthYear("rent"),0)) {
            ((TextView) findViewById(R.id.rent_diamondText)).setText("");
            ((ImageView) findViewById(R.id.rent_greyDiamonds)).setVisibility(View.VISIBLE);
            ((ImageView) findViewById(R.id.rent_greyDiamonds)).setImageResource(R.drawable.ic_border_checkedgreendiamond);
        }
        if (mDatabaseHelper.isDataExists_fromHistory("car", mCalendarHelper.getCycleMonthYear("car"),0)) {
            ((TextView) findViewById(R.id.car_diamondText)).setText("");
            ((ImageView) findViewById(R.id.car_greyDiamonds)).setVisibility(View.VISIBLE);
            ((ImageView) findViewById(R.id.car_greyDiamonds)).setImageResource(R.drawable.ic_border_checkedgreendiamond);
        }
        if (mDatabaseHelper.isDataExists_fromHistory("internet", mCalendarHelper.getCycleMonthYear("internet"),0)) {
            ((TextView) findViewById(R.id.internet_diamondText)).setText("");
            ((ImageView) findViewById(R.id.internet_greyDiamonds)).setVisibility(View.VISIBLE);
            ((ImageView) findViewById(R.id.internet_greyDiamonds)).setImageResource(R.drawable.ic_border_checkedgreendiamond);
        }
        if (mDatabaseHelper.isDataExists_fromHistory("mobile", mCalendarHelper.getCycleMonthYear("mobile"),0)) {
            ((TextView) findViewById(R.id.mobile_diamondText)).setText("");
            ((ImageView) findViewById(R.id.mobile_greyDiamonds)).setVisibility(View.VISIBLE);
            ((ImageView) findViewById(R.id.mobile_greyDiamonds)).setImageResource(R.drawable.ic_border_checkedgreendiamond);
        }
        if (mDatabaseHelper.isDataExists_fromHistory("electricity", mCalendarHelper.getCycleMonthYear("electricity"),0)) {
            ((TextView) findViewById(R.id.electricity_diamondText)).setText("");
            ((ImageView) findViewById(R.id.electricity_greyDiamonds)).setVisibility(View.VISIBLE);
            ((ImageView) findViewById(R.id.electricity_greyDiamonds)).setImageResource(R.drawable.ic_border_checkedgreendiamond);
        }
        if (mDatabaseHelper.isDataExists_fromHistory("water", mCalendarHelper.getCycleMonthYear("water"),0)) {
            ((TextView) findViewById(R.id.water_diamondText)).setText("");
            ((ImageView) findViewById(R.id.water_greyDiamonds)).setVisibility(View.VISIBLE);
            ((ImageView) findViewById(R.id.water_greyDiamonds)).setImageResource(R.drawable.ic_border_checkedgreendiamond);
        }
        if (mDatabaseHelper.isDataExists_fromHistory("gas", mCalendarHelper.getCycleMonthYear("gas"),0)) {
            ((TextView) findViewById(R.id.gas_diamondText)).setText("");
            ((ImageView) findViewById(R.id.gas_greyDiamonds)).setVisibility(View.VISIBLE);
            ((ImageView) findViewById(R.id.gas_greyDiamonds)).setImageResource(R.drawable.ic_border_checkedgreendiamond);
        }
        if (mDatabaseHelper.isDataExists_fromHistory("trash", mCalendarHelper.getCycleMonthYear("trash"),0)) {
            ((TextView) findViewById(R.id.trash_diamondText)).setText("");
            ((ImageView) findViewById(R.id.trash_greyDiamonds)).setVisibility(View.VISIBLE);
            ((ImageView) findViewById(R.id.trash_greyDiamonds)).setImageResource(R.drawable.ic_border_checkedgreendiamond);
        }
        if (mDatabaseHelper.isDataExists_fromHistory("custom1", mCalendarHelper.getCycleMonthYear("custom1"),0)) {
            ((TextView) findViewById(R.id.custom1_diamondText)).setText("");
            ((ImageView) findViewById(R.id.custom1_greyDiamonds)).setVisibility(View.VISIBLE);
            ((ImageView) findViewById(R.id.custom1_greyDiamonds)).setImageResource(R.drawable.ic_border_checkedgreendiamond);
        }
        if (mDatabaseHelper.isDataExists_fromHistory("custom2", mCalendarHelper.getCycleMonthYear("custom2"),0)) {
            ((TextView) findViewById(R.id.custom2_diamondText)).setText("");
            ((ImageView) findViewById(R.id.custom2_greyDiamonds)).setVisibility(View.VISIBLE);
            ((ImageView) findViewById(R.id.custom2_greyDiamonds)).setImageResource(R.drawable.ic_border_checkedgreendiamond);
        }
        if (mDatabaseHelper.isDataExists_fromHistory("custom3", mCalendarHelper.getCycleMonthYear("custom3"),0)) {
            ((TextView) findViewById(R.id.custom3_diamondText)).setText("");
            ((ImageView) findViewById(R.id.custom3_greyDiamonds)).setVisibility(View.VISIBLE);
            ((ImageView) findViewById(R.id.custom3_greyDiamonds)).setImageResource(R.drawable.ic_border_checkedgreendiamond);
        }
        if (mDatabaseHelper.isDataExists_fromHistory("custom4", mCalendarHelper.getCycleMonthYear("custom4"),0)) {
            ((TextView) findViewById(R.id.custom4_diamondText)).setText("");
            ((ImageView) findViewById(R.id.custom4_greyDiamonds)).setVisibility(View.VISIBLE);
            ((ImageView) findViewById(R.id.custom4_greyDiamonds)).setImageResource(R.drawable.ic_border_checkedgreendiamond);
        }
        if (mDatabaseHelper.isDataExists_fromHistory("custom5", mCalendarHelper.getCycleMonthYear("custom5"),0)) {
            ((TextView) findViewById(R.id.custom5_diamondText)).setText("");
            ((ImageView) findViewById(R.id.custom5_greyDiamonds)).setVisibility(View.VISIBLE);
            ((ImageView) findViewById(R.id.custom5_greyDiamonds)).setImageResource(R.drawable.ic_border_checkedgreendiamond);
        }
    }

    public void loadVisibility() {
        boolean empty = true;

        findViewById(R.id.constraint_info).setVisibility(View.GONE);
        findViewById(R.id.constraint_rent).setVisibility(View.GONE);
        findViewById(R.id.constraint_car).setVisibility(View.GONE);
        findViewById(R.id.constraint_internet).setVisibility(View.GONE);
        findViewById(R.id.constraint_mobile).setVisibility(View.GONE);
        findViewById(R.id.constraint_electricity).setVisibility(View.GONE);
        findViewById(R.id.constraint_water).setVisibility(View.GONE);
        findViewById(R.id.constraint_gas).setVisibility(View.GONE);
        findViewById(R.id.constraint_trash).setVisibility(View.GONE);
        findViewById(R.id.constraint_groceries).setVisibility(View.GONE);
        findViewById(R.id.constraint_custom1).setVisibility(View.GONE);
        findViewById(R.id.constraint_custom2).setVisibility(View.GONE);
        findViewById(R.id.constraint_custom3).setVisibility(View.GONE);
        findViewById(R.id.constraint_custom4).setVisibility(View.GONE);
        findViewById(R.id.constraint_custom5).setVisibility(View.GONE);

        if (preferences.getBoolean("check_box_preference_bills_rent", false)) {
            findViewById(R.id.constraint_rent).setVisibility(View.VISIBLE);
            empty = false;
        }
        if (preferences.getBoolean("check_box_preference_bills_car", false)) {
            findViewById(R.id.constraint_car).setVisibility(View.VISIBLE);
            empty = false;
        }
        if (preferences.getBoolean("check_box_preference_bills_internet", false)) {
            findViewById(R.id.constraint_internet).setVisibility(View.VISIBLE);
            empty = false;
        }
        if (preferences.getBoolean("check_box_preference_bills_mobile", false)) {
            findViewById(R.id.constraint_mobile).setVisibility(View.VISIBLE);
            empty = false;
        }
        if (preferences.getBoolean("check_box_preference_bills_electricity", false)) {
            findViewById(R.id.constraint_electricity).setVisibility(View.VISIBLE);
            empty = false;
        }
        if (preferences.getBoolean("check_box_preference_bills_water", false)) {
            findViewById(R.id.constraint_water).setVisibility(View.VISIBLE);
            empty = false;
        }
        if (preferences.getBoolean("check_box_preference_bills_gas", false)) {
            findViewById(R.id.constraint_gas).setVisibility(View.VISIBLE);
            empty = false;
        }
        if (preferences.getBoolean("check_box_preference_bills_trash", false)) {
            findViewById(R.id.constraint_trash).setVisibility(View.VISIBLE);
            empty = false;
        }
        if (preferences.getBoolean("check_box_preference_bills_groceries", false)) {
            findViewById(R.id.constraint_groceries).setVisibility(View.VISIBLE);
            empty = false;
        }
        if (preferences.getBoolean("custombill1_On", false)) {
            ((TextView)findViewById(R.id.custom1_staticText)).setText(preferences.getString("name_preference_bills_custom1", "Custom 1"));
            if (preferences.getString("name_preference_bills_custom1", "Custom 1").equals(""))
                ((TextView)findViewById(R.id.custom1_staticText)).setText("Custom 1");
            findViewById(R.id.constraint_custom1).setVisibility(View.VISIBLE);
            empty = false;
        }
        if (preferences.getBoolean("custombill2_On", false)) {
            ((TextView)findViewById(R.id.custom2_staticText)).setText(preferences.getString("name_preference_bills_custom2", "Custom 2"));
            if (preferences.getString("name_preference_bills_custom2", "Custom 2").equals(""))
                ((TextView)findViewById(R.id.custom2_staticText)).setText("Custom 2");
            findViewById(R.id.constraint_custom2).setVisibility(View.VISIBLE);
            empty = false;
        }
        if (preferences.getBoolean("custombill3_On", false)) {
            ((TextView)findViewById(R.id.custom3_staticText)).setText(preferences.getString("name_preference_bills_custom3", "Custom 3"));
            if (preferences.getString("name_preference_bills_custom3", "Custom 3").equals(""))
                ((TextView)findViewById(R.id.custom3_staticText)).setText("Custom 3");
            findViewById(R.id.constraint_custom3).setVisibility(View.VISIBLE);
            empty = false;
        }
        if (preferences.getBoolean("custombill4_On", false)) {
            ((TextView)findViewById(R.id.custom4_staticText)).setText(preferences.getString("name_preference_bills_custom4", "Custom 4"));
            if (preferences.getString("name_preference_bills_custom4", "Custom 4").equals(""))
                ((TextView)findViewById(R.id.custom4_staticText)).setText("Custom 4");
            findViewById(R.id.constraint_custom4).setVisibility(View.VISIBLE);
            empty = false;
        }
        if (preferences.getBoolean("custombill5_On", false)) {
            ((TextView)findViewById(R.id.custom5_staticText)).setText(preferences.getString("name_preference_bills_custom5", "Custom 5"));
            if (preferences.getString("name_preference_bills_custom5", "Custom 5").equals(""))
                ((TextView)findViewById(R.id.custom5_staticText)).setText("Custom 5");
            findViewById(R.id.constraint_custom5).setVisibility(View.VISIBLE);
            empty = false;
        }

        if (empty) {
            findViewById(R.id.constraint_info).setVisibility(View.VISIBLE);
        }
    }

    public void setAlarm() {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Calendar cur = Calendar.getInstance();

        if (cur.after(calendar)) {
            calendar.add(Calendar.DATE, 1);
        }

        Intent myIntent = new Intent(this, NotificationScheduler.class);
        int ALARM1_ID = 24971;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this, ALARM1_ID, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

    }
}