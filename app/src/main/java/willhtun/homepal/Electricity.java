package willhtun.homepal;

import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Electricity extends AppCompatActivity {

    String type = "electricity";
    int duedate = 0;
    int duemonth = 0;
    int dueyear = 0;

    DatabaseHelper mDatabaseHelper;
    CalendarHelper mCalendarHelper;
    AlertDialog mHistoryChangerDialog;

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

        mDatabaseHelper = new DatabaseHelper(getApplicationContext());
        mCalendarHelper = new CalendarHelper(mDatabaseHelper);

        duedate = mDatabaseHelper.getDate_fromDueDate(type); if (duedate < 0) duedate = 1;
        duemonth = mCalendarHelper.getCycleMonth(type);
        dueyear = mCalendarHelper.getCycleYear(type);

        electricity_p0_btn = (ImageView) findViewById(R.id.electricity_payBtn);
        electricity_p1_btn = (ImageView) findViewById(R.id.electricity_person1);
        electricity_p2_btn = (ImageView) findViewById(R.id.electricity_person2);
        electricity_p3_btn = (ImageView) findViewById(R.id.electricity_person3);
        electricity_p4_btn = (ImageView) findViewById(R.id.electricity_person4);
        electricity_p5_btn = (ImageView) findViewById(R.id.electricity_person5);

        ((TextView) findViewById(R.id.electricity_dueDate)).setText("Due: " + duemonth + "/" + duedate);
        ((TextView) findViewById(R.id.electricity_cost)).setText("$ " + String.valueOf(mDatabaseHelper.getCost_fromDueDate(type)));

        loadHistory();

        loadButtons();
    }

    public void electricity_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), mCalendarHelper.getTodayDate(), 0);
        ((TextView) findViewById(R.id.electricity_payBtn_text)).setTextColor(Color.WHITE);
        ((TextView) findViewById(R.id.electricity_payBtn_text)).setText("PAID");
        electricity_p0_btn.setImageResource(R.drawable.electricity_paybutton);
        loadHistory();
    }

    public void electricity_person1_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 111, 1);
        ((TextView) findViewById(R.id.electricity_person1_text)).setTextColor(Color.WHITE);
        electricity_p1_btn.setImageResource(R.drawable.electricity_personpaidbutton);
        loadHistory();
    }

    public void electricity_person2_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 222, 2);
        ((TextView) findViewById(R.id.electricity_person2_text)).setTextColor(Color.WHITE);
        electricity_p2_btn.setImageResource(R.drawable.electricity_personpaidbutton);
        loadHistory();
    }

    public void electricity_person3_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 333, 3);
        ((TextView) findViewById(R.id.electricity_person3_text)).setTextColor(Color.WHITE);
        electricity_p3_btn.setImageResource(R.drawable.electricity_personpaidbutton);
        loadHistory();
    }

    public void electricity_person4_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 444, 4);
        ((TextView) findViewById(R.id.electricity_person4_text)).setTextColor(Color.WHITE);
        electricity_p4_btn.setImageResource(R.drawable.electricity_personpaidbutton);
        loadHistory();
    }

    public void electricity_person5_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 555, 5);
        ((TextView) findViewById(R.id.electricity_person5_text)).setTextColor(Color.WHITE);
        electricity_p5_btn.setImageResource(R.drawable.electricity_personpaidbutton);
        loadHistory();
    }

    public void loadButtons() {
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),0)) {
            ((TextView) findViewById(R.id.electricity_payBtn_text)).setTextColor(Color.WHITE);
            ((TextView) findViewById(R.id.electricity_payBtn_text)).setText("PAID");
            electricity_p0_btn.setImageResource(R.drawable.electricity_paybutton);
        }
        else{
            electricity_p0_btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    electricity_pay();
                }
            });
        }
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),1)) {
            ((TextView) findViewById(R.id.electricity_person1_text)).setTextColor(Color.WHITE);
            electricity_p1_btn.setImageResource(R.drawable.electricity_personpaidbutton);
        }
        else {
            electricity_p1_btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    electricity_person1_pay();
                    return true;
                }
            });
        }
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),2)) {
            ((TextView) findViewById(R.id.electricity_person2_text)).setTextColor(Color.WHITE);
            electricity_p2_btn.setImageResource(R.drawable.electricity_personpaidbutton);
        }
        else {
            electricity_p2_btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    electricity_person2_pay();
                    return true;
                }
            });
        }
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),3)) {
            ((TextView) findViewById(R.id.electricity_person3_text)).setTextColor(Color.WHITE);
            electricity_p3_btn.setImageResource(R.drawable.electricity_personpaidbutton);
        }
        else {
            electricity_p3_btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    electricity_person3_pay();
                    return true;
                }
            });
        }
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),4)) {
            ((TextView) findViewById(R.id.electricity_person4_text)).setTextColor(Color.WHITE);
            electricity_p4_btn.setImageResource(R.drawable.electricity_personpaidbutton);
        }
        else {
            electricity_p4_btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    electricity_person4_pay();
                    return true;
                }
            });
        }
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),5)) {
            ((TextView) findViewById(R.id.electricity_person5_text)).setTextColor(Color.WHITE);
            electricity_p5_btn.setImageResource(R.drawable.electricity_personpaidbutton);
        }
        else {
            electricity_p5_btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    electricity_person5_pay();
                    return true;
                }
            });
        }
    }

    public void change_history() {
        AlertDialog.Builder historyChanger_Builder = new AlertDialog.Builder(Electricity.this);
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
        TextView history1 = (TextView) findViewById(R.id.electricity_paidDate1);
        history1.setText(mDatabaseHelper.getPaidDate_fromHistory(type, duemonth-3, dueyear)[2]);
        if (history1.getText() == "")
            findViewById(R.id.electricity_paidDate1_bullet).setVisibility(View.INVISIBLE);
        else {
            history1.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    return true;
                }
            });
        }
        TextView history2 = (TextView) findViewById(R.id.electricity_paidDate2);
        history2.setText(mDatabaseHelper.getPaidDate_fromHistory(type, duemonth-2, dueyear)[1]);
        if (history2.getText() == "")
            findViewById(R.id.electricity_paidDate2_bullet).setVisibility(View.INVISIBLE);
        else {
            history1.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    return true;
                }
            });
        }
        TextView history3 = (TextView) findViewById(R.id.electricity_paidDate3);
        history3.setText(mDatabaseHelper.getPaidDate_fromHistory(type, duemonth-1, dueyear)[0]);
        if (history3.getText() == "")
            findViewById(R.id.electricity_paidDate3_bullet).setVisibility(View.INVISIBLE);
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
