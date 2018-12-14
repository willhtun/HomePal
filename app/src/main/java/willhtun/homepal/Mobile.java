package willhtun.homepal;

import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Mobile extends AppCompatActivity {

    String type = "mobile";
    int duedate = 0;
    int duemonth = 0;
    int dueyear = 0;

    DatabaseHelper mDatabaseHelper;
    CalendarHelper mCalendarHelper;
    AlertDialog mHistoryChangerDialog;

    ImageView mobile_p0_btn;
    ImageView mobile_p1_btn;
    ImageView mobile_p2_btn;
    ImageView mobile_p3_btn;
    ImageView mobile_p4_btn;
    ImageView mobile_p5_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile);

        mDatabaseHelper = new DatabaseHelper(getApplicationContext());
        mCalendarHelper = new CalendarHelper(mDatabaseHelper, getApplicationContext());

        duedate = mDatabaseHelper.getDate_fromDueDate(type); if (duedate < 0) duedate = 1;
        duemonth = mCalendarHelper.getCycleMonth(type);
        dueyear = mCalendarHelper.getCycleYear(type);

        mobile_p0_btn = findViewById(R.id.mobile_payBtn);
        mobile_p1_btn = findViewById(R.id.mobile_person1);
        mobile_p2_btn = findViewById(R.id.mobile_person2);
        mobile_p3_btn = findViewById(R.id.mobile_person3);
        mobile_p4_btn = findViewById(R.id.mobile_person4);
        mobile_p5_btn = findViewById(R.id.mobile_person5);

        ((TextView) findViewById(R.id.mobile_dueDate)).setText("Due: " + duemonth + "/" + duedate);

        loadHistory();

        loadButtons();
    }

    public void mobile_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), mCalendarHelper.getTodayDate(), 0);
        ((TextView) findViewById(R.id.mobile_payBtn_text)).setText("PAID");
        ((TextView) findViewById(R.id.mobile_payBtn_text)).setTextColor(Color.WHITE);
        mobile_p0_btn.setImageResource(R.drawable.generic_paidbutton);
        loadHistory();
    }

    public void mobile_person1_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 111, 1);
        mobile_p1_btn.setImageResource(R.drawable.generic_personpaidbutton);
        loadHistory();
    }

    public void mobile_person2_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 222, 2);
        mobile_p2_btn.setImageResource(R.drawable.generic_personpaidbutton);
        loadHistory();
    }

    public void mobile_person3_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 333, 3);
        mobile_p3_btn.setImageResource(R.drawable.generic_personpaidbutton);
        loadHistory();
    }

    public void mobile_person4_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 444, 4);
        mobile_p4_btn.setImageResource(R.drawable.generic_personpaidbutton);
        loadHistory();
    }

    public void mobile_person5_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 555, 5);
        mobile_p5_btn.setImageResource(R.drawable.generic_personpaidbutton);
        loadHistory();
    }

    public void loadButtons() {
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),0)) {
            ((TextView) findViewById(R.id.mobile_payBtn_text)).setText("PAID");
            mobile_p0_btn.setImageResource(R.drawable.generic_paidbutton);
            ((TextView) findViewById(R.id.mobile_payBtn_text)).setTextColor(Color.WHITE);
        }
        else{
            mobile_p0_btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mobile_pay();
                }
            });
            ((TextView) findViewById(R.id.mobile_payBtn_text)).setTextColor(Color.parseColor("#5DB699"));
        }
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),1)) {
            mobile_p1_btn.setImageResource(R.drawable.generic_personpaidbutton);
        }
        else {
            mobile_p1_btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mobile_person1_pay();
                    return true;
                }
            });
        }
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),2)) {
            mobile_p2_btn.setImageResource(R.drawable.generic_personpaidbutton);
        }
        else {
            mobile_p2_btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mobile_person2_pay();
                    return true;
                }
            });
        }
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),3)) {
            mobile_p3_btn.setImageResource(R.drawable.generic_personpaidbutton);
        }
        else {
            mobile_p3_btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mobile_person3_pay();
                    return true;
                }
            });
        }
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),4)) {
            mobile_p4_btn.setImageResource(R.drawable.generic_personpaidbutton);
        }
        else {
            mobile_p4_btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mobile_person4_pay();
                    return true;
                }
            });
        }
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),5)) {
            mobile_p5_btn.setImageResource(R.drawable.generic_personpaidbutton);
        }
        else {
            mobile_p5_btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mobile_person5_pay();
                    return true;
                }
            });
        }
    }

    public void change_history() {
        AlertDialog.Builder historyChanger_Builder = new AlertDialog.Builder(Mobile.this);
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
        TextView history1 = (TextView) findViewById(R.id.mobile_paidDate1);
        history1.setText(mDatabaseHelper.getPaidDate_fromHistory(type, duemonth-3, dueyear)[2]);
        if (history1.getText() == "")
            findViewById(R.id.mobile_paidDate1_bullet).setVisibility(View.INVISIBLE);
        else {
            history1.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    return true;
                }
            });
        }
        TextView history2 = (TextView) findViewById(R.id.mobile_paidDate2);
        history2.setText(mDatabaseHelper.getPaidDate_fromHistory(type, duemonth-2, dueyear)[1]);
        if (history2.getText() == "")
            findViewById(R.id.mobile_paidDate2_bullet).setVisibility(View.INVISIBLE);
        else {
            history1.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    return true;
                }
            });
        }
        TextView history3 = (TextView) findViewById(R.id.mobile_paidDate3);
        history3.setText(mDatabaseHelper.getPaidDate_fromHistory(type, duemonth-1, dueyear)[0]);
        if (history3.getText() == "")
            findViewById(R.id.mobile_paidDate3_bullet).setVisibility(View.INVISIBLE);
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
