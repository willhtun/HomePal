package willhtun.homepal;

import android.app.ActionBar;
import android.app.Activity;
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

public class Rent extends AppCompatActivity {
    
    String type = "rent";
    int duedate = 0;
    int duemonth = 0;
    int dueyear = 0;
    float price = 0;

    DatabaseHelper mDatabaseHelper;
    CalendarHelper mCalendarHelper;
    AlertDialog mPastDues;

    ImageView rent_p0_btn;
    ImageView rent_p1_btn;
    ImageView rent_p2_btn;
    ImageView rent_p3_btn;
    ImageView rent_p4_btn;
    ImageView rent_p5_btn;

    EditText et_pricebox;
    AlertDialog pricepickerdialog;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        mDatabaseHelper = new DatabaseHelper(getApplicationContext());
        mCalendarHelper = new CalendarHelper(mDatabaseHelper, getApplicationContext());

        duedate = mDatabaseHelper.getDate_fromDueDate(type); if (duedate < 0) duedate = 1;
        duemonth = mCalendarHelper.getCycleMonth(type);
        dueyear = mCalendarHelper.getCycleYear(type);

        rent_p0_btn = findViewById(R.id.rent_payBtn);
        rent_p1_btn = findViewById(R.id.rent_person1);
        rent_p2_btn = findViewById(R.id.rent_person2);
        rent_p3_btn = findViewById(R.id.rent_person3);
        rent_p4_btn = findViewById(R.id.rent_person4);
        rent_p5_btn = findViewById(R.id.rent_person5);
        mDatabaseHelper.addData_toHistory(type,"4_2019", 10, 0, price);

        ((TextView) findViewById(R.id.rent_dueDate)).setText("Due: " + duemonth + "/" + duedate);
        ((TextView) findViewById(R.id.rent_priceboxtext)).setText ("$" + mDatabaseHelper.getMostRecentPrice_fromHistory(type));

        loadHistory();
        loadHousemates();
        loadButtons();
    }

    public void rent_pay() {
        mDatabaseHelper.addData_toHistory(type, Integer.toString(5) + "_" + 2019, mCalendarHelper.getTodayDate(), 0, price);
        //mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), mCalendarHelper.getTodayDate(), 0, price);
        ((TextView) findViewById(R.id.rent_payBtn_text)).setText("PAID");
        ((TextView) findViewById(R.id.rent_payBtn_text)).setTextColor(Color.WHITE);
        rent_p0_btn.setImageResource(R.drawable.generic_paidbutton);
        loadHistory();
    }

    public void rent_person1_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 111, 1, price);
        rent_p1_btn.setImageResource(R.drawable.generic_personpaidbutton);
        ((TextView) findViewById(R.id.rent_person1_text)).setTextColor(Color.WHITE);
        loadHistory();
    }

    public void rent_person2_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 222, 2, price);
        rent_p2_btn.setImageResource(R.drawable.generic_personpaidbutton);
        ((TextView) findViewById(R.id.rent_person2_text)).setTextColor(Color.WHITE);
        loadHistory();
    }

    public void rent_person3_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 333, 3, price);
        rent_p3_btn.setImageResource(R.drawable.generic_personpaidbutton);
        ((TextView) findViewById(R.id.rent_person3_text)).setTextColor(Color.WHITE);
        loadHistory();
    }

    public void rent_person4_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 444, 4, price);
        rent_p4_btn.setImageResource(R.drawable.generic_personpaidbutton);
        ((TextView) findViewById(R.id.rent_person4_text)).setTextColor(Color.WHITE);
        loadHistory();
    }

    public void rent_person5_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 555, 5, price);
        rent_p5_btn.setImageResource(R.drawable.generic_personpaidbutton);
        ((TextView) findViewById(R.id.rent_person5_text)).setTextColor(Color.WHITE);
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

        ((TextView) findViewById(R.id.rent_person1_text)).setText(name1);
        ((TextView) findViewById(R.id.rent_person2_text)).setText(name2);
        ((TextView) findViewById(R.id.rent_person3_text)).setText(name3);
        ((TextView) findViewById(R.id.rent_person4_text)).setText(name4);
        ((TextView) findViewById(R.id.rent_person5_text)).setText(name5);
    }

    public void loadButtons() {
        rent_p0_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Press and hold to pay", Toast.LENGTH_SHORT).show();
            }
        });
        rent_p0_btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                rent_pay();
                return true;
            }
        });
        ((TextView) findViewById(R.id.rent_payBtn_text)).setTextColor(Color.parseColor("#5DB699"));

        /*
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),0)) {
            ((TextView) findViewById(R.id.rent_payBtn_text)).setText("PAID");
            rent_p0_btn.setImageResource(R.drawable.generic_paidbutton);
            ((TextView) findViewById(R.id.rent_payBtn_text)).setTextColor(Color.WHITE);
        }
        else{
            rent_p0_btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Press and hold to pay", Toast.LENGTH_SHORT).show();
                }
            });
            rent_p0_btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    rent_pay();
                    return true;
                }
            });
            ((TextView) findViewById(R.id.rent_payBtn_text)).setTextColor(Color.parseColor("#5DB699"));
        }
        */

        if (preferences.getBoolean("settings_billSharingOn", false)) {
            if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type), 1)) {
                rent_p1_btn.setImageResource(R.drawable.generic_personpaidbutton);
                ((TextView) findViewById(R.id.rent_person1_text)).setTextColor(Color.WHITE);
            } else {
                rent_p1_btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Press and hold to pay", Toast.LENGTH_SHORT).show();
                    }
                });
                rent_p1_btn.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        rent_person1_pay();
                        return true;
                    }
                });
            }
            if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type), 2)) {
                rent_p2_btn.setImageResource(R.drawable.generic_personpaidbutton);
                ((TextView) findViewById(R.id.rent_person2_text)).setTextColor(Color.WHITE);
            } else {
                rent_p2_btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Press and hold to pay", Toast.LENGTH_SHORT).show();
                    }
                });
                rent_p2_btn.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        rent_person2_pay();
                        return true;
                    }
                });
            }
            if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type), 3)) {
                rent_p3_btn.setImageResource(R.drawable.generic_personpaidbutton);
                ((TextView) findViewById(R.id.rent_person3_text)).setTextColor(Color.WHITE);
            } else {
                rent_p3_btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Press and hold to pay", Toast.LENGTH_SHORT).show();
                    }
                });
                rent_p3_btn.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        rent_person3_pay();
                        return true;
                    }
                });
            }
            if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type), 4)) {
                rent_p4_btn.setImageResource(R.drawable.generic_personpaidbutton);
                ((TextView) findViewById(R.id.rent_person4_text)).setTextColor(Color.WHITE);
            } else {
                rent_p4_btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Press and hold to pay", Toast.LENGTH_SHORT).show();
                    }
                });
                rent_p4_btn.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        rent_person4_pay();
                        return true;
                    }
                });
            }
            if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type), 5)) {
                rent_p5_btn.setImageResource(R.drawable.generic_personpaidbutton);
                ((TextView) findViewById(R.id.rent_person5_text)).setTextColor(Color.WHITE);
            } else {
                rent_p5_btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Press and hold to pay", Toast.LENGTH_SHORT).show();
                    }
                });
                rent_p5_btn.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        rent_person5_pay();
                        return true;
                    }
                });
            }
        }
        else {
            rent_p1_btn.setVisibility(View.GONE);
            rent_p2_btn.setVisibility(View.GONE);
            rent_p3_btn.setVisibility(View.GONE);
            rent_p4_btn.setVisibility(View.GONE);
            rent_p5_btn.setVisibility(View.GONE);
        }
    }

    public void loadHistory() {
        LinearLayout ll = findViewById(R.id.rent_history);

        for (int i = 0; i < 20; i++) {
            LinearLayout rl = new LinearLayout(this);
            TextView textEntry = new TextView(this);
            ImageButton duesButton = new ImageButton(this);
            String text = mDatabaseHelper.getPaidDate_fromHistory(type)[i];
            String period;
            String personsPaidStats;
            
            if (!text.equals("")) {
                int t = 0;
                int u = 0;
                if (text.charAt(0) == 'u') // Unpaid Dues
                    duesButton.setImageResource(R.drawable.icon_electricity);
                else
                    duesButton.setVisibility(View.INVISIBLE);
                while (text.charAt(t) != '+')
                    t++;
                period = text.substring(1, t);
                t++;
                u = t;
                while (text.charAt(t) != '#')
                    t++;
                personsPaidStats = text.substring(u, t);
                text = text.substring(t+1);
                textEntry.setText(text);
                textEntry.setGravity(Gravity.CENTER_VERTICAL);
                textEntry.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                
                duesButton.setBackground(null);
                duesButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
                duesButton.setPadding(0,0,0,0);

                duesButton.setOnClickListener(new ArgsOnClickListener(this, period, personsPaidStats) {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder pastDues_builder = new AlertDialog.Builder(Rent.this);
                        View pastDues_view = getLayoutInflater().inflate(R.layout.dialog_pastdues, null);
                        pastDues_builder.setView(pastDues_view);
                        mPastDues = pastDues_builder.create();
                        mPastDues.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                        ((TextView) pastDues_view.findViewById(R.id.pastdues_date)).setText(period);

                        if (persons[0] == 0)
                            ((Button) pastDues_view.findViewById(R.id.pastdues_button1)).setVisibility(View.INVISIBLE);
                        if (persons[1] == 0)
                            ((Button) pastDues_view.findViewById(R.id.pastdues_button2)).setVisibility(View.INVISIBLE);
                        if (persons[2] == 0)
                            ((Button) pastDues_view.findViewById(R.id.pastdues_button3)).setVisibility(View.INVISIBLE);
                        if (persons[3] == 0)
                            ((Button) pastDues_view.findViewById(R.id.pastdues_button4)).setVisibility(View.INVISIBLE);
                        if (persons[4] == 0)
                            ((Button) pastDues_view.findViewById(R.id.pastdues_button5)).setVisibility(View.INVISIBLE);

                        String[] housemates_names = mDatabaseHelper.getHousemates_fromHistory(period);
                        Log.d("NAMESS_2", housemates_names[0] + " " + housemates_names[1] + " " + housemates_names[2] + " " + housemates_names[3] + " " + housemates_names[4]);

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

                        mPastDues.show();
                    }
                });


                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                textEntry.setLayoutParams(params);

                LinearLayout.LayoutParams params_u = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                duesButton.setLayoutParams(params_u);

                LinearLayout.LayoutParams params_rl = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, (int) dipToPix(30f));
                params_rl.setMargins(30,30,30,30);
                params_rl.setLayoutDirection(LinearLayout.HORIZONTAL);
                rl.setLayoutParams(params_rl);
                
                rl.addView(textEntry);
                rl.addView(duesButton);
                ll.addView(rl);
            }
        }
    }

    public void show_pricePicker(View view) {
        AlertDialog.Builder pricePicker_Builder = new AlertDialog.Builder(Rent.this);
        View pricePicker_view = getLayoutInflater().inflate(R.layout.dialog_pricepicker, null);
        pricePicker_Builder.setView(pricePicker_view);
        pricepickerdialog = pricePicker_Builder.create();

        et_pricebox = pricePicker_view.findViewById(R.id.dialog_setPricePicker);
        pricepickerdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pricepickerdialog.show();
    }

    public void setPricePicker(View view) {
        ((TextView) findViewById(R.id.rent_priceboxtext)).setText("$" + et_pricebox.getText());
        price = Float.parseFloat(et_pricebox.getText().toString());
        pricepickerdialog.dismiss();
    }

    public void dismiss_pastDuesDialog(View view) {
        mPastDues.dismiss();
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
