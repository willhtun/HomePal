package willhtun.homepal;

import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.support.v4.content.res.ResourcesCompat;
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

public class Custom1 extends AppCompatActivity {

    String type = "custom1";
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
    View pastDues_view;

    ImageView custom1_p0_btn;
    ImageView custom1_p1_btn;
    ImageView custom1_p2_btn;
    ImageView custom1_p3_btn;
    ImageView custom1_p4_btn;
    ImageView custom1_p5_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom1);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        mDatabaseHelper = new DatabaseHelper(getApplicationContext());
        mCalendarHelper = new CalendarHelper(mDatabaseHelper, getApplicationContext());

        duedate = mDatabaseHelper.getDate_fromDueDate(type); if (duedate < 0) duedate = 1;
        duemonth = mCalendarHelper.getCycleMonth(type);
        dueyear = mCalendarHelper.getCycleYear(type);

        custom1_p0_btn = findViewById(R.id.custom1_payBtn);
        custom1_p1_btn = findViewById(R.id.custom1_person1);
        custom1_p2_btn = findViewById(R.id.custom1_person2);
        custom1_p3_btn = findViewById(R.id.custom1_person3);
        custom1_p4_btn = findViewById(R.id.custom1_person4);
        custom1_p5_btn = findViewById(R.id.custom1_person5);

        // Populate screen with information
        ((TextView) findViewById(R.id.home_title)).setText(preferences.getString("name_preference_bills_custom1", "Custom 1"));
        ((TextView) findViewById(R.id.custom1_dueDate)).setText("Due: " + duemonth + "/" + duedate);
        String temp_price = mDatabaseHelper.getMostRecentPrice_fromHistory(type);
        if (temp_price == "") {
            price = 0;
            ((TextView) findViewById(R.id.custom1_priceboxtext)).setText("$0.00");
        }
        else {
            price = Float.valueOf(temp_price);
            ((TextView) findViewById(R.id.custom1_priceboxtext)).setText("$" + String.format("%.2f", price));
        }

        if (preferences.getBoolean("check_box_preference_bills_" + type, false)) {
            int mon = mCalendarHelper.getCycleMonth(type);
            int yr = mCalendarHelper.getCycleYear(type);
            String monyr = String.valueOf(mon) + "_" + String.valueOf(yr);
            if (!mDatabaseHelper.isEntryExists_fromHistory(type, monyr)) {
                mDatabaseHelper.addData_toHistory(type, monyr, mCalendarHelper.getTodayDate(), 8, 0);
            }
        }

        loadHistory();
        loadHousemates();
        loadButtons();
    }

    public void custom1_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), mCalendarHelper.getTodayDate(), 0, price);
        ((TextView) findViewById(R.id.custom1_payBtn_text)).setText("PAID");
        ((TextView) findViewById(R.id.custom1_payBtn_text)).setTextColor(Color.WHITE);
        custom1_p0_btn.setImageResource(R.drawable.generic_paidbutton);
        loadHistory();
    }

    public void custom1_personX_pay(int personNum, ImageView custom1_pX_button, int custom1_buttontextID) { //R.id.custom1_person1_text
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), personNum, personNum, price);
        custom1_pX_button.setImageResource(R.drawable.generic_personpaidbutton);
        ((TextView) findViewById(custom1_buttontextID)).setTextColor(Color.BLACK);
        loadHistory();
    }

    public void loadHousemates() {
        int text[] = {R.id.custom1_person1_text, R.id.custom1_person2_text, R.id.custom1_person3_text, R.id.custom1_person4_text, R.id.custom1_person5_text};
        for (int i = 1; i < 6; i++) {
            String name = preferences.getString("name_preference_housemates" + i, "--");
            if (name.equals("")) name = "--";
            if (name.length() > 2) name = name.substring(0,2);
            ((TextView) findViewById(text[i-1])).setText(name);
        }
    }

    public void loadButtons() {
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),0)) {
            ((TextView) findViewById(R.id.custom1_payBtn_text)).setText("PAID");
            custom1_p0_btn.setImageResource(R.drawable.generic_paidbutton);
            ((TextView) findViewById(R.id.custom1_payBtn_text)).setTextColor(Color.WHITE);
        }
        else{
            custom1_p0_btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Press and hold to pay", Toast.LENGTH_SHORT).show();
                }
            });
            custom1_p0_btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    custom1_pay();
                    return true;
                }
            });
            ((TextView) findViewById(R.id.custom1_payBtn_text)).setTextColor(Color.parseColor("#AAAAAA"));
        }

        if (preferences.getBoolean("settings_billSharingOn_custom1", false)) {
            final int text[] = {R.id.custom1_person1_text, R.id.custom1_person2_text, R.id.custom1_person3_text, R.id.custom1_person4_text, R.id.custom1_person5_text};
            final ImageView button [] = {custom1_p1_btn, custom1_p2_btn, custom1_p3_btn, custom1_p4_btn, custom1_p5_btn};

            for (int i = 1;i < 6; i++) {
                if (!preferences.getBoolean("housemate" + i + "_On", false)) {
                    button[i-1].setImageResource(R.drawable.generic_personunavailable);
                    ((TextView) findViewById(text[i-1])).setText("");
                }
                else if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type), i)) {
                    button[i-1].setImageResource(R.drawable.generic_personpaidbutton);
                    ((TextView) findViewById(text[i-1])).setTextColor(Color.BLACK);
                }
                else {
                    button[i-1].setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            Toast.makeText(getApplicationContext(), "Press and hold to pay", Toast.LENGTH_SHORT).show();
                        }
                    });
                    final int person_nump_temp = i;
                    button[i-1].setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            custom1_personX_pay(person_nump_temp, button[person_nump_temp-1], text[person_nump_temp-1]);
                            return true;
                        }
                    });
                }
            }
        }
        else {
            findViewById(R.id.custom1_payButtonsContainer).setVisibility(View.GONE);
        }
    }

    public void loadHistory() {
        LinearLayout vertical_ll = findViewById(R.id.custom1_history);
        String[][] text_raw = mDatabaseHelper.getPaidDate_fromHistory(type);

        vertical_ll.removeAllViews();
        preferences.edit().putBoolean("overdue_custom1", false).apply();

        boolean overdue_exist = false;

        for (int i = 0; i < 20; i++) {
            LinearLayout entry_ll = new LinearLayout(this);
            TextView textDate = new TextView(this);
            TextView textPrice = new TextView(this);
            // ImageButton duesButton = new ImageButton(this);

            // Date and Price from DB
            String text = text_raw[0][i];
            final String history_price = text_raw[1][i];

            String type_period;
            String format_period = "";
            String personsPaidStats;
            boolean pastdues_exist = false;

            if (!text.equals("")) {
                int t = 0;
                int u;

                while (text.charAt(t) != '+')
                    t++;
                type_period = text.substring(1, t);

                if (type_period.equals(type + "_" + mCalendarHelper.getCycleMonthYear(type)))
                    continue;

                t++;
                u = t;
                while (text.charAt(t) != '#')
                    t++;
                personsPaidStats = text.substring(u, t);

                final String[] housemates_names = mDatabaseHelper.getHousemates_fromHistory(type_period);

                /*
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
                */

                text = text.substring(t+1);
                textDate.setText(text);
                textDate.setGravity(Gravity.CENTER_VERTICAL);
                textDate.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                textDate.setPadding(50,0,0,0);
                textDate.setTypeface(ResourcesCompat.getFont(getApplicationContext(), R.font.josefinsans_regular));

                textPrice.setText(history_price);
                textPrice.setGravity(Gravity.CENTER_VERTICAL);
                textPrice.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
                textPrice.setPadding(0,0,0,0);
                textPrice.setTypeface(ResourcesCompat.getFont(getApplicationContext(), R.font.josefinsans_regular));

                // duesButton.setBackground(null);
                // duesButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
                // duesButton.setPadding(40,10,50,0);

                int ii = 0;
                while (text.charAt(ii) != ' ')
                    ii++;
                format_period = text.substring(ii + 1, text.length());

                final int [] pd_housemate = {R.id.pastdues_housemate1, R.id.pastdues_housemate2, R.id.pastdues_housemate3, R.id.pastdues_housemate4, R.id.pastdues_housemate5 };
                final int [] pd_amount = {R.id.pastdues_amount1, R.id.pastdues_amount2, R.id.pastdues_amount3, R.id.pastdues_amount4, R.id.pastdues_amount5};
                final int [] pd_buttontext = {R.id.pastdues_button1text, R.id.pastdues_button2text, R.id.pastdues_button3text, R.id.pastdues_button4text, R.id.pastdues_button5text};
                final int [] pd_button = {R.id.pastdues_button1, R.id.pastdues_button2, R.id.pastdues_button3, R.id.pastdues_button4, R.id.pastdues_button5};

                /*
                if (overdue_exist) {
                    preferences.edit().putBoolean("overdue_custom1", true).apply();
                    duesButton.setOnClickListener(new ArgsOnClickListener(this, history_price, type_period, format_period, personsPaidStats) {
                        @Override
                        public void onClick(View v) {
                            final AlertDialog.Builder pastDues_builder = new AlertDialog.Builder(Custom1.this);
                            pastDues_view = getLayoutInflater().inflate(R.layout.dialog_pastdues_overdue, null);
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
                                if (!housemates_names[kk].equals("--"))
                                    num_housemates++;
                            }
                            housemate_price = String.valueOf(Float.parseFloat(historyprice) / num_housemates);

                            for (int housematesID = 1; housematesID < 6; housematesID++) {
                                duesdialog_prepVisibility(housematesID, housemates_names[housematesID-1], housemate_price, period,
                                        pd_housemate[housematesID-1], pd_amount[housematesID-1], pd_buttontext[housematesID-1], pd_button[housematesID-1]);
                            }

                            for (int g = 0; g < 5; g++)
                                ((TextView) pastDues_view.findViewById(pd_housemate[g])).setText(housemates_names[g]);

                            pastduesdialog.show();
                        }
                    });
                }
                else if (pastdues_exist) {
                    duesButton.setOnClickListener(new ArgsOnClickListener(this, history_price, type_period, format_period, personsPaidStats) {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder pastDues_builder = new AlertDialog.Builder(Custom1.this);
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
                                if (!housemates_names[kk].equals("--"))
                                    num_housemates++;
                            }
                            housemate_price = String.valueOf(Float.parseFloat(historyprice) / num_housemates);

                            for (int housematesID = 1; housematesID < 6; housematesID++) {
                                duesdialog_prepVisibility(housematesID, housemates_names[housematesID-1], housemate_price, period,
                                        pd_housemate[housematesID-1], pd_amount[housematesID-1], pd_buttontext[housematesID-1], pd_button[housematesID-1]);
                            }

                            for (int g = 0; g < 5; g++)
                                ((TextView) pastDues_view.findViewById(pd_housemate[g])).setText(housemates_names[g]);

                            pastduesdialog.show();
                        }
                    });
                }
                */

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) dipToPix(100f), ViewGroup.LayoutParams.MATCH_PARENT);
                textDate.setLayoutParams(params);

                LinearLayout.LayoutParams params_t = new LinearLayout.LayoutParams((int) dipToPix(100f), ViewGroup.LayoutParams.MATCH_PARENT);
                textPrice.setLayoutParams(params_t);

                // LinearLayout.LayoutParams params_u = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                // duesButton.setLayoutParams(params_u);

                LinearLayout.LayoutParams params_rl = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, (int) dipToPix(30f));
                params_rl.setMargins(30,30,30,30);
                params_rl.setLayoutDirection(LinearLayout.HORIZONTAL);
                entry_ll.setLayoutParams(params_rl);

                entry_ll.addView(textDate);
                entry_ll.addView(textPrice);
                //entry_ll.addView(duesButton);
                vertical_ll.addView(entry_ll);
            }
        }
    }

    public void show_pricePicker(View view) {
        AlertDialog.Builder pricePicker_Builder = new AlertDialog.Builder(Custom1.this);
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
        ((TextView) findViewById(R.id.custom1_priceboxtext)).setText("$" + et_pricebox.getText());
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

    /*
    private void duesdialog_prepVisibility(final int person_num, String person_name, String housemate_price, String per,
                                           int pastdues_housemateX, int pastdues_amountX, final int pastdues_buttonXtext, final int pastdues_buttonX) {
        if (person_num == 1 || person_name.equals("--")) {
            ((TextView) pastDues_view.findViewById(pastdues_housemateX)).setVisibility(View.GONE);
            ((TextView) pastDues_view.findViewById(pastdues_amountX)).setVisibility(View.GONE);
            ((TextView) pastDues_view.findViewById(pastdues_buttonXtext)).setVisibility(View.GONE);
            ((ImageButton) pastDues_view.findViewById(pastdues_buttonX)).setVisibility(View.GONE);
        } else {
            final String period = per;
            ((TextView) pastDues_view.findViewById(pastdues_amountX)).setText(housemate_price);
            ((ImageButton) pastDues_view.findViewById(pastdues_buttonX)).setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mDatabaseHelper.addData_toHistory_typemon(period, 99, person_num, price);
                    ((ImageButton) pastDues_view.findViewById(pastdues_buttonX)).setImageResource(R.drawable.ic_pastdues_paidbutton);
                    ((TextView) pastDues_view.findViewById(pastdues_buttonXtext)).setTextColor(Color.WHITE);
                    ((TextView) pastDues_view.findViewById(pastdues_buttonXtext)).setText("PAID");
                    return true;
                }
            });
        }
    }
    */
}
