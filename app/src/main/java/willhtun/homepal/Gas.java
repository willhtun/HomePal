package willhtun.homepal;

import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Gas extends AppCompatActivity {

    String type = "gas";
    int duedate = 0;
    int duemonth = 0;
    int dueyear = 0;

    DatabaseHelper mDatabaseHelper;
    CalendarHelper mCalendarHelper;
    AlertDialog mHistoryChangerDialog;

    ImageView gas_p0_btn;
    ImageView gas_p1_btn;
    ImageView gas_p2_btn;
    ImageView gas_p3_btn;
    ImageView gas_p4_btn;
    ImageView gas_p5_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas);

        mDatabaseHelper = new DatabaseHelper(getApplicationContext());
        mCalendarHelper = new CalendarHelper(mDatabaseHelper);

        duedate = mDatabaseHelper.getDate_fromDueDate(type); if (duedate < 0) duedate = 1;
        duemonth = mCalendarHelper.getCycleMonth(type);
        dueyear = mCalendarHelper.getCycleYear(type);

        gas_p0_btn = (ImageView) findViewById(R.id.gas_payBtn);
        gas_p1_btn = (ImageView) findViewById(R.id.gas_person1);
        gas_p2_btn = (ImageView) findViewById(R.id.gas_person2);
        gas_p3_btn = (ImageView) findViewById(R.id.gas_person3);
        gas_p4_btn = (ImageView) findViewById(R.id.gas_person4);
        gas_p5_btn = (ImageView) findViewById(R.id.gas_person5);

        ((TextView) findViewById(R.id.gas_dueDate)).setText("Due: " + duemonth + "/" + duedate);
        ((TextView) findViewById(R.id.gas_cost)).setText("$ " + String.valueOf(mDatabaseHelper.getCost_fromDueDate(type)));

        loadHistory();

        loadButtons();
    }

    public void gas_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), mCalendarHelper.getTodayDate(), 0);
        ((TextView) findViewById(R.id.gas_payBtn_text)).setTextColor(Color.WHITE);
        ((TextView) findViewById(R.id.gas_payBtn_text)).setText("PAID");
        gas_p0_btn.setImageResource(R.drawable.gas_paybutton);
        loadHistory();
    }

    public void gas_person1_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 111, 1);
        ((TextView) findViewById(R.id.gas_person1_text)).setTextColor(Color.WHITE);
        gas_p1_btn.setImageResource(R.drawable.gas_personpaidbutton);
        loadHistory();
    }

    public void gas_person2_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 222, 2);
        ((TextView) findViewById(R.id.gas_person2_text)).setTextColor(Color.WHITE);
        gas_p2_btn.setImageResource(R.drawable.gas_personpaidbutton);
        loadHistory();
    }

    public void gas_person3_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 333, 3);
        ((TextView) findViewById(R.id.gas_person3_text)).setTextColor(Color.WHITE);
        gas_p3_btn.setImageResource(R.drawable.gas_personpaidbutton);
        loadHistory();
    }

    public void gas_person4_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 444, 4);
        ((TextView) findViewById(R.id.gas_person4_text)).setTextColor(Color.WHITE);
        gas_p4_btn.setImageResource(R.drawable.gas_personpaidbutton);
        loadHistory();
    }

    public void gas_person5_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 555, 5);
        ((TextView) findViewById(R.id.gas_person5_text)).setTextColor(Color.WHITE);
        gas_p5_btn.setImageResource(R.drawable.gas_personpaidbutton);
        loadHistory();
    }

    public void loadButtons() {
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),0)) {
            ((TextView) findViewById(R.id.gas_payBtn_text)).setTextColor(Color.WHITE);
            ((TextView) findViewById(R.id.gas_payBtn_text)).setText("PAID");
            gas_p0_btn.setImageResource(R.drawable.gas_paybutton);
        }
        else{
            gas_p0_btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    gas_pay();
                }
            });
        }
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),1)) {
            ((TextView) findViewById(R.id.gas_person1_text)).setTextColor(Color.WHITE);
            gas_p1_btn.setImageResource(R.drawable.gas_personpaidbutton);
        }
        else {
            gas_p1_btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    gas_person1_pay();
                    return true;
                }
            });
        }
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),2)) {
            ((TextView) findViewById(R.id.gas_person2_text)).setTextColor(Color.WHITE);
            gas_p2_btn.setImageResource(R.drawable.gas_personpaidbutton);
        }
        else {
            gas_p2_btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    gas_person2_pay();
                    return true;
                }
            });
        }
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),3)) {
            ((TextView) findViewById(R.id.gas_person3_text)).setTextColor(Color.WHITE);
            gas_p3_btn.setImageResource(R.drawable.gas_personpaidbutton);
        }
        else {
            gas_p3_btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    gas_person3_pay();
                    return true;
                }
            });
        }
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),4)) {
            ((TextView) findViewById(R.id.gas_person4_text)).setTextColor(Color.WHITE);
            gas_p4_btn.setImageResource(R.drawable.gas_personpaidbutton);
        }
        else {
            gas_p4_btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    gas_person4_pay();
                    return true;
                }
            });
        }
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),5)) {
            ((TextView) findViewById(R.id.gas_person5_text)).setTextColor(Color.WHITE);
            gas_p5_btn.setImageResource(R.drawable.gas_personpaidbutton);
        }
        else {
            gas_p5_btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    gas_person5_pay();
                    return true;
                }
            });
        }
    }

    public void change_history() {
        AlertDialog.Builder historyChanger_Builder = new AlertDialog.Builder(Gas.this);
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
        TextView history1 = (TextView) findViewById(R.id.gas_paidDate1);
        history1.setText(mDatabaseHelper.getPaidDate_fromHistory(type, duemonth-3, dueyear)[2]);
        if (history1.getText() == "")
            findViewById(R.id.gas_paidDate1_bullet).setVisibility(View.INVISIBLE);
        else {
            history1.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    return true;
                }
            });
        }
        TextView history2 = (TextView) findViewById(R.id.gas_paidDate2);
        history2.setText(mDatabaseHelper.getPaidDate_fromHistory(type, duemonth-2, dueyear)[1]);
        if (history2.getText() == "")
            findViewById(R.id.gas_paidDate2_bullet).setVisibility(View.INVISIBLE);
        else {
            history1.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    return true;
                }
            });
        }
        TextView history3 = (TextView) findViewById(R.id.gas_paidDate3);
        history3.setText(mDatabaseHelper.getPaidDate_fromHistory(type, duemonth-1, dueyear)[0]);
        if (history3.getText() == "")
            findViewById(R.id.gas_paidDate3_bullet).setVisibility(View.INVISIBLE);
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
