package willhtun.homepal;

import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
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
    AlertDialog mHistoryChangerDialog;

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

        ((TextView) findViewById(R.id.rent_dueDate)).setText("Due: " + duemonth + "/" + duedate);
        ((TextView) findViewById(R.id.rent_priceboxtext)).setText ("$" + mDatabaseHelper.getMostRecentPrice_fromHistory(type));

        loadHistory();
        loadHousemates();
        loadButtons();
    }

    public void rent_pay() {
        mDatabaseHelper.addData_toHistory(type, "11_2018", 25, 0, price);
        mDatabaseHelper.addData_toHistory(type,"10_2018", 2, 0, price);
        mDatabaseHelper.addData_toHistory(type,"8_2018", 22, 0, price);
        mDatabaseHelper.addData_toHistory(type, "1_2017", 16, 0, price);
        mDatabaseHelper.addData_toHistory(type,"9_2018", 22, 0, price);
       // mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), mCalendarHelper.getTodayDate(), 0);
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

    public void change_history() {
        AlertDialog.Builder historyChanger_Builder = new AlertDialog.Builder(Rent.this);
        View reminder_view = getLayoutInflater().inflate(R.layout.dialog_historychanger, null);
        historyChanger_Builder.setView(reminder_view);
        mHistoryChangerDialog = historyChanger_Builder.create();

        ((Button) findViewById(R.id.history_person1_btn)).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // person 1 pay on history date
                return true;
            }
        });
        ((Button) findViewById(R.id.history_person1_btn)).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // person 2 pay on history date
                return true;
            }
        });

        ((Button) findViewById(R.id.history_person1_btn)).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // person 3 pay on history date
                return true;
            }
        });

        ((Button) findViewById(R.id.history_person1_btn)).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // person 4 pay on history date
                return true;
            }
        });

        ((Button) findViewById(R.id.history_person1_btn)).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // person 5 pay on history date
                return true;
            }
        });

        mHistoryChangerDialog.show();
    }

    public void loadHistory() {
        LinearLayout ll = findViewById(R.id.rent_history);
        LinearLayout ll_u = findViewById(R.id.rent_history_unpaiddues);

        for (int i = 0; i < 20; i++) {
            TextView b = new TextView(this);
            TextView b_u = new TextView(this);
            String text = mDatabaseHelper.getPaidDate_fromHistory(type)[i];
            if (!text.equals("")) {

                if (text.charAt(0) == 'u') { // Unpaid Dues
                    b_u.setText("Unpaid Dues");
                    b.setText(text.substring(1));
                }
                else {
                    b_u.setText("");
                    b.setText(text);
                }

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(30,30,30,30);
                b.setLayoutParams(params);

                LinearLayout.LayoutParams params_u = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params_u.setMargins(30,30,30,30);
                b_u.setLayoutParams(params);

                b.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                b_u.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);

                ll.addView(b);
                ll_u.addView(b_u);
            }
        }
        /*
        TextView history1 = (TextView) findViewById(R.id.rent_paidDate1);
        history1.setText(mDatabaseHelper.getPaidDate_fromHistory(type, duemonth-3, dueyear)[2]);
        if (history1.getText() == "")
            findViewById(R.id.rent_paidDate1_bullet).setVisibility(View.INVISIBLE);
        else {
            history1.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    return true;
                }
            });
        }
        TextView history2 = (TextView) findViewById(R.id.rent_paidDate2);
        history2.setText(mDatabaseHelper.getPaidDate_fromHistory(type, duemonth-2, dueyear)[1]);
        if (history2.getText() == "")
            findViewById(R.id.rent_paidDate2_bullet).setVisibility(View.INVISIBLE);
        else {
            history1.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    return true;
                }
            });
        }
        TextView history3 = (TextView) findViewById(R.id.rent_paidDate3);
        history3.setText(mDatabaseHelper.getPaidDate_fromHistory(type, duemonth-1, dueyear)[0]);
        if (history3.getText() == "")
            findViewById(R.id.rent_paidDate3_bullet).setVisibility(View.INVISIBLE);
        else {
            history1.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    return true;
                }
            });
        }
        */
    }


    public void show_reminderPicker(View view) {
        AlertDialog.Builder reminderPicker_Builder = new AlertDialog.Builder(Rent.this);
        View reminder_view = getLayoutInflater().inflate(R.layout.dialog_pricepicker, null);
        reminderPicker_Builder.setView(reminder_view);
        pricepickerdialog = reminderPicker_Builder.create();

        et_pricebox = reminder_view.findViewById(R.id.dialog_setPricePicker);

        pricepickerdialog.show();
    }

    public void setPricePicker(View view) {
        ((TextView) findViewById(R.id.rent_priceboxtext)).setText("$" + et_pricebox.getText());
        price = Float.parseFloat(et_pricebox.getText().toString());
        pricepickerdialog.dismiss();
    }
}
