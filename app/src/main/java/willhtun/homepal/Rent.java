package willhtun.homepal;

import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Rent extends AppCompatActivity {
    
    String type = "rent";
    int duedate = 0;
    int duemonth = 0;
    int dueyear = 0;

    DatabaseHelper mDatabaseHelper;
    CalendarHelper mCalendarHelper;
    AlertDialog mHistoryChangerDialog;

    ImageView rent_p0_btn;
    ImageView rent_p1_btn;
    ImageView rent_p2_btn;
    ImageView rent_p3_btn;
    ImageView rent_p4_btn;
    ImageView rent_p5_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);

        mDatabaseHelper = new DatabaseHelper(getApplicationContext());
        mCalendarHelper = new CalendarHelper(mDatabaseHelper);

        duedate = mDatabaseHelper.getDate_fromDueDate(type); if (duedate < 0) duedate = 1;
        duemonth = mCalendarHelper.getCycleMonth(type);
        dueyear = mCalendarHelper.getCycleYear(type);

        rent_p0_btn = (ImageView) findViewById(R.id.rent_payBtn);
        rent_p1_btn = (ImageView) findViewById(R.id.rent_person1);
        rent_p2_btn = (ImageView) findViewById(R.id.rent_person2);
        rent_p3_btn = (ImageView) findViewById(R.id.rent_person3);
        rent_p4_btn = (ImageView) findViewById(R.id.rent_person4);
        rent_p5_btn = (ImageView) findViewById(R.id.rent_person5);

        ((TextView) findViewById(R.id.rent_dueDate)).setText("Due: " + duemonth + "/" + duedate);
        ((TextView) findViewById(R.id.rent_cost)).setText("$ " + String.valueOf(mDatabaseHelper.getCost_fromDueDate(type)));

        loadHistory();

        loadButtons();
    }

    public void rent_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), mCalendarHelper.getTodayDate(), 0);
        ((TextView) findViewById(R.id.rent_payBtn_text)).setTextColor(Color.WHITE);
        ((TextView) findViewById(R.id.rent_payBtn_text)).setText("PAID");
        rent_p0_btn.setImageResource(R.drawable.rent_paybutton);
        loadHistory();
    }

    public void rent_person1_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 111, 1);
        ((TextView) findViewById(R.id.rent_person1_text)).setTextColor(Color.WHITE);
        rent_p1_btn.setImageResource(R.drawable.rent_personpaidbutton);
        loadHistory();
    }

    public void rent_person2_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 222, 2);
        ((TextView) findViewById(R.id.rent_person2_text)).setTextColor(Color.WHITE);
        rent_p2_btn.setImageResource(R.drawable.rent_personpaidbutton);
        loadHistory();
    }

    public void rent_person3_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 333, 3);
        ((TextView) findViewById(R.id.rent_person3_text)).setTextColor(Color.WHITE);
        rent_p3_btn.setImageResource(R.drawable.rent_personpaidbutton);
        loadHistory();
    }

    public void rent_person4_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 444, 4);
        ((TextView) findViewById(R.id.rent_person4_text)).setTextColor(Color.WHITE);
        rent_p4_btn.setImageResource(R.drawable.rent_personpaidbutton);
        loadHistory();
    }

    public void rent_person5_pay() {
        mDatabaseHelper.addData_toHistory(type, mCalendarHelper.getCycleMonthYear(type), 555, 5);
        ((TextView) findViewById(R.id.rent_person5_text)).setTextColor(Color.WHITE);
        rent_p5_btn.setImageResource(R.drawable.rent_personpaidbutton);
        loadHistory();
    }

    public void loadButtons() {
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),0)) {
            ((TextView) findViewById(R.id.rent_payBtn_text)).setTextColor(Color.WHITE);
            ((TextView) findViewById(R.id.rent_payBtn_text)).setText("PAID");
            rent_p0_btn.setImageResource(R.drawable.rent_paybutton);
        }
        else{
            rent_p0_btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    rent_pay();
                }
            });
        }
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),1)) {
            ((TextView) findViewById(R.id.rent_person1_text)).setTextColor(Color.WHITE);
            rent_p1_btn.setImageResource(R.drawable.rent_personpaidbutton);
        }
        else {
            rent_p1_btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    rent_person1_pay();
                    return true;
                }
            });
        }
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),2)) {
            ((TextView) findViewById(R.id.rent_person2_text)).setTextColor(Color.WHITE);
            rent_p2_btn.setImageResource(R.drawable.rent_personpaidbutton);
        }
        else {
            rent_p2_btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    rent_person2_pay();
                    return true;
                }
            });
        }
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),3)) {
            ((TextView) findViewById(R.id.rent_person3_text)).setTextColor(Color.WHITE);
            rent_p3_btn.setImageResource(R.drawable.rent_personpaidbutton);
        }
        else {
            rent_p3_btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    rent_person3_pay();
                    return true;
                }
            });
        }
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),4)) {
            ((TextView) findViewById(R.id.rent_person4_text)).setTextColor(Color.WHITE);
            rent_p4_btn.setImageResource(R.drawable.rent_personpaidbutton);
        }
        else {
            rent_p4_btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    rent_person4_pay();
                    return true;
                }
            });
        }
        if (mDatabaseHelper.isDataExists_fromHistory(type, mCalendarHelper.getCycleMonthYear(type),5)) {
            ((TextView) findViewById(R.id.rent_person5_text)).setTextColor(Color.WHITE);
            rent_p5_btn.setImageResource(R.drawable.rent_personpaidbutton);
        }
        else {
            rent_p5_btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    rent_person5_pay();
                    return true;
                }
            });
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
    }
}
