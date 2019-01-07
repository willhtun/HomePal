package willhtun.homepal;

import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Electricity extends AppCompatActivity {

    String type = "electricity";
    int duedate = 0;
    int duemonth = 0;
    int dueyear = 0;
    float price = 0;

    DatabaseHelper mDatabaseHelper;
    CalendarHelper mCalendarHelper;
    SharedPreferences preferences;

    EditText et_pricebox;
    AlertDialog pricepickerdialog;
    AlertDialog pastduesdialog;

    ImageView electricity_p0_btn;
    ImageView electricity_p1_btn;
    ImageView electricity_p2_btn;
    ImageView electricity_p3_btn;
    ImageView electricity_p4_btn;
    ImageView electricity_p5_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        mDatabaseHelper = new DatabaseHelper(getApplicationContext());
        mCalendarHelper = new CalendarHelper(mDatabaseHelper, getApplicationContext());

        duedate = mDatabaseHelper.getDate_fromDueDate(type); if (duedate < 0) duedate = 1;
        duemonth = mCalendarHelper.getCycleMonth(type);
        dueyear = mCalendarHelper.getCycleYear(type);

        electricity_p0_btn = findViewById(R.id.electricity_payBtn);
        electricity_p1_btn = findViewById(R.id.electricity_person1);
        electricity_p2_btn = findViewById(R.id.electricity_person2);
        electricity_p3_btn = findViewById(R.id.electricity_person3);
        electricity_p4_btn = findViewById(R.id.electricity_person4);
        electricity_p5_btn = findViewById(R.id.electricity_person5);

        // Populate screen with information
        ((TextView) findViewById(R.id.electricity_dueDate)).setText("Due: " + duemonth + "/" + duedate);
        String temp_price = mDatabaseHelper.getMostRecentPrice_fromHistory(type);
        if (temp_price == "") {
            price = 0;
            ((TextView) findViewById(R.id.electricity_priceboxtext)).setText("$0.00");
        }
        else {
            price = Float.valueOf(temp_price);
            ((TextView) findViewById(R.id.electricity_priceboxtext)).setText("$" + String.format("%.2f", price));
        }

        mDatabaseHelper.addData_toHistory(type, "7_2018", mCalendarHelper.getTodayDate(), 1, 100.00f);
        mDatabaseHelper.addData_toHistory(type, "8_2018", mCalendarHelper.getTodayDate(), 8, 43.12f);

        mDatabaseHelper.addData_toHistory(type, "10_2018", mCalendarHelper.getTodayDate(), 0, price);

        mDatabaseHelper.addData_toHistory(type, "11_2018", mCalendarHelper.getTodayDate(), 0, 809f);
        mDatabaseHelper.addData_toHistory(type, "11_2018", mCalendarHelper.getTodayDate(), 1, price);
        mDatabaseHelper.addData_toHistory(type, "11_2018", mCalendarHelper.getTodayDate(), 5, price);

        mDatabaseHelper.addData_toHistory(type, "12_2018", mCalendarHelper.getTodayDate(), 0, price);
        mDatabaseHelper.addData_toHistory(type, "12_2018", mCalendarHelper.getTodayDate(), 1, price);
        mDatabaseHelper.addData_toHistory(type, "12_2018", mCalendarHelper.getTodayDate(), 2, price);
        mDatabaseHelper.addData_toHistory(type, "12_2018", mCalendarHelper.getTodayDate(), 3, price);
        mDatabaseHelper.addData_toHistory(type, "12_2018", mCalendarHelper.getTodayDate(), 4, price);
        mDatabaseHelper.addData_toHistory(type, "12_2018", mCalendarHelper.getTodayDate(), 5, price);

        preferences.edit().putBoolean("overdue_electricity", true).apply();

        loadHistory();
        loadHousemates();
        loadButtons();
    }

    public void electricity_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), mCalendarHelper.getTodayDate(), 0, price);
        ((TextView) findViewById(R.id.electricity_payBtn_text)).setText("PAID");
        ((TextView) findViewById(R.id.electricity_payBtn_text)).setTextColor(Color.WHITE);
        electricity_p0_btn.setImageResource(R.drawable.generic_paidbutton);
        loadHistory();
    }

    public void electricity_person1_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 111, 1, price);
        electricity_p1_btn.setImageResource(R.drawable.generic_personpaidbutton);
        ((TextView) findViewById(R.id.electricity_person1_text)).setTextColor(Color.WHITE);
        loadHistory();
    }

    public void electricity_person2_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 222, 2, price);
        electricity_p2_btn.setImageResource(R.drawable.generic_personpaidbutton);
        ((TextView) findViewById(R.id.electricity_person2_text)).setTextColor(Color.WHITE);
        loadHistory();
    }

    public void electricity_person3_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 333, 3, price);
        electricity_p3_btn.setImageResource(R.drawable.generic_personpaidbutton);
        ((TextView) findViewById(R.id.electricity_person3_text)).setTextColor(Color.WHITE);
        loadHistory();
    }

    public void electricity_person4_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 444, 4, price);
        electricity_p4_btn.setImageResource(R.drawable.generic_personpaidbutton);
        ((TextView) findViewById(R.id.electricity_person4_text)).setTextColor(Color.WHITE);
        loadHistory();
    }

    public void electricity_person5_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 555, 5, price);
        electricity_p5_btn.setImageResource(R.drawable.generic_personpaidbutton);
        ((TextView) findViewById(R.id.electricity_person5_text)).setTextColor(Color.WHITE);
        loadHistory();
    }

    public void loadHousemates() {
        String name1 = preferences.getString("name_preference_housemates1", "--");
        if (name1.equals("")) name1 = "--";
        if (name1.length() > 2) name1 = name1.substring(0,2);
        String name2 = preferences.getString("name_preference_housemates2", "--");
        if (name2.equals("")) name2 = "--";
        if (name2.length() > 2) name2 = name2.substring(0,2);
        String name3 = preferences.getString("name_preference_housemates3", "--");
        if (name3.equals("")) name3 = "--";
        if (name3.length() > 2) name3 = name3.substring(0,2);
        String name4 = preferences.getString("name_preference_housemates4", "--");
        if (name4.equals("")) name4 = "--";
        if (name4.length() > 2) name4 = name4.substring(0,2);
        String name5 = preferences.getString("name_preference_housemates5", "--");
        if (name5.equals("")) name5 = "--";
        if (name5.length() > 2) name5 = name5.substring(0,2);

        ((TextView) findViewById(R.id.electricity_person1_text)).setText(name1);
        ((TextView) findViewById(R.id.electricity_person2_text)).setText(name2);
        ((TextView) findViewById(R.id.electricity_person3_text)).setText(name3);
        ((TextView) findViewById(R.id.electricity_person4_text)).setText(name4);
        ((TextView) findViewById(R.id.electricity_person5_text)).setText(name5);
    }

    public void loadButtons() {
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),0)) {
            ((TextView) findViewById(R.id.electricity_payBtn_text)).setText("PAID");
            electricity_p0_btn.setImageResource(R.drawable.generic_paidbutton);
            ((TextView) findViewById(R.id.electricity_payBtn_text)).setTextColor(Color.WHITE);
        }
        else{
            electricity_p0_btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Press and hold to pay", Toast.LENGTH_SHORT).show();
                }
            });
            electricity_p0_btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    electricity_pay();
                    return true;
                }
            });
            ((TextView) findViewById(R.id.electricity_payBtn_text)).setTextColor(Color.parseColor("#5DB699"));
        }

        if (preferences.getBoolean("settings_billSharingOn_electricity", false)) {
            if (!preferences.getBoolean("housemate1_On", false)) {
                electricity_p1_btn.setImageResource(R.drawable.generic_personunavailable);
                ((TextView) findViewById(R.id.electricity_person1_text)).setText("");
            }
            else if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type), 1)) {
                electricity_p1_btn.setImageResource(R.drawable.generic_personpaidbutton);
                ((TextView) findViewById(R.id.electricity_person1_text)).setTextColor(Color.WHITE);
            }
            else {
                electricity_p1_btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Press and hold to pay", Toast.LENGTH_SHORT).show();
                    }
                });
                electricity_p1_btn.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        electricity_person1_pay();
                        return true;
                    }
                });
            }
            if (!preferences.getBoolean("housemate2_On", false)) {
                electricity_p2_btn.setImageResource(R.drawable.generic_personunavailable);
                ((TextView) findViewById(R.id.electricity_person2_text)).setText("");
            }
            else if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type), 2)) {
                electricity_p2_btn.setImageResource(R.drawable.generic_personpaidbutton);
                ((TextView) findViewById(R.id.electricity_person2_text)).setTextColor(Color.WHITE);
            }
            else {
                electricity_p2_btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Press and hold to pay", Toast.LENGTH_SHORT).show();
                    }
                });
                electricity_p2_btn.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        electricity_person2_pay();
                        return true;
                    }
                });
            }
            if (!preferences.getBoolean("housemate3_On", false)) {
                electricity_p3_btn.setImageResource(R.drawable.generic_personunavailable);
                ((TextView) findViewById(R.id.electricity_person3_text)).setText("");
            }
            else if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type), 3)) {
                electricity_p3_btn.setImageResource(R.drawable.generic_personpaidbutton);
                ((TextView) findViewById(R.id.electricity_person3_text)).setTextColor(Color.WHITE);
            }
            else {
                electricity_p3_btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Press and hold to pay", Toast.LENGTH_SHORT).show();
                    }
                });
                electricity_p3_btn.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        electricity_person3_pay();
                        return true;
                    }
                });
            }
            if (!preferences.getBoolean("housemate4_On", false)) {
                electricity_p4_btn.setImageResource(R.drawable.generic_personunavailable);
                ((TextView) findViewById(R.id.electricity_person4_text)).setText("");
            }
            else if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type), 4)) {
                electricity_p4_btn.setImageResource(R.drawable.generic_personpaidbutton);
                ((TextView) findViewById(R.id.electricity_person4_text)).setTextColor(Color.WHITE);
            }
            else {
                electricity_p4_btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Press and hold to pay", Toast.LENGTH_SHORT).show();
                    }
                });
                electricity_p4_btn.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        electricity_person4_pay();
                        return true;
                    }
                });
            }
            if (!preferences.getBoolean("housemate5_On", false)) {
                electricity_p5_btn.setImageResource(R.drawable.generic_personunavailable);
                ((TextView) findViewById(R.id.electricity_person5_text)).setText("");
            }
            else if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type), 5)) {
                electricity_p5_btn.setImageResource(R.drawable.generic_personpaidbutton);
                ((TextView) findViewById(R.id.electricity_person5_text)).setTextColor(Color.WHITE);
            }
            else {
                electricity_p5_btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Press and hold to pay", Toast.LENGTH_SHORT).show();
                    }
                });
                electricity_p5_btn.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        electricity_person5_pay();
                        return true;
                    }
                });
            }
        }
        else {
            findViewById(R.id.electricity_payButtonsContainer).setVisibility(View.GONE);
        }
    }

    public void loadHistory() {
        LinearLayout ll = findViewById(R.id.electricity_history);
        ll.removeAllViews();
        String[][] text_raw = mDatabaseHelper.getPaidDate_fromHistory(type);

        preferences.edit().putBoolean("overdue_electricity", false).apply();

        for (int i = 0; i < 20; i++) {
            LinearLayout rl = new LinearLayout(this);
            TextView textDate = new TextView(this);
            TextView textPrice = new TextView(this);
            ImageButton duesButton = new ImageButton(this);
            String text = text_raw[0][i];
            final String history_price = text_raw[1][i];
            String type_period;
            String format_period = "";
            String personsPaidStats;
            boolean pastdues_exist = false;
            boolean overdue_exist = false;

            if (!text.equals("")) {
                int t = 0;
                int u;

                while (text.charAt(t) != '+')
                    t++;
                type_period = text.substring(1, t);

                /*
                int ii = 0, jj = 0;
                while (type_period.charAt(ii) != '_')
                    ii++;
                ii++;
                jj = ii;
                while (type_period.charAt(jj) != '_')
                    jj++;
                jj++;
                format_period += type_period.substring(ii, jj - 1) + "/" + type_period.substring(jj + 2, jj + 4);
                */

                if (type_period.equals(type + "_" + mCalendarHelper.getCycleMonthYear(type)))
                    continue;

                t++;
                u = t;
                while (text.charAt(t) != '#')
                    t++;
                personsPaidStats = text.substring(u, t);

                final String[] housemates_names = mDatabaseHelper.getHousemates_fromHistory(type_period);

                duesButton.setImageResource(R.drawable.ic_pastdues_false);
                for (int p = 0; p < 5; p++) {
                    if (!housemates_names[p].equals("--") && personsPaidStats.charAt(p) == '0') {
                        duesButton.setImageResource(R.drawable.ic_pastdues_true);
                        pastdues_exist = true;
                        break;
                    }
                }

                if (!mDatabaseHelper.isDataExists_fromHistory_typemon(type_period, 0)) {
                    duesButton.setImageResource(R.drawable.ic_pastdues_overdue);
                    overdue_exist = true;
                }

                text = text.substring(t+1);
                textDate.setText(text);
                textDate.setGravity(Gravity.CENTER_VERTICAL);
                textDate.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                textDate.setPadding(50,0,0,0);

                textPrice.setText(history_price);
                textPrice.setGravity(Gravity.CENTER_VERTICAL);
                textPrice.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
                textPrice.setPadding(0,0,0,0);

                duesButton.setBackground(null);
                duesButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
                duesButton.setPadding(40,0,50,0);

                int ii = 0;
                while (text.charAt(ii) != ' ')
                    ii++;
                format_period = text.substring(ii + 1, text.length());

                if (overdue_exist) {
                    preferences.edit().putBoolean("overdue_electricity", true).apply();
                    duesButton.setOnClickListener(new ArgsOnClickListener(this, history_price, type_period, format_period, personsPaidStats) {
                        @Override
                        public void onClick(View v) {
                            final AlertDialog.Builder pastDues_builder = new AlertDialog.Builder(Electricity.this);
                            final View pastDues_view = getLayoutInflater().inflate(R.layout.dialog_pastdues_overdue, null);
                            int num_housemates = 0;
                            final String housemate_price;
                            pastDues_builder.setView(pastDues_view);
                            pastDues_builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                @Override
                                public void onCancel(DialogInterface dialog) {
                                    loadHistory();
                                }
                            });
                            pastduesdialog = pastDues_builder.create();
                            pastduesdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            pastduesdialog.setCanceledOnTouchOutside(true);

                            ((TextView) pastDues_view.findViewById(R.id.pastdues_date)).setText(fperiod);

                            // Overdue button
                            ((EditText) pastDues_view.findViewById(R.id.pastdues_pricebox)).addTextChangedListener(new NumberTextWatcher(et_pricebox));
                            ((EditText) pastDues_view.findViewById(R.id.pastdues_pricebox)).setText("0.00");
                            ((ImageButton) pastDues_view.findViewById(R.id.pastdues_button6)).setOnLongClickListener(new View.OnLongClickListener() {
                                @Override
                                public boolean onLongClick(View v) {
                                    Float inputprice = Float.valueOf(((EditText) pastDues_view.findViewById(R.id.pastdues_pricebox)).getText().toString());
                                    mDatabaseHelper.addData_toHistory_typemon(period, mCalendarHelper.getTodayDate(), 0, inputprice);
                                    ((ImageButton) pastDues_view.findViewById(R.id.pastdues_button6)).setImageResource(R.drawable.ic_pastdues_paidbutton);
                                    ((TextView) pastDues_view.findViewById(R.id.pastdues_button6text)).setTextColor(Color.WHITE);
                                    ((TextView) pastDues_view.findViewById(R.id.pastdues_button6text)).setText("PAID");
                                    return true;
                                }
                            });

                            for (int kk = 0; kk < 5; kk++) {
                                if (persons[kk] == 1)
                                    num_housemates++;
                            }
                            housemate_price = String.valueOf(Float.parseFloat(history_price) / num_housemates);

                            if (persons[0] == 1 || housemates_names[0].equals("--")) {
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_housemate1)).setVisibility(View.GONE);
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_amount1)).setVisibility(View.GONE);
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_button1text)).setVisibility(View.GONE);
                                ((ImageButton) pastDues_view.findViewById(R.id.pastdues_button1)).setVisibility(View.GONE);
                            } else {
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_amount1)).setText(housemate_price);
                                ((ImageButton) pastDues_view.findViewById(R.id.pastdues_button1)).setOnLongClickListener(new View.OnLongClickListener() {
                                    @Override
                                    public boolean onLongClick(View v) {
                                        mDatabaseHelper.addData_toHistory_typemon(period, 99, 1, price);
                                        ((ImageButton) pastDues_view.findViewById(R.id.pastdues_button1)).setImageResource(R.drawable.ic_pastdues_paidbutton);
                                        ((TextView) pastDues_view.findViewById(R.id.pastdues_button1text)).setTextColor(Color.WHITE);
                                        ((TextView) pastDues_view.findViewById(R.id.pastdues_button1text)).setText("PAID");
                                        return true;
                                    }
                                });
                            }
                            if (persons[1] == 1 || housemates_names[1].equals("--")) {
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_housemate2)).setVisibility(View.GONE);
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_amount2)).setVisibility(View.GONE);
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_button2text)).setVisibility(View.GONE);
                                ((ImageButton) pastDues_view.findViewById(R.id.pastdues_button2)).setVisibility(View.GONE);
                            } else {
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_amount2)).setText(housemate_price);
                                ((ImageButton) pastDues_view.findViewById(R.id.pastdues_button2)).setOnLongClickListener(new View.OnLongClickListener() {
                                    @Override
                                    public boolean onLongClick(View v) {
                                        mDatabaseHelper.addData_toHistory_typemon(period, 99, 2, price);
                                        ((ImageButton) pastDues_view.findViewById(R.id.pastdues_button2)).setImageResource(R.drawable.ic_pastdues_paidbutton);
                                        ((TextView) pastDues_view.findViewById(R.id.pastdues_button2text)).setTextColor(Color.WHITE);
                                        ((TextView) pastDues_view.findViewById(R.id.pastdues_button2text)).setText("PAID");
                                        return true;
                                    }
                                });
                            }
                            if (persons[2] == 1 || housemates_names[2].equals("--")) {
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_housemate3)).setVisibility(View.GONE);
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_amount3)).setVisibility(View.GONE);
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_button3text)).setVisibility(View.GONE);
                                ((ImageButton) pastDues_view.findViewById(R.id.pastdues_button3)).setVisibility(View.GONE);
                            } else {
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_amount3)).setText(housemate_price);
                                ((ImageButton) pastDues_view.findViewById(R.id.pastdues_button3)).setOnLongClickListener(new View.OnLongClickListener() {
                                    @Override
                                    public boolean onLongClick(View v) {
                                        mDatabaseHelper.addData_toHistory_typemon(period, 99, 3, price);
                                        ((ImageButton) pastDues_view.findViewById(R.id.pastdues_button3)).setImageResource(R.drawable.ic_pastdues_paidbutton);
                                        ((TextView) pastDues_view.findViewById(R.id.pastdues_button3text)).setTextColor(Color.WHITE);
                                        ((TextView) pastDues_view.findViewById(R.id.pastdues_button3text)).setText("PAID");
                                        return true;
                                    }
                                });
                            }
                            if (persons[3] == 1 || housemates_names[3].equals("--")) {
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_housemate4)).setVisibility(View.GONE);
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_amount4)).setVisibility(View.GONE);
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_button4text)).setVisibility(View.GONE);
                                ((ImageButton) pastDues_view.findViewById(R.id.pastdues_button4)).setVisibility(View.GONE);
                            } else {
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_amount4)).setText(housemate_price);
                                ((ImageButton) pastDues_view.findViewById(R.id.pastdues_button4)).setOnLongClickListener(new View.OnLongClickListener() {
                                    @Override
                                    public boolean onLongClick(View v) {
                                        mDatabaseHelper.addData_toHistory_typemon(period, 99, 4, price);
                                        ((ImageButton) pastDues_view.findViewById(R.id.pastdues_button4)).setImageResource(R.drawable.ic_pastdues_paidbutton);
                                        ((TextView) pastDues_view.findViewById(R.id.pastdues_button4text)).setTextColor(Color.WHITE);
                                        ((TextView) pastDues_view.findViewById(R.id.pastdues_button4text)).setText("PAID");
                                        return true;
                                    }
                                });
                            }
                            if (persons[4] == 1 || housemates_names[4].equals("--")) {
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_housemate5)).setVisibility(View.GONE);
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_amount5)).setVisibility(View.GONE);
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_button5text)).setVisibility(View.GONE);
                                ((ImageButton) pastDues_view.findViewById(R.id.pastdues_button5)).setVisibility(View.GONE);
                            } else {
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_amount5)).setText(housemate_price);
                                ((ImageButton) pastDues_view.findViewById(R.id.pastdues_button5)).setOnLongClickListener(new View.OnLongClickListener() {
                                    @Override
                                    public boolean onLongClick(View v) {
                                        mDatabaseHelper.addData_toHistory_typemon(period, 99, 5, price);
                                        ((ImageButton) pastDues_view.findViewById(R.id.pastdues_button5)).setImageResource(R.drawable.ic_pastdues_paidbutton);
                                        ((TextView) pastDues_view.findViewById(R.id.pastdues_button5text)).setTextColor(Color.WHITE);
                                        ((TextView) pastDues_view.findViewById(R.id.pastdues_button5text)).setText("PAID");
                                        return true;
                                    }
                                });
                            }

                            ((TextView) pastDues_view.findViewById(R.id.pastdues_housemate1)).setText(housemates_names[0]);
                            ((TextView) pastDues_view.findViewById(R.id.pastdues_housemate2)).setText(housemates_names[1]);
                            ((TextView) pastDues_view.findViewById(R.id.pastdues_housemate3)).setText(housemates_names[2]);
                            ((TextView) pastDues_view.findViewById(R.id.pastdues_housemate4)).setText(housemates_names[3]);
                            ((TextView) pastDues_view.findViewById(R.id.pastdues_housemate5)).setText(housemates_names[4]);

                            ((TextView) pastDues_view.findViewById(R.id.pastdues_amount1)).setText(mDatabaseHelper.getPriceFromDate_fromHistory(period));
                            ((TextView) pastDues_view.findViewById(R.id.pastdues_amount2)).setText(mDatabaseHelper.getPriceFromDate_fromHistory(period));
                            ((TextView) pastDues_view.findViewById(R.id.pastdues_amount3)).setText(mDatabaseHelper.getPriceFromDate_fromHistory(period));
                            ((TextView) pastDues_view.findViewById(R.id.pastdues_amount4)).setText(mDatabaseHelper.getPriceFromDate_fromHistory(period));
                            ((TextView) pastDues_view.findViewById(R.id.pastdues_amount5)).setText(mDatabaseHelper.getPriceFromDate_fromHistory(period));

                            pastduesdialog.show();
                        }
                    });
                }
                else if (pastdues_exist) {
                    duesButton.setOnClickListener(new ArgsOnClickListener(this, history_price, type_period, format_period, personsPaidStats) {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder pastDues_builder = new AlertDialog.Builder(Electricity.this);
                            final View pastDues_view = getLayoutInflater().inflate(R.layout.dialog_pastdues, null);
                            int num_housemates = 0;
                            final String housemate_price;
                            pastDues_builder.setView(pastDues_view);
                            pastDues_builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                @Override
                                public void onCancel(DialogInterface dialog) {
                                    loadHistory();
                                }
                            });
                            pastduesdialog = pastDues_builder.create();
                            pastduesdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            pastduesdialog.setCanceledOnTouchOutside(true);

                            ((TextView) pastDues_view.findViewById(R.id.pastdues_date)).setText(fperiod);

                            for (int kk = 0; kk < 5; kk++) {
                                if (persons[kk] == 1)
                                    num_housemates++;
                            }
                            housemate_price = String.valueOf(Float.parseFloat(history_price) / num_housemates);

                            if (persons[0] == 1 || housemates_names[0].equals("--")) {
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_housemate1)).setVisibility(View.GONE);
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_amount1)).setVisibility(View.GONE);
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_button1text)).setVisibility(View.GONE);
                                ((ImageButton) pastDues_view.findViewById(R.id.pastdues_button1)).setVisibility(View.GONE);
                            } else {
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_amount1)).setText(housemate_price);
                                ((ImageButton) pastDues_view.findViewById(R.id.pastdues_button1)).setOnLongClickListener(new View.OnLongClickListener() {
                                    @Override
                                    public boolean onLongClick(View v) {
                                        mDatabaseHelper.addData_toHistory_typemon(period, 99, 1, price);
                                        ((ImageButton) pastDues_view.findViewById(R.id.pastdues_button1)).setImageResource(R.drawable.ic_pastdues_paidbutton);
                                        ((TextView) pastDues_view.findViewById(R.id.pastdues_button1text)).setTextColor(Color.WHITE);
                                        ((TextView) pastDues_view.findViewById(R.id.pastdues_button1text)).setText("PAID");
                                        return true;
                                    }
                                });
                            }
                            if (persons[1] == 1 || housemates_names[1].equals("--")) {
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_housemate2)).setVisibility(View.GONE);
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_amount2)).setVisibility(View.GONE);
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_button2text)).setVisibility(View.GONE);
                                ((ImageButton) pastDues_view.findViewById(R.id.pastdues_button2)).setVisibility(View.GONE);
                            } else {
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_amount2)).setText(housemate_price);
                                ((ImageButton) pastDues_view.findViewById(R.id.pastdues_button2)).setOnLongClickListener(new View.OnLongClickListener() {
                                    @Override
                                    public boolean onLongClick(View v) {
                                        mDatabaseHelper.addData_toHistory_typemon(period, 99, 2, price);
                                        ((ImageButton) pastDues_view.findViewById(R.id.pastdues_button2)).setImageResource(R.drawable.ic_pastdues_paidbutton);
                                        ((TextView) pastDues_view.findViewById(R.id.pastdues_button2text)).setTextColor(Color.WHITE);
                                        ((TextView) pastDues_view.findViewById(R.id.pastdues_button2text)).setText("PAID");
                                        return true;
                                    }
                                });
                            }
                            if (persons[2] == 1 || housemates_names[2].equals("--")) {
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_housemate3)).setVisibility(View.GONE);
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_amount3)).setVisibility(View.GONE);
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_button3text)).setVisibility(View.GONE);
                                ((ImageButton) pastDues_view.findViewById(R.id.pastdues_button3)).setVisibility(View.GONE);
                            } else {
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_amount3)).setText(housemate_price);
                                ((ImageButton) pastDues_view.findViewById(R.id.pastdues_button3)).setOnLongClickListener(new View.OnLongClickListener() {
                                    @Override
                                    public boolean onLongClick(View v) {
                                        mDatabaseHelper.addData_toHistory_typemon(period, 99, 3, price);
                                        ((ImageButton) pastDues_view.findViewById(R.id.pastdues_button3)).setImageResource(R.drawable.ic_pastdues_paidbutton);
                                        ((TextView) pastDues_view.findViewById(R.id.pastdues_button3text)).setTextColor(Color.WHITE);
                                        ((TextView) pastDues_view.findViewById(R.id.pastdues_button3text)).setText("PAID");
                                        return true;
                                    }
                                });
                            }
                            if (persons[3] == 1 || housemates_names[3].equals("--")) {
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_housemate4)).setVisibility(View.GONE);
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_amount4)).setVisibility(View.GONE);
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_button4text)).setVisibility(View.GONE);
                                ((ImageButton) pastDues_view.findViewById(R.id.pastdues_button4)).setVisibility(View.GONE);
                            } else {
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_amount4)).setText(housemate_price);
                                ((ImageButton) pastDues_view.findViewById(R.id.pastdues_button4)).setOnLongClickListener(new View.OnLongClickListener() {
                                    @Override
                                    public boolean onLongClick(View v) {
                                        mDatabaseHelper.addData_toHistory_typemon(period, 99, 4, price);
                                        ((ImageButton) pastDues_view.findViewById(R.id.pastdues_button4)).setImageResource(R.drawable.ic_pastdues_paidbutton);
                                        ((TextView) pastDues_view.findViewById(R.id.pastdues_button4text)).setTextColor(Color.WHITE);
                                        ((TextView) pastDues_view.findViewById(R.id.pastdues_button4text)).setText("PAID");
                                        return true;
                                    }
                                });
                            }
                            if (persons[4] == 1 || housemates_names[4].equals("--")) {
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_housemate5)).setVisibility(View.GONE);
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_amount5)).setVisibility(View.GONE);
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_button5text)).setVisibility(View.GONE);
                                ((ImageButton) pastDues_view.findViewById(R.id.pastdues_button5)).setVisibility(View.GONE);
                            } else {
                                ((TextView) pastDues_view.findViewById(R.id.pastdues_amount5)).setText(housemate_price);
                                ((ImageButton) pastDues_view.findViewById(R.id.pastdues_button5)).setOnLongClickListener(new View.OnLongClickListener() {
                                    @Override
                                    public boolean onLongClick(View v) {
                                        mDatabaseHelper.addData_toHistory_typemon(period, 99, 5, price);
                                        ((ImageButton) pastDues_view.findViewById(R.id.pastdues_button5)).setImageResource(R.drawable.ic_pastdues_paidbutton);
                                        ((TextView) pastDues_view.findViewById(R.id.pastdues_button5text)).setTextColor(Color.WHITE);
                                        ((TextView) pastDues_view.findViewById(R.id.pastdues_button5text)).setText("PAID");
                                        return true;
                                    }
                                });
                            }

                            ((TextView) pastDues_view.findViewById(R.id.pastdues_housemate1)).setText(housemates_names[0]);
                            ((TextView) pastDues_view.findViewById(R.id.pastdues_housemate2)).setText(housemates_names[1]);
                            ((TextView) pastDues_view.findViewById(R.id.pastdues_housemate3)).setText(housemates_names[2]);
                            ((TextView) pastDues_view.findViewById(R.id.pastdues_housemate4)).setText(housemates_names[3]);
                            ((TextView) pastDues_view.findViewById(R.id.pastdues_housemate5)).setText(housemates_names[4]);

                            ((TextView) pastDues_view.findViewById(R.id.pastdues_amount1)).setText(mDatabaseHelper.getPriceFromDate_fromHistory(period));
                            ((TextView) pastDues_view.findViewById(R.id.pastdues_amount2)).setText(mDatabaseHelper.getPriceFromDate_fromHistory(period));
                            ((TextView) pastDues_view.findViewById(R.id.pastdues_amount3)).setText(mDatabaseHelper.getPriceFromDate_fromHistory(period));
                            ((TextView) pastDues_view.findViewById(R.id.pastdues_amount4)).setText(mDatabaseHelper.getPriceFromDate_fromHistory(period));
                            ((TextView) pastDues_view.findViewById(R.id.pastdues_amount5)).setText(mDatabaseHelper.getPriceFromDate_fromHistory(period));

                            pastduesdialog.show();
                        }
                    });
                }

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) dipToPix(100f), ViewGroup.LayoutParams.MATCH_PARENT);
                textDate.setLayoutParams(params);

                LinearLayout.LayoutParams params_t = new LinearLayout.LayoutParams((int) dipToPix(125f), ViewGroup.LayoutParams.MATCH_PARENT);
                textPrice.setLayoutParams(params_t);

                LinearLayout.LayoutParams params_u = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                duesButton.setLayoutParams(params_u);

                LinearLayout.LayoutParams params_rl = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, (int) dipToPix(30f));
                params_rl.setMargins(30,30,30,30);
                params_rl.setLayoutDirection(LinearLayout.HORIZONTAL);
                rl.setLayoutParams(params_rl);

                rl.addView(textDate);
                rl.addView(textPrice);
                rl.addView(duesButton);
                ll.addView(rl);
            }
        }
    }

    public void show_pricePicker(View view) {
        AlertDialog.Builder pricePicker_Builder = new AlertDialog.Builder(Electricity.this);
        View pricePicker_view = getLayoutInflater().inflate(R.layout.dialog_pricepicker, null);
        pricePicker_Builder.setView(pricePicker_view);
        pricepickerdialog = pricePicker_Builder.create();

        et_pricebox = pricePicker_view.findViewById(R.id.dialog_setPricePicker);
        et_pricebox.addTextChangedListener(new NumberTextWatcher(et_pricebox));
        et_pricebox.setText("0.00");

        pricepickerdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pricepickerdialog.show();

        // Auto show keyboard
        et_pricebox.requestFocus();
        et_pricebox.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager keyboard = (InputMethodManager)
                        getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                keyboard.showSoftInput(et_pricebox, 0);
            }
        },200);
    }

    public void setPricePicker(View view) {
        ((TextView) findViewById(R.id.electricity_priceboxtext)).setText("$" + et_pricebox.getText());
        price = Float.parseFloat(et_pricebox.getText().toString());
        pricepickerdialog.dismiss();
    }

    public void dismiss_pastDuesDialog(View view) {
        pastduesdialog.dismiss();
        loadHistory();
    }

    private float dipToPix(float dip) {
        Resources r = getResources();
        float px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                r.getDisplayMetrics()
        );
        return px;
    }
}
