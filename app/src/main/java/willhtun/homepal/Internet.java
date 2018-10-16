package willhtun.homepal;

import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Internet extends AppCompatActivity {

    String type = "internet";
    int duedate = 0;
    int duemonth = 0;
    int dueyear = 0;

    DatabaseHelper mDatabaseHelper;
    CalendarHelper mCalendarHelper;
    AlertDialog mHistoryChangerDialog;

    ImageView internet_p0_btn;
    ImageView internet_p1_btn;
    ImageView internet_p2_btn;
    ImageView internet_p3_btn;
    ImageView internet_p4_btn;
    ImageView internet_p5_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);

        mDatabaseHelper = new DatabaseHelper(getApplicationContext());
        mCalendarHelper = new CalendarHelper(mDatabaseHelper);

        duedate = mDatabaseHelper.getDate_fromDueDate(type); if (duedate < 0) duedate = 1;
        duemonth = mCalendarHelper.getCycleMonth(type);
        dueyear = mCalendarHelper.getCycleYear(type);

        internet_p0_btn = (ImageView) findViewById(R.id.internet_payBtn);
        internet_p1_btn = (ImageView) findViewById(R.id.internet_person1);
        internet_p2_btn = (ImageView) findViewById(R.id.internet_person2);
        internet_p3_btn = (ImageView) findViewById(R.id.internet_person3);
        internet_p4_btn = (ImageView) findViewById(R.id.internet_person4);
        internet_p5_btn = (ImageView) findViewById(R.id.internet_person5);

        ((TextView) findViewById(R.id.internet_dueDate)).setText("Due: " + duemonth + "/" + duedate);
        ((TextView) findViewById(R.id.internet_cost)).setText("$ " + String.valueOf(mDatabaseHelper.getCost_fromDueDate(type)));

        loadHistory();

        loadButtons();
    }

    public void internet_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), mCalendarHelper.getTodayDate(), 0);
        ((TextView) findViewById(R.id.internet_payBtn_text)).setTextColor(Color.WHITE);
        ((TextView) findViewById(R.id.internet_payBtn_text)).setText("PAID");
        internet_p0_btn.setImageResource(R.drawable.internet_paybutton);
        loadHistory();
    }

    public void internet_person1_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 111, 1);
        ((TextView) findViewById(R.id.internet_person1_text)).setTextColor(Color.WHITE);
        internet_p1_btn.setImageResource(R.drawable.internet_personpaidbutton);
        loadHistory();
    }

    public void internet_person2_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 222, 2);
        ((TextView) findViewById(R.id.internet_person2_text)).setTextColor(Color.WHITE);
        internet_p2_btn.setImageResource(R.drawable.internet_personpaidbutton);
        loadHistory();
    }

    public void internet_person3_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 333, 3);
        ((TextView) findViewById(R.id.internet_person3_text)).setTextColor(Color.WHITE);
        internet_p3_btn.setImageResource(R.drawable.internet_personpaidbutton);
        loadHistory();
    }

    public void internet_person4_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 444, 4);
        ((TextView) findViewById(R.id.internet_person4_text)).setTextColor(Color.WHITE);
        internet_p4_btn.setImageResource(R.drawable.internet_personpaidbutton);
        loadHistory();
    }

    public void internet_person5_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 555, 5);
        ((TextView) findViewById(R.id.internet_person5_text)).setTextColor(Color.WHITE);
        internet_p5_btn.setImageResource(R.drawable.internet_personpaidbutton);
        loadHistory();
    }

    public void loadButtons() {
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),0)) {
            ((TextView) findViewById(R.id.internet_payBtn_text)).setTextColor(Color.WHITE);
            ((TextView) findViewById(R.id.internet_payBtn_text)).setText("PAID");
            internet_p0_btn.setImageResource(R.drawable.internet_paybutton);
        }
        else{
            internet_p0_btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    internet_pay();
                }
            });
        }
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),1)) {
            ((TextView) findViewById(R.id.internet_person1_text)).setTextColor(Color.WHITE);
            internet_p1_btn.setImageResource(R.drawable.internet_personpaidbutton);
        }
        else {
            internet_p1_btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    internet_person1_pay();
                    return true;
                }
            });
        }
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),2)) {
            ((TextView) findViewById(R.id.internet_person2_text)).setTextColor(Color.WHITE);
            internet_p2_btn.setImageResource(R.drawable.internet_personpaidbutton);
        }
        else {
            internet_p2_btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    internet_person2_pay();
                    return true;
                }
            });
        }
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),3)) {
            ((TextView) findViewById(R.id.internet_person3_text)).setTextColor(Color.WHITE);
            internet_p3_btn.setImageResource(R.drawable.internet_personpaidbutton);
        }
        else {
            internet_p3_btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    internet_person3_pay();
                    return true;
                }
            });
        }
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),4)) {
            ((TextView) findViewById(R.id.internet_person4_text)).setTextColor(Color.WHITE);
            internet_p4_btn.setImageResource(R.drawable.internet_personpaidbutton);
        }
        else {
            internet_p4_btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    internet_person4_pay();
                    return true;
                }
            });
        }
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),5)) {
            ((TextView) findViewById(R.id.internet_person5_text)).setTextColor(Color.WHITE);
            internet_p5_btn.setImageResource(R.drawable.internet_personpaidbutton);
        }
        else {
            internet_p5_btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    internet_person5_pay();
                    return true;
                }
            });
        }
    }

    public void change_history() {
        AlertDialog.Builder historyChanger_Builder = new AlertDialog.Builder(Internet.this);
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
        TextView history1 = (TextView) findViewById(R.id.internet_paidDate1);
        history1.setText(mDatabaseHelper.getPaidDate_fromHistory(type, duemonth-3, dueyear)[2]);
        if (history1.getText() == "")
            findViewById(R.id.internet_paidDate1_bullet).setVisibility(View.INVISIBLE);
        else {
            history1.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    return true;
                }
            });
        }
        TextView history2 = (TextView) findViewById(R.id.internet_paidDate2);
        history2.setText(mDatabaseHelper.getPaidDate_fromHistory(type, duemonth-2, dueyear)[1]);
        if (history2.getText() == "")
            findViewById(R.id.internet_paidDate2_bullet).setVisibility(View.INVISIBLE);
        else {
            history1.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    return true;
                }
            });
        }
        TextView history3 = (TextView) findViewById(R.id.internet_paidDate3);
        history3.setText(mDatabaseHelper.getPaidDate_fromHistory(type, duemonth-1, dueyear)[0]);
        if (history3.getText() == "")
            findViewById(R.id.internet_paidDate3_bullet).setVisibility(View.INVISIBLE);
        else {
            history1.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    return true;
                }
            });
        }
    }
}
