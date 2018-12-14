package willhtun.homepal;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Custom4 extends AppCompatActivity {

    String type = "custom4";
    int duedate = 0;
    int duemonth = 0;
    int dueyear = 0;

    DatabaseHelper mDatabaseHelper;
    CalendarHelper mCalendarHelper;
    AlertDialog mHistoryChangerDialog;

    ImageView custom4_p0_btn;
    ImageView custom4_p1_btn;
    ImageView custom4_p2_btn;
    ImageView custom4_p3_btn;
    ImageView custom4_p4_btn;
    ImageView custom4_p5_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom4);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        mDatabaseHelper = new DatabaseHelper(getApplicationContext());
        mCalendarHelper = new CalendarHelper(mDatabaseHelper, getApplicationContext());

        duedate = mDatabaseHelper.getDate_fromDueDate(type); if (duedate < 0) duedate = 1;
        duemonth = mCalendarHelper.getCycleMonth(type);
        dueyear = mCalendarHelper.getCycleYear(type);

        ((TextView) findViewById(R.id.custom4_title)).setText(preferences.getString("name_preference_bills_custom4", "Custom 4"));
        if (preferences.getString("name_preference_bills_custom4", "Custom 4").equals(""))
            ((TextView)findViewById(R.id.custom4_title)).setText("Custom 4");

        custom4_p0_btn = findViewById(R.id.custom4_payBtn);
        custom4_p1_btn = findViewById(R.id.custom4_person1);
        custom4_p2_btn = findViewById(R.id.custom4_person2);
        custom4_p3_btn = findViewById(R.id.custom4_person3);
        custom4_p4_btn = findViewById(R.id.custom4_person4);
        custom4_p5_btn = findViewById(R.id.custom4_person5);

        ((TextView) findViewById(R.id.custom4_dueDate)).setText("Due: " + duemonth + "/" + duedate);

        loadHistory();

        loadButtons();
    }

    public void custom4_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), mCalendarHelper.getTodayDate(), 0);
        ((TextView) findViewById(R.id.custom4_payBtn_text)).setText("PAID");
        ((TextView) findViewById(R.id.custom5_payBtn_text)).setTextColor(Color.WHITE);
        custom4_p0_btn.setImageResource(R.drawable.generic_paidbutton);
        loadHistory();
    }

    public void custom4_person1_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 111, 1);
        custom4_p1_btn.setImageResource(R.drawable.generic_personpaidbutton);
        loadHistory();
    }

    public void custom4_person2_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 222, 2);
        custom4_p2_btn.setImageResource(R.drawable.generic_personpaidbutton);
        loadHistory();
    }

    public void custom4_person3_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 333, 3);
        custom4_p3_btn.setImageResource(R.drawable.generic_personpaidbutton);
        loadHistory();
    }

    public void custom4_person4_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 444, 4);
        custom4_p4_btn.setImageResource(R.drawable.generic_personpaidbutton);
        loadHistory();
    }

    public void custom4_person5_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 555, 5);
        custom4_p5_btn.setImageResource(R.drawable.generic_personpaidbutton);
        loadHistory();
    }

    public void loadButtons() {
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),0)) {
            ((TextView) findViewById(R.id.custom4_payBtn_text)).setText("PAID");
            custom4_p0_btn.setImageResource(R.drawable.generic_paidbutton);
            ((TextView) findViewById(R.id.custom4_payBtn_text)).setTextColor(Color.WHITE);
        }
        else{
            custom4_p0_btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    custom4_pay();
                }
            });
            ((TextView) findViewById(R.id.custom4_payBtn_text)).setTextColor(Color.parseColor("#5DB699"));
        }
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),1)) {
            custom4_p1_btn.setImageResource(R.drawable.generic_personpaidbutton);
        }
        else {
            custom4_p1_btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    custom4_person1_pay();
                    return true;
                }
            });
        }
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),2)) {
            custom4_p2_btn.setImageResource(R.drawable.generic_personpaidbutton);
        }
        else {
            custom4_p2_btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    custom4_person2_pay();
                    return true;
                }
            });
        }
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),3)) {
            custom4_p3_btn.setImageResource(R.drawable.generic_personpaidbutton);
        }
        else {
            custom4_p3_btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    custom4_person3_pay();
                    return true;
                }
            });
        }
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),4)) {
            custom4_p4_btn.setImageResource(R.drawable.generic_personpaidbutton);
        }
        else {
            custom4_p4_btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    custom4_person4_pay();
                    return true;
                }
            });
        }
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),5)) {
            custom4_p5_btn.setImageResource(R.drawable.generic_personpaidbutton);
        }
        else {
            custom4_p5_btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    custom4_person5_pay();
                    return true;
                }
            });
        }
    }

    public void change_history() {
        AlertDialog.Builder historyChanger_Builder = new AlertDialog.Builder(Custom4.this);
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
        /*
        TextView history1 = (TextView) findViewById(R.id.custom4_paidDate1);
        history1.setText(mDatabaseHelper.getPaidDate_fromHistory(type, duemonth-3, dueyear)[2]);
        if (history1.getText() == "")
            findViewById(R.id.custom4_paidDate1_bullet).setVisibility(View.INVISIBLE);
        else {
            history1.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    return true;
                }
            });
        }
        TextView history2 = (TextView) findViewById(R.id.custom4_paidDate2);
        history2.setText(mDatabaseHelper.getPaidDate_fromHistory(type, duemonth-2, dueyear)[1]);
        if (history2.getText() == "")
            findViewById(R.id.custom4_paidDate2_bullet).setVisibility(View.INVISIBLE);
        else {
            history1.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    return true;
                }
            });
        }
        TextView history3 = (TextView) findViewById(R.id.custom4_paidDate3);
        history3.setText(mDatabaseHelper.getPaidDate_fromHistory(type, duemonth-1, dueyear)[0]);
        if (history3.getText() == "")
            findViewById(R.id.custom4_paidDate3_bullet).setVisibility(View.INVISIBLE);
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
}
